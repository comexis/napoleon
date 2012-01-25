package eu.comexis.napoleon.shared.model;

import javax.persistence.Id;

public class City {
  @Id
  private Long id;
  private Long name;
  private String postalCode;
  private String country;

  public City() {
    // TODO Auto-generated constructor stub
  }

  public String getCountry() {
    return country;
  }

  public Long getId() {
    return id;
  }

  public Long getName() {
    return name;
  }

  public String getPostCode() {
    return postalCode;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public void setName(Long name) {
    this.name = name;
  }

  public void setPostCode(String postCode) {
    this.postalCode = postCode;
  }

}
