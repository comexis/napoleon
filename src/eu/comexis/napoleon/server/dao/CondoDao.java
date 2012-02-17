package eu.comexis.napoleon.server.dao;

import java.util.UUID;

import com.googlecode.objectify.Key;

import eu.comexis.napoleon.shared.model.Association;
import eu.comexis.napoleon.shared.model.City;
import eu.comexis.napoleon.shared.model.Company;
import eu.comexis.napoleon.shared.model.Condo;
import eu.comexis.napoleon.shared.model.Country;
import eu.comexis.napoleon.shared.model.Owner;

public class CondoDao extends NapoleonDao<Condo>{

  public CondoDao() {
    super();
  }
  public Condo create(Key<Company> companyKey) {
    Condo cdo = new Condo();
    System.out.println("Set company key " + companyKey.toString());
    cdo.setCompany(companyKey);
    return cdo;
  }
  public Condo create(String companyId) {
    Key<Company> companyKey = new Key<Company>(Company.class, companyId);
    return create(companyKey);
  }
  @Override
  public Condo update(Condo cdo){
    if (cdo.getCompany() != null){
      return update(cdo,cdo.getCompany());
    }else{
      // log error
      LOG.fatal("Parent Company is not set, cannot save condo");
      return null;
    }
  }
  public Condo update(Condo cdo,String companyId){
    Key<Company> companyKey = new Key<Company>(Company.class, companyId);
    return update(cdo,companyKey);
  }
  public Condo update(Condo cdo,Key<Company> companyKey){
    String condoId = cdo.getId();
    if (condoId == null || condoId.length() == 0) {
      UUID uuid = UUID.randomUUID();
      System.out.println("Creating Uuid " + uuid.toString());
      cdo.setId(uuid.toString());
      cdo.setCompany(companyKey);
    }
    CountryDao countryData = new CountryDao();
    // if country does not exist, create it.
    Country country = countryData.getByName(cdo.getCountry(), companyKey);
    if (country == null) {
      country = countryData.create(companyKey);
      country.setName(cdo.getCountry());
      countryData.update(country);
    }
    City city = countryData.getCityByFullName(country.getId(), cdo.getCity(),cdo.getPostalCode());
    if (city == null) {
      city = countryData.addCity(country.getId(), cdo.getCity(),cdo.getPostalCode());
    }
    return super.update(cdo);
  }
  @Override
  public Condo getById(String cdoId,String companyId){
    Condo cdo = super.getById(cdoId, companyId);
    // enrich the object with the corresponding homeownerAssociation
    Association assoc = ofy().get(cdo.getHomeownerAssociationKey());
    cdo.setHomeownerAssociation(assoc);
    return cdo;
  }
}
