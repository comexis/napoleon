package eu.comexis.napoleon.shared.model.simple;

import com.google.gwt.user.client.rpc.IsSerializable;

import eu.comexis.napoleon.shared.model.EnablableEntity;
import eu.comexis.napoleon.shared.model.EntityStatus;
import eu.comexis.napoleon.shared.model.Identifiable;


public class SimpleOwner  implements EnablableEntity, IsSerializable, Identifiable{

  private String id;
  private String clientId;
  private String name;
  private String postalCode;
  private String address;
  private String city;
  private String phoneNumber;
  private String mobile;
  private String Email;
  private EntityStatus entityStatus;
  
  public SimpleOwner() {
  }

  public String getAddress() {
    return address;
  }

  public String getCity() {
    return city;
  }

  public String getClientId() {
    return clientId;
  }

  public String getEmail() {
    return Email;
  }

  public String getId() {
    return id;
  }

  public String getMobileNumber() {
    return mobile;
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

  public void setAddress(String address) {
    this.address = address;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public void setEmail(String email) {
    Email = email;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setMobileNumber(String mobile) {
    this.mobile = mobile;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPhoneNumber(String telephone) {
    this.phoneNumber = telephone;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }
  
  public EntityStatus getEntityStatus() {
	return entityStatus;
  }

  public void setEntityStatus(EntityStatus entityStatus) {
	this.entityStatus = entityStatus;
  }  

}