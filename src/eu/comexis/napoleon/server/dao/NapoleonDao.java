/**
 * 
 */
package eu.comexis.napoleon.server.dao;
import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;


import com.google.gwt.logging.client.LogConfiguration;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import com.googlecode.objectify.util.DAOBase;

import eu.comexis.napoleon.server.manager.UserManager;
import eu.comexis.napoleon.shared.model.*;
/**
 * @author xavier
 *
 */

public class NapoleonDao<T> extends DAOBase{
  Logger logger = Logger.getLogger(NapoleonDao.class.getName());
	/**
	 * 
	 */
  static {
    ObjectifyService.register(Country.class);
    ObjectifyService.register(City.class);
    ObjectifyService.register(Owner.class);
    ObjectifyService.register(Tenant.class);
    ObjectifyService.register(Condo.class);
    ObjectifyService.register(RealEstate.class);
    ObjectifyService.register(Lease.class);
    ObjectifyService.register(ApplicationUser.class);
  }
  protected Class<T> clazz;
  protected Key<Company> companyKey;
  public NapoleonDao(String companyId){
    companyKey = new Key<Company>(Company.class,companyId);
    Type genericSuperclass = getClass().getGenericSuperclass();
    // Allow this class to be safely instantiated with or without a parameterized type
    if (genericSuperclass instanceof ParameterizedType)
      clazz = (Class<T>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
  }
  public T update(T entity){
    try{
      Key<T> entityKey = ofy().put(entity);
      logger.info("Entity " + clazz + " has been updated");
      return ofy().get(entityKey);
    }catch (Exception e){
      logger.severe("Entity " + clazz + " cannot be updated: " + e.getMessage());
      return null;
    }
  }
  
  public Boolean delete(T entity){
    try{
      ofy().delete(entity);
      return true;
    }catch (Exception e){
      e.printStackTrace();
      return false;
    }
  }
  public List<T> listAll()
  {
          System.out.println("List all for Parent " + companyKey.toString());
          Query<T> q = ofy().query(this.clazz);
          q.ancestor(companyKey);
          return q.list();
  }
  public void deleteAll(Iterable<T> entities)
  {
          ofy().delete(entities);
  }
  public T getById(String id) {
    try {
      T entity = ofy().get(new Key<T>(companyKey,this.clazz, id));
      return entity;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
