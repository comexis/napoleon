package eu.comexis.napoleon.shared.model.simple;

import com.google.gwt.user.client.rpc.IsSerializable;

import eu.comexis.napoleon.shared.model.Identifiable;

public class SimpleRealEstate implements IsSerializable, Identifiable {
  private String id;
  private String clientId;
  private String reference;
  private String address;
  private String city;
  private String owner;
  private String phoneNumber;
  private String mobile;

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

  public String getPhoneNumber() {
    return phoneNumber;
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

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }
}
