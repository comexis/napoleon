/**
 * 
 */
package eu.comexis.napoleon.server.dao;

import java.util.ArrayList;
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
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.Tenant;
import eu.comexis.napoleon.shared.model.simple.SimpleLease;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;
import eu.comexis.napoleon.shared.model.simple.SimpleRealEstate;
import eu.comexis.napoleon.shared.model.simple.SimpleTenant;

/**
 * @author xavier
 *
 */
public class LeaseDao extends DAOBase {
  public static Log LOG = LogFactory.getLog(LeaseDao.class);
  static {
    ObjectifyService.register(Company.class);
  }
  public LeaseDao() {
    // TODO Auto-generated constructor stub
  }
  public Lease update(Lease lease) {
    LOG.info("Update Lease");
    String leaseId = lease.getId();
    // create unique id if new entity
    if (leaseId == null || leaseId.length() == 0) {
      UUID uuid = UUID.randomUUID();
      System.out.println("Creating Uuid " + uuid.toString());
      lease.setId(uuid.toString());
    }
    // set parent
    if (lease.getRealEstateKey()==null){
      if (lease.getRealEstate() != null){
        Key<RealEstate> estateKey = new Key<RealEstate>(RealEstate.class, lease.getRealEstate().getId());
        lease.setRealEstateKey(estateKey);
      }else{
        LOG.fatal("Lease cannot be updated, missing parent RealEstate");
        return null;
      }
    }
    // set tenant
    if (lease.getTenantKey()==null){
      if (lease.getTenant() != null){
        Key<Tenant> tenantKey = new Key<Tenant>(Tenant.class, lease.getTenant().getId());
        lease.setTenantKey(tenantKey);
      }else{
        LOG.error("Lease cannot be updated, missing tenant");
        return null;
      }
    }
    try {
      Key<Lease> leaseKey = ofy().put(lease);
      LOG.info("Lease has been updated");
      return ofy().get(leaseKey);
    } catch (Exception e) {
      LOG.fatal("Lease cannot be updated: ",e);
      return null;
    }
  }
  public Lease getLastLeaseForRE(String realEstateId){
    return null;
  }
  public Lease isAlreadyRented(String realEstateId, Date startDate, Date endDate){
    Key<RealEstate> estateKey = new Key<RealEstate>(RealEstate.class, realEstateId);
    return isAlreadyRented(estateKey, startDate, endDate);
  }
  public Lease isAlreadyRented(Key<RealEstate> estateKey, Date startDate, Date endDate){
    Query<Lease> q = ofy().query(Lease.class);
    q.ancestor(estateKey);
    Lease lease = q.filter("endDate >=", startDate).order("endDate").get();
    if (lease!= null){
      return lease;
    }
    q = ofy().query(Lease.class);
    q.ancestor(estateKey);
    lease = q.filter("startDate <=", endDate).order("-startDate").get();
    if (lease!= null){
      return lease;
    }
    return null;
  }
  public Lease getById(String leaseId,String realEstateId){
    Key<RealEstate> estateKey = new Key<RealEstate>(RealEstate.class, realEstateId);
    return getById(leaseId,estateKey);
  }
  public Lease getById(String leaseId,Key<RealEstate> estateKey){
    Lease l = ofy().find(new Key<Lease>(estateKey, Lease.class, leaseId));
    if (l!=null){
      RealEstate realEstate = ofy().find(estateKey);
      SimpleRealEstate se = new SimpleRealEstate();
      se.setReference(realEstate.getReference());
      se.setId(realEstate.getId());
      l.setRealEstate(se);
      Tenant t = ofy().find(l.getTenantKey());
      SimpleTenant st = new SimpleTenant();
      st.setName(t.getLastName());
      st.setAddress(t.getStreet());
      st.setCity(t.getCity());
      st.setPostalCode(t.getPostalCode());
      st.setPhoneNumber(t.getPhoneNumber());
      st.setMobileNumber(t.getMobilePhoneNumber());
      l.setTenant(st);
    }
    return l;
  }
  public List<SimpleLease> getAllLease(String companyId){
    return null;
  }
  public List<SimpleLease> getAllLeaseForEstate(String realEstateId, String companyId){
    RealEstateDao eDao = new RealEstateDao();
    RealEstate e = eDao.getById(realEstateId, companyId);
    Key<RealEstate> estateKey = new Key<RealEstate>(RealEstate.class, realEstateId);
    Query<Lease> q = ofy().query(Lease.class);
    q.ancestor(estateKey);
    List<SimpleLease> leaseList = new ArrayList<SimpleLease>();
    for (Lease l: q.list()){
      SimpleLease sl = new SimpleLease();
      sl.setId(l.getId());
      sl.setRealEstateId(e.getId());
      sl.setRealEstateRef(e.getReference());
      sl.setOwnerId(e.getOwner().getId());
      sl.setOwnerName(e.getOwner().getName());
      leaseList.add(sl);
    }
    return leaseList;
  }
}
