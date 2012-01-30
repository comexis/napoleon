package eu.comexis.napoleon.shared.model;

import javax.persistence.Id;

public class Company {

  @Id
  private String id;
  private String name;
  private String address;
  private String email;
  private String url;
  private String telephone;
  private String fax;

  public Company() {
  }
  public Client toClient(){
    Client c = new Client();
    c.setId(id);
    c.setName(name);
    c.setAddress(address);
    c.setEmail(email);
    c.setUrl(url);
    c.setTelephone(telephone);
    c.setFax(fax);
    return c;
  }
  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }
}
