/**
 * 
 */
package eu.comexis.napoleon.server.dao;
import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import java.util.List;


import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import com.googlecode.objectify.util.DAOBase;
import eu.comexis.napoleon.shared.model.*;
/**
 * @author xavier
 *
 */

public class NapoleonDao<T> extends DAOBase{
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
  }
  protected Class<T> clazz;
  public NapoleonDao()
  {
    Type genericSuperclass = getClass().getGenericSuperclass();
    // Allow this class to be safely instantiated with or without a parameterized type
    if (genericSuperclass instanceof ParameterizedType)
      clazz = (Class<T>) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
  }
  public T update(T entity){
    try{
      Key<T> entityKey = ofy().put(entity);
      return ofy().get(entityKey);
    }catch (Exception e){
      // should raise a NapoleonDaoUpdateFailed exception
      e.printStackTrace();
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
          Query<T> q = ofy().query(this.clazz);
          return q.list();
  }
  public void deleteAll(Iterable<T> entities)
  {
          ofy().delete(entities);
  }
}
