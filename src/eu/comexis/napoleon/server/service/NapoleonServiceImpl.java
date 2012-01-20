/**
 * 
 */
package eu.comexis.napoleon.server.service;

import java.util.Date;

import eu.comexis.napoleon.client.NapoleonService;
import eu.comexis.napoleon.shared.model.*;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author xavier
 *
 */
public class NapoleonServiceImpl extends RemoteServiceServlet implements NapoleonService{
	public static NapoleonDao model = new NapoleonDao();
	// CREATE
	public Tenant createTenant(){return null;}
	public RealEstate createRealEstate(){return null;}
	public Owner createOwner(){return null;}
	public Condo createCondo(){return null;}
	public Lease createLease(Long realEstateId,Long tenantId,Date startDate, Date endDate){return null;}
	// READ
	public Tenant findTenantById(Long id){return null;}
	public Tenant findTenantByName(String name){return null;}
	public Owner findOwnerById(Long id){return null;}
	public Owner findOwnerByName(String name){return null;}
	public RealEstate findRealEstateById(Long id){return null;}
	public RealEstate findRealEstateByRef(String reference){return null;}
	public Condo findCondoById(Long id){return null;}
	public Condo findCondoByName(String name){return null;}
	public Lease findLeaseByParties(Long realEstateId,Long tenantId){return null;}
	public Lease findActualLeaseForTenant(Long tenantId){return null;}
	public Lease[] findLeaseHistoryForTenant(Long tenantId){return null;}
	public Lease findActualLeaseForRealEstate(Long realEstateId){return null;}
	public Lease[] findLeaseHistoryForRealEstate(Long realEstateId){return null;}
	// UPDATE
	public Boolean updateOwner(Owner owner){return null;}
	public Boolean updateTenant(Tenant tenant){return null;}
	public Boolean updateRealEstate(RealEstate rental){return null;}
	public Boolean updateCondo(Condo condo){return null;}
	public Boolean updateLease(Lease lease){return null;}
	// DELETE
	public Boolean deleteOwner(Owner owner){return null;}
	public Boolean deleteTenant(Tenant tenant){return null;}
	public Boolean deleteRealEstate(RealEstate rental){return null;}
	public Boolean deleteCondo(Condo condo){return null;}
	public Boolean deleteLease(Lease lease){return null;}
}
