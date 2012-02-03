package eu.comexis.napoleon.server.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import eu.comexis.napoleon.shared.model.Owner;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

public class OwnerDao extends NapoleonDao<Owner> {

  public OwnerDao(String companyId) {
    super(companyId);
    // TODO Auto-generated constructor stub
  }

  public Owner create() {
		Owner owner = new Owner();
		System.out.println("Set company key " + ancestor.toString());
		owner.setCompany(ancestor);
		return owner;
	}

	@Override
	public Owner update(Owner owner) {
		String ownerId = owner.getId();
		
		if (ownerId == null || ownerId.length() == 0){
			UUID uuid = UUID.randomUUID();
			System.out.println("Creating Uuid " + uuid.toString());
			owner.setId(uuid.toString());
		}
		return super.update(owner);
	}

	/**
	 * Retrieves the list of all owners stored in the database (with the minimum
	 * of data to be shown in UI)
	 * 
	 * @return The list of owners
	 */
	public ArrayList<SimpleOwner> getListSimpleOwners() {
		Iterator<Owner> iterator = this.listAll().iterator();
		ArrayList<SimpleOwner> owners = new ArrayList<SimpleOwner>();
		while (iterator.hasNext()) {
			Owner owner = iterator.next();
			SimpleOwner o = new SimpleOwner();
			o.setId(owner.getId());
			o.setName(owner.getLastName());
			o.setPostalCode(owner.getPostalCode());
			o.setCity(owner.getCity());
			o.setAddress(owner.getStreet());
			o.setMobileNumber(owner.getMobilePhoneNumber());
			o.setPhoneNumber(owner.getPhoneNumber());
			owners.add(o);
		}
		return owners;
	}
}
