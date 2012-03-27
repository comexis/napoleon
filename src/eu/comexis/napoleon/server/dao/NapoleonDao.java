/**
 * 
 */
package eu.comexis.napoleon.server.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import com.googlecode.objectify.util.DAOBase;

import eu.comexis.napoleon.shared.model.AcademicYear;
import eu.comexis.napoleon.shared.model.ApplicationUser;
import eu.comexis.napoleon.shared.model.Association;
import eu.comexis.napoleon.shared.model.City;
import eu.comexis.napoleon.shared.model.Company;
import eu.comexis.napoleon.shared.model.Condo;
import eu.comexis.napoleon.shared.model.Contractor;
import eu.comexis.napoleon.shared.model.Country;
import eu.comexis.napoleon.shared.model.Expense;
import eu.comexis.napoleon.shared.model.JobTitle;
import eu.comexis.napoleon.shared.model.Lease;
import eu.comexis.napoleon.shared.model.Nationality;
import eu.comexis.napoleon.shared.model.Owner;
import eu.comexis.napoleon.shared.model.Ownership;
import eu.comexis.napoleon.shared.model.Payment;
import eu.comexis.napoleon.shared.model.PaymentOwner;
import eu.comexis.napoleon.shared.model.PaymentTenant;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.Tenant;
import eu.comexis.napoleon.shared.model.TypeOfWork;

/**
 * @author xavier
 * 
 */
public class NapoleonDao<T> extends DAOBase {
  public static Log LOG = LogFactory.getLog(NapoleonDao.class);
  /**
   * 
   */
  static {
    ObjectifyService.register(Country.class);
    ObjectifyService.register(City.class);
    ObjectifyService.register(Owner.class);
    ObjectifyService.register(Ownership.class);
    ObjectifyService.register(Tenant.class);
    ObjectifyService.register(Condo.class);
    ObjectifyService.register(RealEstate.class);
    ObjectifyService.register(ApplicationUser.class);
    ObjectifyService.register(Association.class);
    ObjectifyService.register(Nationality.class);
    ObjectifyService.register(JobTitle.class);
    ObjectifyService.register(AcademicYear.class);
    ObjectifyService.register(Payment.class);
    ObjectifyService.register(PaymentOwner.class);
    ObjectifyService.register(PaymentTenant.class);
    ObjectifyService.register(Lease.class);
    ObjectifyService.register(Company.class);
    ObjectifyService.register(Expense.class);
    ObjectifyService.register(TypeOfWork.class);
    ObjectifyService.register(Contractor.class);
  }
  protected Class<T> clazz;

  // protected Key<Company> companyKey;

  @SuppressWarnings("unchecked")
  public NapoleonDao() {
    Type genericSuperclass = getClass().getGenericSuperclass();
    // Allow this class to be safely instantiated with or without a
    // parameterized type
    if (genericSuperclass instanceof ParameterizedType)
      clazz = (Class<T>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
  }

  public Boolean delete(T entity) {
    LOG.info("Delete Entity " + clazz);
    try {
      ofy().delete(entity);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public void deleteAll(Iterable<T> entities) {
    LOG.info("Delete all Entities " + entities.toString());
    ofy().delete(entities);
    LOG.info("Done");
  }

  public T getById(String id, Key<Company> companyKey) {
    LOG.info("Get Entity " + clazz + " by id " + id + " for company " + companyKey.toString());
    try {
      T entity = ofy().get(new Key<T>(companyKey, this.clazz, id));
      return entity;
    } catch (Exception e) {
      e.printStackTrace();
      LOG.error("Entity not found " + e.getMessage());
      return null;
    }
  }

  public T getById(String id, String companyId) {
    Key<Company> companyKey = new Key<Company>(Company.class, companyId);
    return getById(id, companyKey);
  }

  public List<T> listAll(Key<Company> companyKey) {
    LOG.info("List all Entity " + clazz + " for company " + companyKey.toString());
    Query<T> q = ofy().query(this.clazz);
    if (companyKey != null) {
      q.ancestor(companyKey);
      return q.list();
    }
    return null;
  }

  public List<T> listAll(String companyId) {
    Key<Company> companyKey = new Key<Company>(Company.class, companyId);
    return listAll(companyKey);
  }
  public T update(T entity) {
    LOG.info("Update Entity " + clazz);
    try {
      Key<T> entityKey = ofy().put(entity);
      LOG.info("Entity " + clazz + " has been updated");
      return ofy().get(entityKey);
    } catch (Exception e) {
      LOG.fatal("Entity " + clazz + " cannot be updated: ",e);
      return null;
    }
  }
}