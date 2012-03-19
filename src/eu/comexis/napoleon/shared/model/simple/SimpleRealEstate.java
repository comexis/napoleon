package eu.comexis.napoleon.shared.model.simple;

import com.google.gwt.user.client.rpc.IsSerializable;

import eu.comexis.napoleon.shared.model.Identifiable;

public class SimpleRealEstate implements IsSerializable, Identifiable {
  
  private String address;
  private String city;
  private String clientId;
  private String id;
  private String mobile;
  private String owner;
  private String ownerId;
  private String phoneNumber;
  private String postalCode;
  private String reference;

  public String getAddress() {
    return address;
  }

  public String getCity() {
    return city;
  }

  public String getClientId() {
    return clientId;
  }

  public String getId() {
    return id;
  }

  public String getMobile() {
    return mobile;
  }

  public String getOwner() {
    return owner;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public String getReference() {
    return reference;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  
  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }
  
  public void setReference(String reference) {
    this.reference = reference;
  }
}
