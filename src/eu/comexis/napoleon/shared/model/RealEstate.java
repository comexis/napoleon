package eu.comexis.napoleon.shared.model;

import javax.persistence.Id;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Parent;

/**
 * @author xavier Bien immobilier
 */
public class RealEstate implements IsSerializable{

  @Id
  private String id;
  @Parent
  private Key<Company> company;
	private String box;

  private Long number;

  private String city;

  private String country;

  private String street;

  private String dimension;

  private String reference;

  private String square;

  private RealEstateState state;

  private TypeOfRealEstate type;

  private Condo condo;

  public RealEstate() {
	}

  public String getBox() {
    return box;
  }

  public String getCity() {
    return city;
  }

  public Key<Company> getCompany() {
    return company;
  }

  public Condo getCondo() {
    return condo;
  }

  public String getCountry() {
    return country;
  }

  public String getDimension() {
    return dimension;
  }

  public String getId() {
    return id;
  }

  public Long getNumber() {
    return number;
  }

  public String getReference() {
    return reference;
  }

  public String getSquare() {
    return square;
  }

  public RealEstateState getState() {
    return state;
  }

  public String getStreet() {
    return street;
  }

  public TypeOfRealEstate getType() {
    return type;
  }

  public void setBox(String box) {
    this.box = box;
  }

  public void setCity(String city) {
    this.city = city;
  }
	public void setCompany(Key<Company> company) {
    this.company = company;
  }
	public void setCondo(Condo condo) {
    this.condo = condo;
  }
	public void setCountry(String country) {
    this.country = country;
  }
  public void setDimension(String dimension) {
    this.dimension = dimension;
  }
	public void setId(String id) {
    this.id = id;
  }
	public void setNumber(Long number) {
    this.number = number;
  }
	public void setReference(String reference) {
    this.reference = reference;
  }
	public void setSquare(String square) {
    this.square = square;
  }
	public void setState(RealEstateState state) {
    this.state = state;
  }
	public void setStreet(String street) {
    this.street = street;
  }

	public void setType(TypeOfRealEstate type) {
    this.type = type;
  }

}
