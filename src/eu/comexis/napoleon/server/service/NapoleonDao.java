/**
 * 
 */
package eu.comexis.napoleon.server.service;

import java.util.Map;

import com.google.appengine.api.datastore.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;

import eu.comexis.napoleon.shared.model.*;

/**
 * @author xavier
 *
 */
public class NapoleonDao extends DAOBase{
	/**
	 * 
	 */
	static
    {
            ObjectifyService.register(Owner.class);
            ObjectifyService.register(Tenant.class);
            ObjectifyService.register(RealEstate.class);
            ObjectifyService.register(Condo.class);
            ObjectifyService.register(Lease.class);
    }
	public NapoleonDao() {
		// TODO Auto-generated constructor stub
	}
	public Owner createOwner(){
		Owner owner = new Owner();
		return owner;
	}
	public Boolean updateOwner(Owner owner){
		try{
			ofy().put(owner);
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public Boolean deleteOwner(Owner owner){
		try{
			ofy().delete(owner);
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public Owner findOwnerByName(String name){
		try{
			Owner owner = ofy().query(Owner.class).filter("lastName", name).get();
			return owner;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public Tenant createTenant(){
		Tenant tenant = new Tenant();
		return tenant;
	}
	public Boolean updateTenant(Tenant tenant){
		try{
			ofy().put(tenant);
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public Boolean deleteTenant(Tenant tenant){
		try{
			ofy().delete(tenant);
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public Tenant findTenantByName(String name){
		try{
			Tenant tenant = ofy().query(Tenant.class).filter("lastName", name).get();
			return tenant;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
