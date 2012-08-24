package eu.comexis.napoleon.shared.model;

import java.util.Date;

import javax.persistence.Id;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Parent;

public class Ownership  implements IsSerializable {

  @Id
  private String id;
  @Parent
  private Key<RealEstate> estate;

  private Key<Owner> ownerKey;

  private Date fromDate;
  private Date toDate;
  
  public Ownership() {
    // TODO Auto-generated constructor stub
  }
  
  public Key<RealEstate> getEstate() {
    return estate;
  }
  
  public Date getFromDate() {
    return fromDate;
  }

  public String getId() {
    return id;
  }

  public Key<Owner> getOwnerKey() {
    return ownerKey;
  }

  public Date getToDate() {
    return toDate;
  }

  public void setEstate(Key<RealEstate> estate) {
    this.estate = estate;
  }

  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setOwnerKey(Key<Owner> ownerKey) {
    this.ownerKey = ownerKey;
  }

  public void setToDate(Date toDate) {
    this.toDate = toDate;
  }

}
