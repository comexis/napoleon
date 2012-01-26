package eu.comexis.napoleon.server.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import com.googlecode.objectify.Key;

import eu.comexis.napoleon.shared.model.Owner;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

public class OwnerDao extends NapoleonDao<Owner> {

	public Owner create() {
		Owner owner = new Owner();
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
		
		try {

			Key<Owner> ownerKey = ofy().put(owner);
			return ofy().get(ownerKey);
		} catch (Exception e) {
			// should raise a NapoleonDaoUpdateFailed exception
			e.printStackTrace();
			return null;
		}
	}

	
	/**
	 * returns the owner (full) corresponding to the given id
	 * 
	 * @param name
	 *            : the owner's id
	 * @return
	 */
	public Owner getById(String id) {
		try {
			Owner owner = ofy().get(Owner.class, id);
			return owner;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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
