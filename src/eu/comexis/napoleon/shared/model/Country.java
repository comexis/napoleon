package eu.comexis.napoleon.shared.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.persistence.Id;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.NotSaved;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.annotation.Unindexed;

@Unindexed
public class Country implements IsSerializable {
  @Id
  private String id;
  @Parent
  private Key<Company> company;
  @Indexed
  private String name;
  @NotSaved
  private List<City> cities;

  public Country() {
    // TODO Auto-generated constructor stub
  }

  public List<City> getCities() {
    return cities;
  }

  public Key<Company> getCompany() {
    return company;
  }

  public String getId() {
    return id;
  }

  public List<City> getListCitiesByPostalCode(String postalCode) {
    List<City> lst = new ArrayList<City>();
    for(City c:this.cities){
      if (c.getPostalCode()!=null){
        if (c.getPostalCode().equals(postalCode)){
          lst.add(c);
        }
      }
    }
    return lst;
  }

  public List<String> getListPostalCode() {
    List<String> lst = new ArrayList<String>();
    for(City c:this.cities){
        lst.add(c.getPostalCode());
    }
    return lst;
  }

  public String getName() {
    return name;
  }

  public void setCities(List<City> cities) {
    this.cities = cities;
  }

  public void setCompany(Key<Company> company) {
    this.company = company;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

}
