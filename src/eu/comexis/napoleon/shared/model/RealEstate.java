package eu.comexis.napoleon.shared.model;

import javax.persistence.Id;

/**
 * @author xavier Bien immobilier
 */
public class RealEstate {

	private String boite;
	private String city;
	private Long condoId;
	private String dimension;

	@Id
	private Long id;
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

	public Long getCondoId() {
		return condoId;
	}

	public Long getId() {
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
	public void setCity(String value) {
		city = value;
	}
	public void setCondoId(Long id) {
		condoId = id;
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
