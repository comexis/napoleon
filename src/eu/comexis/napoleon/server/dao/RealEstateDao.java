package eu.comexis.napoleon.server.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import com.googlecode.objectify.Key;

import eu.comexis.napoleon.shared.model.City;
import eu.comexis.napoleon.shared.model.Company;
import eu.comexis.napoleon.shared.model.Country;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.simple.SimpleRealEstate;

public class RealEstateDao extends NapoleonDao<RealEstate> {
  public RealEstateDao() {
    super();
    // TODO Auto-generated constructor stub
  }

  public RealEstate create(Key<Company> companyKey) {
    RealEstate realEstate = new RealEstate();
    System.out.println("Set company key " + companyKey.toString());
    realEstate.setCompany(companyKey);
    return realEstate;
  }

  public RealEstate create(String companyId) {
    Key<Company> companyKey = new Key<Company>(Company.class, companyId);
    return create(companyKey);
  }

  /**
   * Retrieves the list of all realEstates stored in the database (with the minimum of data to be
   * shown in UI)
   * 
   * @return The list of realEstates
   */
  public ArrayList<SimpleRealEstate> getListSimpleRealEstates(String companyId) {
    Iterator<RealEstate> iterator = this.listAll(companyId).iterator();
    ArrayList<SimpleRealEstate> realEstates = new ArrayList<SimpleRealEstate>();
    while (iterator.hasNext()) {
      RealEstate realEstate = iterator.next();
      SimpleRealEstate o = new SimpleRealEstate();
      o.setId(realEstate.getId());
      o.setReference(realEstate.getReference());
      o.setCity(realEstate.getCity());
      o.setAddress(realEstate.getStreet());
      // o.setMobileNumber(Owner.mobile);
      // o.setPhoneNumber(realEstate.getPhoneNumber());
      realEstates.add(o);
    }
    return realEstates;
  }

  public RealEstate update(RealEstate realEstate, String companyId) {
    String realEstateId = realEstate.getId();
    CountryDao countryData = new CountryDao();
    Key<Company> companyKey = new Key<Company>(Company.class, companyId);
    if (realEstateId == null || realEstateId.length() == 0) {
      UUID uuid = UUID.randomUUID();
      System.out.println("Creating Uuid " + uuid.toString());
      realEstate.setId(uuid.toString());
      realEstate.setCompany(companyKey);
    }
    // if country does not exist, create it.
    Country country = countryData.getByName(realEstate.getCountry(), companyKey);
    if (country == null) {
      country = countryData.create(companyKey);
      country.setName(realEstate.getCountry());
      countryData.update(country);
    }
    City city = countryData.getCityByName(country.getId(), realEstate.getCity());
    if (city == null) {
      city = countryData.addCity(country.getId(), realEstate.getCity());
    }
    return super.update(realEstate);
  }
}
