package eu.comexis.napoleon.server.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import com.googlecode.objectify.Key;

import eu.comexis.napoleon.shared.model.City;
import eu.comexis.napoleon.shared.model.Company;
import eu.comexis.napoleon.shared.model.Country;
import eu.comexis.napoleon.shared.model.Owner;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;



public class OwnerDao extends NapoleonDao<Owner> {
  public OwnerDao() {
    super();
    // TODO Auto-generated constructor stub
  }

  public Owner create(String companyId) {
    Key<Company> companyKey = new Key<Company>(Company.class, companyId);
		Owner owner = new Owner();
		System.out.println("Set company key " + companyKey.toString());
		owner.setCompany(companyKey);
		return owner;
	}

	@Override
	public Owner update(Owner owner) {
		String ownerId = owner.getId();
		Key<Company> companyKey = owner.getCompany();
		CountryDao countryData = new CountryDao();
		if (ownerId == null || ownerId.length() == 0){
			UUID uuid = UUID.randomUUID();
			System.out.println("Creating Uuid " + uuid.toString());
			owner.setId(uuid.toString());
		}
		// if country does not exist, create it.
		Country country = countryData.getByName(owner.getCountry(),companyKey);
		if (country == null){
		  country = countryData.create(companyKey);
		  country.setName(owner.getCountry());
		  countryData.update(country);
		}
		City city = countryData.getCityByName(country.getId(), owner.getCity());
		if (city == null){
		  city = countryData.addCity(country.getId(), owner.getCity());
		}
		return super.update(owner);
	}

	/**
	 * Retrieves the list of all owners stored in the database (with the minimum
	 * of data to be shown in UI)
	 * 
	 * @return The list of owners
	 */
	public ArrayList<SimpleOwner> getListSimpleOwners(String companyId) {
	  LOG.info("Get list Owners (" + clazz + ") for company " + companyId);
	  Key<Company> companyKey = new Key<Company>(Company.class, companyId);
		Iterator<Owner> iterator = this.listAll(companyId).iterator();
		ArrayList<SimpleOwner> owners = new ArrayList<SimpleOwner>();
		while (iterator.hasNext()) {
			Owner owner = iterator.next();
			SimpleOwner o = new SimpleOwner();
			o.setId(owner.getId());
			o.setName(owner.getLastName());
			o.setCity(owner.getCity());
			o.setAddress(owner.getStreet());
			o.setMobileNumber(owner.getMobilePhoneNumber());
			o.setPhoneNumber(owner.getPhoneNumber());
			owners.add(o);
		}
		return owners;
	}
}
