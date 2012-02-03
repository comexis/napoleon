package eu.comexis.napoleon.server.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import com.googlecode.objectify.Key;

import eu.comexis.napoleon.shared.model.City;
import eu.comexis.napoleon.shared.model.Client;
import eu.comexis.napoleon.shared.model.Company;
import eu.comexis.napoleon.shared.model.Country;
import eu.comexis.napoleon.shared.model.Owner;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.simple.SimpleRealEstate;

public class RealEstateDao extends NapoleonDao<RealEstate> {
  private CountryDao countryData;
  public RealEstateDao(String companyId) {
    super(companyId);
    countryData = new CountryDao(companyId);
    // TODO Auto-generated constructor stub
  }

  public RealEstate create() {
		RealEstate realEstate = new RealEstate();
		System.out.println("Set company key " + companyKey.toString());
		realEstate.setCompany(companyKey);
		return realEstate;
	}

	@Override
	public RealEstate update(RealEstate realEstate) {
		String realEstateId = realEstate.getId();
		
		if (realEstateId == null || realEstateId.length() == 0){
			UUID uuid = UUID.randomUUID();
			System.out.println("Creating Uuid " + uuid.toString());
			realEstate.setId(uuid.toString());
		}
		// if country does not exist, create it.
		Country country = countryData.getByName(realEstate.getCountry());
		if (country == null){
		  country = countryData.create();
		  country.setName(realEstate.getCountry());
		  countryData.update(country);
		}
		City city = countryData.getCityByName(country.getId(), realEstate.getCity());
		if (city == null){
		  city = countryData.addCity(country.getId(), realEstate.getCity());
		}
		return super.update(realEstate);
	}

	/**
	 * Retrieves the list of all realEstates stored in the database (with the minimum
	 * of data to be shown in UI)
	 * 
	 * @return The list of realEstates
	 */
	public ArrayList<SimpleRealEstate> getListSimpleRealEstates() {
		Iterator<RealEstate> iterator = this.listAll().iterator();
		ArrayList<SimpleRealEstate> realEstates = new ArrayList<SimpleRealEstate>();
		while (iterator.hasNext()) {
			RealEstate realEstate = iterator.next();
			SimpleRealEstate o = new SimpleRealEstate();
			o.setId(realEstate.getId());
			o.setReference(realEstate.getReference());
			o.setCity(realEstate.getCity());
			o.setAddress(realEstate.getStreet());
			//o.setMobileNumber(Owner.mobile);
			//o.setPhoneNumber(realEstate.getPhoneNumber());
			realEstates.add(o);
		}
		return realEstates;
	}
}
