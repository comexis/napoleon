package eu.comexis.napoleon.client;
import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.shared.model.*;

public interface NapoleonServiceAsync {
	
	// CREATE
	void createTenant(AsyncCallback<Tenant> callback);
	void createRealEstate(AsyncCallback<RealEstate> callback);
	void createOwner(AsyncCallback<Owner> callback);
	void createCondo(AsyncCallback<Condo> callback);
	void createLease(Long realEstateId,Long tenantId,Date startDate, Date endDate,AsyncCallback<Lease> callback);
	// READ
	void findTenantById(Long id,AsyncCallback<Tenant> callback);
	void findTenantByName(String name,AsyncCallback<Tenant> callback);
	void findOwnerById(Long id,AsyncCallback<Owner> callback);
	void findOwnerByName(String name,AsyncCallback<Owner> callback);
	void findRealEstateById(Long id,AsyncCallback<RealEstate> callback);
	void findRealEstateByRef(String reference,AsyncCallback<RealEstate> callback);
	void findCondoById(Long id,AsyncCallback<Condo> callback);
	void findCondoByName(String name,AsyncCallback<Condo> callback);
	void findLeaseByParties(Long realEstateId,Long tenantId,AsyncCallback<Lease> callback);
	void findActualLeaseForTenant(Long tenantId,AsyncCallback<Lease> callback);
	void findLeaseHistoryForTenant(Long tenantId,AsyncCallback<Lease[]> callback);
	void findActualLeaseForRealEstate(Long realEstateId,AsyncCallback<Lease> callback);
	void findLeaseHistoryForRealEstate(Long realEstateId,AsyncCallback<Lease[]> callback);
	// UPDATE
	void updateOwner(Owner owner,AsyncCallback<Boolean> callback);
	void updateTenant(Tenant tenant,AsyncCallback<Boolean> callback);
	void updateRealEstate(RealEstate rental,AsyncCallback<Boolean> callback);
	void updateCondo(Condo condo,AsyncCallback<Boolean> callback);
	void updateLease(Lease lease,AsyncCallback<Boolean> callback);
	// DELETE
	void deleteOwner(Owner owner,AsyncCallback<Boolean> callback);
	void deleteTenant(Tenant tenant,AsyncCallback<Boolean> callback);
	void deleteRealEstate(RealEstate rental,AsyncCallback<Boolean> callback);
	void deleteCondo(Condo condo,AsyncCallback<Boolean> callback);
	void deleteLease(Lease lease,AsyncCallback<Boolean> callback);
}
