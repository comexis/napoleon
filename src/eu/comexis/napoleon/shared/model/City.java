package eu.comexis.napoleon.shared.model;

import javax.persistence.Id;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Parent;

public class City implements IsSerializable{
  @Id
  private String id;
  @Parent
  private Key<Company> company;
  private Long name;

  private String postalCode;

  private String country;

  public City() {
    // TODO Auto-generated constructor stub
  }

  public String getCountry() {
    return country;
  }

  public String getId() {
    return id;
  }

  public Long getName() {
    return name;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setCountry(String country) {
    this.country = country;
  }
  public void setId(String id) {
    this.id = id;
  }
  public void setName(Long name) {
    this.name = name;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

}
