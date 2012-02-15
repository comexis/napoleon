package eu.comexis.napoleon.server.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Query;

import eu.comexis.napoleon.shared.model.City;
import eu.comexis.napoleon.shared.model.Company;
import eu.comexis.napoleon.shared.model.Country;

public class CountryDao extends NapoleonDao<Country> {

  public CountryDao() {
    super();
    // TODO Auto-generated constructor stub
  }

  public City addCity(String countryId, String cityName, String postalCode) {
    try {
      City city = new City();
      Key<Country> countryKey = new Key<Country>(Country.class, countryId);
      UUID uuid = UUID.randomUUID();
      city.setId(uuid.toString());
      city.setCountry(countryKey);
      city.setPostalCode(postalCode);
      city.setName(cityName);
      ofy().put(city);
      return city;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  public void UpdateCity(City c){
    ofy().put(c);
  }
  public Country create(Key<Company> companyKey) {
    Country country = new Country();
    country.setCompany(companyKey);
    return country;
  }

  public Country create(String companyId) {
    Key<Company> companyKey = new Key<Company>(Company.class, companyId);
    return create(companyKey);
  }

  public Country getByName(String name, Key<Company> companyKey) {
    try {
      Query<Country> q = ofy().query(Country.class);
      q.ancestor(companyKey);
      Country c = q.filter("name", name).get();
      return c;
    } catch (Exception e) {
      return null;
    }
  }

  public Country getByName(String name, String companyId) {
    Key<Company> companyKey = new Key<Company>(Company.class, companyId);
    return getByName(name, companyKey);
  }
  public City getCityByFullName(String countryId, String name,String postalCode) {
    try {
      Key<Country> countryKey = new Key<Country>(Country.class, countryId);
      Query<City> q = ofy().query(City.class);
      q.ancestor(countryKey);
      q.filter("postalCode", postalCode).filter("name", name);
      City c = q.get();
      return c;
    } catch (Exception e) {
      return null;
    }
  }
  public City getCityByName(String countryId, String name) {
    try {
      Key<Country> countryKey = new Key<Country>(Country.class, countryId);
      Query<City> q = ofy().query(City.class);
      q.ancestor(countryKey);
      q.filter("name", name);
      City c = q.get();
      return c;
    } catch (Exception e) {
      return null;
    }
  }

  public ArrayList<Country> getList(String companyId) {
    Iterator<Country> iterator = this.listAll(companyId).iterator();
    ArrayList<Country> countries = new ArrayList<Country>();
    while (iterator.hasNext()) {
      Country c = iterator.next();
      countries.add(c);
    }
    return countries;
  }
  public List<City> getListCitiesByCountryName(String countryName,String companyId) {
    Country cnty = getByName(countryName,companyId);
    Key<Country> countryKey = new Key<Country>(Country.class, cnty.getId());
    Query<City> q = ofy().query(City.class);
    q.ancestor(countryKey);
    return q.list();
  }
  public List<City> getListCities(String countryId) {
    Key<Country> countryKey = new Key<Country>(Country.class, countryId);
    Query<City> q = ofy().query(City.class);
    q.ancestor(countryKey);
    return q.list();
  }
  @Override
  public Country update(Country country) {
    String countryId = country.getId();
    if (countryId == null || countryId.length() == 0) {
      UUID uuid = UUID.randomUUID();
      country.setId(uuid.toString());
    }
    return super.update(country);
  }
  public Country getFullCountryById(String id,String companyId){
    Country cnty = this.getById(id, companyId);
    cnty.setCities(this.getListCities(cnty.getId()));
    return cnty;
  }
  public Country getFullCountryByName(String name,String companyId){
    LOG.info("Get country with name: " + name + " for company " + companyId);
    Country cnty = this.getByName(name, companyId);
    if (cnty!=null){
      LOG.info("Country found: " + cnty.getId());
      cnty.setCities(this.getListCities(cnty.getId()));
    }
    return cnty;
  }
}
