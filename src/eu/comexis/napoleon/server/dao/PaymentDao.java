package eu.comexis.napoleon.server.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import com.googlecode.objectify.util.DAOBase;

import eu.comexis.napoleon.shared.model.Company;
import eu.comexis.napoleon.shared.model.Lease;
import eu.comexis.napoleon.shared.model.Payment;
import eu.comexis.napoleon.shared.model.PaymentOwner;
import eu.comexis.napoleon.shared.model.PaymentTenant;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.simple.PaymentListItem;

public class PaymentDao<T extends Payment> extends DAOBase{
  protected Class<T> clazz;
  public Class<T> getClazz() {
    return clazz;
  }
  public void setClazz(Class<T> clazz) {
    this.clazz = clazz;
  }
  public PaymentDao() {
  }
  public List<T> getPaymentsForLease(String leaseId, String realEstateId, String companyId){
    Key<Company> companyKey = new Key<Company>(Company.class, companyId);
    Key<RealEstate> estateKey = new Key<RealEstate>(companyKey,RealEstate.class, realEstateId);
    Key<Lease> leaseKey = new Key<Lease>(estateKey,Lease.class, leaseId);
    LOG.info("Get list payment (" + clazz + ") for lease " + leaseId);
    Query<T> q = ofy().query(clazz);
    q.ancestor(leaseKey);
    List<T> payments = q.list();
    return payments;
  }
  public static Log LOG = LogFactory.getLog(PaymentDao.class);
  static {
    ObjectifyService.register(PaymentOwner.class);
    ObjectifyService.register(PaymentTenant.class);
  }

  public T getById(String paymentId, String leaseId, String realEstateId, String companyId) {
    Key<Company> companyKey = new Key<Company>(Company.class, companyId);
    Key<RealEstate> estateKey = new Key<RealEstate>(companyKey,RealEstate.class, realEstateId);
    Key<Lease> leaseKey = new Key<Lease>(estateKey,Lease.class, leaseId);
    T p = ofy().find(new Key<T>(leaseKey, clazz, paymentId));
    p.setLeaseId(leaseId);
    p.setEstateId(realEstateId);
    return p;
  }
  public List<T> getPaymentForLease(Key<Lease> leaseKey) {
    // get the lease for the company
    Query<T> ql = ofy().query(clazz);
    ql.ancestor(leaseKey);
    return ql.list();
  }
  public T isAlreadyPaid(String id,Key<Lease> leaseKey, Date startDate, Date endDate) {
    Query<T> q = ofy().query(clazz);
    q.ancestor(leaseKey);
    List<T> payments = q.filter("periodEndDate >=", startDate).order("periodEndDate").list();
    for (T p:payments){
      if (!p.getId().equals(id) && p.getPeriodStartDate().compareTo(endDate) <= 0){
        return p;
      }
    }
    return null;
  }
  public T update(T payment,String companyId) {
    Key<Company> companyKey = new Key<Company>(Company.class, companyId);
    return update(payment,companyKey);
  }
  public T update(T payment, Key<Company> companyKey) {
    LOG.info("Update PaymentOwner");
    String paymentId = payment.getId();
    Key<Lease> leaseKey = null;
    Key<RealEstate> estateKey = null;
    // create unique id if new entity
    
    if (paymentId == null || paymentId.length() == 0) {
      UUID uuid = UUID.randomUUID();
      System.out.println("Creating Uuid " + uuid.toString());
      payment.setId(uuid.toString());
    }
    // set parent
    if (payment.getLeaseKey() == null) {
      if (payment.getLeaseId() != null && payment.getEstateId() != null) {
        estateKey = new Key<RealEstate>(companyKey,RealEstate.class, payment.getEstateId());
        leaseKey =
            new Key<Lease>(estateKey,Lease.class, payment.getLeaseId());
        payment.setLeaseKey(leaseKey);
        
      } else {
        LOG.fatal("Lease cannot be updated, missing parent RealEstate");
        return null;
      }
    }else{
      leaseKey =payment.getLeaseKey();
    }
    T actualPayment =isAlreadyPaid(payment.getId(),leaseKey,payment.getPeriodStartDate(),payment.getPeriodEndDate());
    if (actualPayment!=null){
      LOG.info("Payment cannot be updated because there is already a payment for that period " + actualPayment.getId());
      return null;
    }
    try {
      Key<T> paymentKey = ofy().put(payment);
      LOG.info("Payment has been updated");
      Lease l = ofy().get(payment.getLeaseKey());
      RealEstate e = ofy().get(l.getRealEstateKey());
      T pt = ofy().get(paymentKey);
      pt.setEstateId(e.getId());
      pt.setLeaseId(l.getId());
      return pt;
    } catch (Exception e) {
      LOG.fatal("Payment cannot be updated: ", e);
      return null;
    }
  }
}
