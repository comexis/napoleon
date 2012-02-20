package eu.comexis.napoleon.shared.model.simple;

import com.google.gwt.user.client.rpc.IsSerializable;

import eu.comexis.napoleon.shared.model.Identifiable;


public class SimpleOwner implements IsSerializable, Identifiable{
	
	private String id;
	private String clientId;
	private String name;
	private String postalCode;
	private String address;
	private String city;
	private String phoneNumber;
	private String mobile;
	
	public SimpleOwner() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String telephone) {
		this.phoneNumber = telephone;
	}

	public String getMobileNumber() {
		return mobile;
	}

	public void setMobileNumber(String mobile) {
		this.mobile = mobile;
	}
	
	
	
	

}
