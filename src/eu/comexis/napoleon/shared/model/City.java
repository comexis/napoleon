package eu.comexis.napoleon.shared.model;

import javax.persistence.Id;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Parent;

public class City implements IsSerializable{
  @Id
  private String id;
  @Parent
  private Key<Country> country;
  private String name;

  public City() {
    // TODO Auto-generated constructor stub
  }

  public Key<Country> getCountry() {
    return country;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setCountry(Key<Country> country) {
    this.country = country;
  }
  public void setId(String id) {
    this.id = id;
  }
  public void setName(String name) {
    this.name = name;
  }

}
