/**
 * 
 */
package eu.comexis.napoleon.shared.model;

import javax.persistence.Id;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.annotation.Unindexed;

/**
 * @author xavier Coopropriété (Condominium)
 */
@Unindexed
public class Condo implements IsSerializable {

  @Id
  private String id;
  private String name;
  @Parent
  private Key<Company> company;

  // Syndicat de copropriété.
  private String homeownerAssociation;

  // Copropriété
  // adresse
  private String street;

  // municipalité
  private String city;

  // code postal
  private String postalCode;

  // pays
  private String country;

  // adresse e-mail
  private String email;
  // n° de téléphone
  private String phoneNumber;
  // n° de téléphone mobile (GSM)
  private String mobilePhoneNumber;
  /**
	 * 
	 */
  public Condo() {
    // empty constructor needed by GWT
  }
  public String getCity() {
    return city;
  }

  public Key<Company> getCompany() {
    return company;
  }

  public String getCountry() {
    return country;
  }

  public String getEmail() {
    return email;
  }

  public String getHomeownerAssociation() {
    return homeownerAssociation;
  }

  public String getId() {
    return id;
  }

  public String getMobilePhoneNumber() {
    return mobilePhoneNumber;
  }

  public String getName() {
    return name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public String getStreet() {
    return street;
  }

  public void setCity(String value) {
    city = value;
  }

  public void setCompany(Key<Company> company) {
    this.company = company;
  }

  public void setCountry(String value) {
    country = value;
  }

  public void setEmail(String value) {
    email = value;
  }

  public void setHomeownerAssociation(String value) {
    homeownerAssociation = value;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setMobilePhoneNumber(String value) {
    mobilePhoneNumber = value;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPhoneNumber(String value) {
    phoneNumber = value;
  }

  public void setPostalCode(String value) {
    postalCode = value;
  }

  public void setStreet(String value) {
    street = value;
  }
}
