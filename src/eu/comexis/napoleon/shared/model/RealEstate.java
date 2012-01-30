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
	private String boite;
	private String city;
	private String dimension;
	private Long ownerId;
	private String postalCode;
	private String reference;
	private String square;
	private String state;
	private String street;
	private String type;

	public RealEstate() {
		// empty constructor needed by GWT
	}

	public RealEstate(String reference) {
		//
	}

	public String getCity() {
		return city;
	}

	public String getId() {
		return id;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getReference() {
		return reference;
	}

	public String getStreet() {
		return street;
	}
	public void setId(String value) {
    id = value;
  }
	public void setCity(String value) {
		city = value;
	}

	public void setOwnerId(Long id) {
		ownerId = id;
	}
	public void setPostalCode(String value) {
		postalCode = value;
	}

	public void setReference(String value) {
		reference = value;
	}

	public void setStreet(String value) {
		street = value;
	}

}
