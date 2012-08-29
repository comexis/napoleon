package eu.comexis.napoleon.shared.model;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Unindexed;
@Unindexed
public class Company {

  @Id
  private String id;
  @Indexed
  private String name;
  private String address;
  private String email;
  private String url;
  private String telephone;
  private String fax;
  //this field contains encoded logo picture
  private String logo;

  public Company() {
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }

  public String getFax() {
    return fax;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getTelephone() {
    return telephone;
  }

  public String getUrl() {
    return url;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public void setUrl(String url) {
    this.url = url;
  }
  
  public String getLogo() {
    return logo;
  }
  
  public void setLogo(String logo) {
    this.logo = logo;
  }
}
