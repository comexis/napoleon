package eu.comexis.napoleon.server.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Query;

import eu.comexis.napoleon.shared.model.City;
import eu.comexis.napoleon.shared.model.Company;
import eu.comexis.napoleon.shared.model.Condo;
import eu.comexis.napoleon.shared.model.Country;
import eu.comexis.napoleon.shared.model.Owner;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.Tenant;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;
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
  @Override
  public RealEstate update(RealEstate realEstate) {
    if (realEstate.getCompany() != null){
      return update(realEstate,realEstate.getCompany());
    }else{
      // log error
      LOG.fatal("Parent Company is not set, cannot save realEstate");
      return null;
    }
  }
  public RealEstate update(RealEstate realEstate, String companyId) {
    Key<Company> companyKey = new Key<Company>(Company.class, companyId);
    return update(realEstate,companyKey);
  }
  public RealEstate update(RealEstate realEstate, Key<Company> companyKey) {
    String realEstateId = realEstate.getId();
    CountryDao countryData = new CountryDao();
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
    City city = countryData.getCityByFullName(country.getId(), realEstate.getCity(),realEstate.getPostalCode());
    // if city does not exist, create it.
    if (city == null) {
      city = countryData.addCity(country.getId(), realEstate.getCity(),realEstate.getPostalCode());
    }
    if (!realEstate.getSquare().isEmpty()){
      ArrayList<String> allSquares = city.getSquareList();
      if (allSquares != null){
        if (!allSquares.contains(realEstate.getSquare())){
          allSquares.add(realEstate.getSquare());
          city.setSquareList(allSquares);
          countryData.UpdateCity(city);
        }
      }else{
        allSquares = new ArrayList<String>();
        allSquares.add(realEstate.getSquare());
        city.setSquareList(allSquares);
        countryData.UpdateCity(city);
      }
    }
    return super.update(realEstate);
  }
  public Condo getCondo(RealEstate realEstate){
    Key<Condo> condoKey = realEstate.getCondo();
    if (condoKey!=null){
      Condo cdo = ofy().get(condoKey);
      return cdo;
    }
    return null;
  }
  public void setCondo(RealEstate realEstate,Condo cdo){
    Key<Condo> cdoKey = new Key<Condo>(cdo.getCompany(), Condo.class, cdo.getId());
    realEstate.setCondo(cdoKey);
  }
  public void deleteCondo(RealEstate realEstate){
    realEstate.setCondo(null);
  }
  public void setOwner(RealEstate realEstate,Owner owner){
    Key<Owner> ownerKey = new Key<Owner>(owner.getCompany(),Owner.class, owner.getId());
    realEstate.setOwner(ownerKey);
  }
  public void setOwner(RealEstate realEstate,String ownerId,String companyId){
    Key<Company> companyKey = new Key<Company>(Company.class, companyId);
    Key<Owner> ownerKey = new Key<Owner>(companyKey,Owner.class, ownerId);
    realEstate.setOwner(ownerKey);
  }
  public SimpleOwner getOwner(RealEstate realEstate){
    SimpleOwner o = new SimpleOwner();
    Owner own = ofy().get(realEstate.getOwner());
    if (own!= null){
      o.setId(own.getId());
      o.setName(own.getLastName());
      o.setMobileNumber(own.getMobilePhoneNumber());
      o.setPhoneNumber(own.getPhoneNumber());
    }
    return o;
  }
  public ArrayList<SimpleRealEstate> getListSimpleRealEstatesForOwner(String companyId,String ownerId) {
    try {
      Key<Company> companyKey = new Key<Company>(Company.class, companyId);
      Key<Owner> ownerKey = new Key<Owner>(companyKey,Owner.class, ownerId);
      Query<RealEstate> q = ofy().query(RealEstate.class);
      q.ancestor(companyKey);
      ArrayList<SimpleRealEstate> realEstates = new ArrayList<SimpleRealEstate>();
      for(RealEstate e:q.filter("owner", ownerKey).list()){
        SimpleRealEstate se = new SimpleRealEstate();
        se.setId(e.getId());
        se.setReference(e.getReference());
        se.setCity(e.getCity());
        se.setAddress(e.getStreet());
        realEstates.add(se);
      }
      return realEstates;
    } catch (Exception e) {
      return null;
    }
  }
}
