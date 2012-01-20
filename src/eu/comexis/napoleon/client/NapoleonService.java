package eu.comexis.napoleon.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;

import eu.comexis.napoleon.shared.model.*;

public interface NapoleonService extends RemoteService {
	// CREATE
	Tenant createTenant();
	RealEstate createRealEstate();
	Owner createOwner();
	Condo createCondo();
	Lease createLease(Long realEstateId,Long tenantId,Date startDate, Date endDate);
	// READ
	Tenant findTenantById(Long id);
	Tenant findTenantByName(String name);
	Owner findOwnerById(Long id);
	Owner findOwnerByName(String name);
	RealEstate findRealEstateById(Long id);
	RealEstate findRealEstateByRef(String reference);
	Condo findCondoById(Long id);
	Condo findCondoByName(String name);
	Lease findLeaseByParties(Long realEstateId,Long tenantId);
	Lease findActualLeaseForTenant(Long tenantId);
	Lease[] findLeaseHistoryForTenant(Long tenantId);
	Lease findActualLeaseForRealEstate(Long realEstateId);
	Lease[] findLeaseHistoryForRealEstate(Long realEstateId);
	// UPDATE
	Boolean updateOwner(Owner owner);
	Boolean updateTenant(Tenant tenant);
	Boolean updateRealEstate(RealEstate rental);
	Boolean updateCondo(Condo condo);
	Boolean updateLease(Lease lease);
	// DELETE
	Boolean deleteOwner(Owner owner);
	Boolean deleteTenant(Tenant tenant);
	Boolean deleteRealEstate(RealEstate rental);
	Boolean deleteCondo(Condo condo);
	Boolean deleteLease(Lease lease);
}
