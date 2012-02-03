/**
 * 
 */
package eu.comexis.napoleon.server.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;
import com.googlecode.objectify.util.DAOBase;

import eu.comexis.napoleon.shared.model.ApplicationUser;
import eu.comexis.napoleon.shared.model.City;
import eu.comexis.napoleon.shared.model.Company;
import eu.comexis.napoleon.shared.model.Condo;
import eu.comexis.napoleon.shared.model.Country;
import eu.comexis.napoleon.shared.model.Lease;
import eu.comexis.napoleon.shared.model.Owner;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.Tenant;

/**
 * @author xavier
 * 
 */

public class NapoleonDao<T> extends DAOBase {

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
	protected Key<Company> ancestor;
	
	@SuppressWarnings("unchecked")
	public NapoleonDao() {
		Type genericSuperclass = getClass().getGenericSuperclass();
		// Allow this class to be safely instantiated with or without a
		// parameterized type
		if (genericSuperclass instanceof ParameterizedType)
			clazz = (Class<T>) ((ParameterizedType) genericSuperclass)
					.getActualTypeArguments()[0];
	}

	//TODO delete this constructor !!!
	public NapoleonDao(String companyId) {
		this();
		ancestor = new Key<Company>(Company.class, companyId);
	}

	public Boolean delete(T entity) {
		try {
			ofy().delete(entity);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void deleteAll(Iterable<T> entities) {
		ofy().delete(entities);
	}

	public T getById(String id) {
		try {
			T entity = ofy().get(new Key<T>(ancestor, this.clazz, id));
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<T> listAll() {
		Query<T> q = ofy().query(this.clazz);
		if (ancestor != null){
			q.ancestor(ancestor);
		}
		return q.list();
	}

	public T update(T entity) {
		try {
			Key<T> entityKey = ofy().put(entity);
			return ofy().get(entityKey);
		} catch (Exception e) {
			// should raise a NapoleonDaoUpdateFailed exception
			e.printStackTrace();
			return null;
		}
	}
}
