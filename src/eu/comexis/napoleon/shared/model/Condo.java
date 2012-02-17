/**
 * 
 */
package eu.comexis.napoleon.shared.model;

import javax.persistence.Id;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.NotSaved;
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
  @Indexed
  private Key<Association> homeownerAssociationKey;
  @NotSaved
  private Association homeownerAssociation;

  // Copropriété
  // adresse
  private String street;
  // municipalité
  private String city;

  private String square;

  // code postal
  private String postalCode;

  // pays
  private String country;

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

  public Association getHomeownerAssociation() {
    return homeownerAssociation;
  }

  public Key<Association> getHomeownerAssociationKey() {
    return homeownerAssociationKey;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public String getSquare() {
    return square;
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

  public void setHomeownerAssociation(Association homeownerAssociation) {
    this.homeownerAssociation = homeownerAssociation;
  }

  public void setHomeownerAssociationKey(Key<Association> value) {
    homeownerAssociationKey = value;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPostalCode(String value) {
    postalCode = value;
  }

  public void setSquare(String square) {
    this.square = square;
  }

  public void setStreet(String value) {
    street = value;
  }
}
