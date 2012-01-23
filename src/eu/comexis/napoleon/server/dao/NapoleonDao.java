/**
 * 
 */
package eu.comexis.napoleon.server.dao;

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
}
