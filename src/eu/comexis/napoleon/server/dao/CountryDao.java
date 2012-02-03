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

public class CountryDao extends NapoleonDao<Country>{

  public CountryDao(String companyId) {
    super(companyId);
    // TODO Auto-generated constructor stub
  }
  public Country create() {
    Country country = new Country();
    country.setCompany(companyKey);
    return country;
  }
  @Override
  public Country update(Country country) {
    String countryId = country.getId();
    
    if (countryId == null || countryId.length() == 0){
      UUID uuid = UUID.randomUUID();
      country.setId(uuid.toString());
    }
    return super.update(country);
  }
  public ArrayList<Country> getList(){
    Iterator<Country> iterator = this.listAll().iterator();
    ArrayList<Country> countries = new ArrayList<Country>();
    while (iterator.hasNext()) {
      Country c = iterator.next();
      countries.add(c);
    }
    return countries;
  }
  public City addCity(String countryId, String cityName){
    try{
      City city = new City();
      Key<Country> countryKey = new Key<Country>(Country.class,countryId);
      UUID uuid = UUID.randomUUID();
      city.setId(uuid.toString());
      city.setCountry(countryKey);
      city.setName(cityName);
      ofy().put(city);
      return city;
    }catch (Exception e){
      e.printStackTrace();
      return null;
    }
  }
  public Country getByName(String name){
    try{
      Query<Country> q = ofy().query(Country.class);
      q.ancestor(companyKey);
      Country c = q.filter("name", name).get();
      return c;
    }catch(Exception e){
      return null;
    }
  }
  public City getCityByName(String countryId, String name){
    try{
      Key<Country> countryKey = new Key<Country>(Country.class,countryId);
      Query<City> q = ofy().query(City.class);
      q.ancestor(countryKey);
      q.filter("name", name);
      City c = q.get();
      return c;
    }catch(Exception e){
      return null;
    }
  }
  public ArrayList<String> getListCities(String countryId){
    Key<Country> countryKey = new Key<Country>(Country.class,countryId);
    Query<City> q = ofy().query(City.class);
    q.ancestor(countryKey);
    Iterator<City> iterator = q.list().iterator();
    ArrayList<String> cities = new ArrayList<String>();
    while (iterator.hasNext()) {
      City c = iterator.next();
      cities.add(c.getName());
    }
    return cities;
  }
}
