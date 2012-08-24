package eu.comexis.napoleon.shared.model.simple;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

import eu.comexis.napoleon.shared.model.EnablableEntity;
import eu.comexis.napoleon.shared.model.EntityStatus;
import eu.comexis.napoleon.shared.model.Identifiable;

public class SimpleLease implements EnablableEntity, IsSerializable, Identifiable{
  private String id;
  private String realEstateRef;

  private String realEstateId;

  private String academicYear;
  private String tenantName;
  private String tenantId;
  private Date startDate;
  private Date endDate;
  private String ownerId;
  private String ownerName; 
  private EntityStatus entityStatus;

  public SimpleLease() {

  }

  public String getAcademicYear() {
    return academicYear;
  }

  public Date getEndDate() {
    return endDate;
  }

  public String getId() {
    return id;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public String getOwnerName() {
    return ownerName;
  }

  public String getRealEstateId() {
    return realEstateId;
  }

  public String getRealEstateRef() {
    return realEstateRef;
  }

  public Date getStartDate() {
    return startDate;
  }

  public String getTenantId() {
    return tenantId;
  }

  public String getTenantName() {
    return tenantName;
  }

  public void setAcademicYear(String academicYear) {
    this.academicYear = academicYear;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  public void setOwnerName(String ownerName) {
    this.ownerName = ownerName;
  }

  public void setRealEstateId(String realEstateId) {
    this.realEstateId = realEstateId;
  }

  public void setRealEstateRef(String realEstateRef) {
    this.realEstateRef = realEstateRef;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  public void setTenantName(String tenantName) {
    this.tenantName = tenantName;
  }

  public EntityStatus getEntityStatus() {
	return entityStatus;
  }

  public void setEntityStatus(EntityStatus entityStatus) {
	this.entityStatus = entityStatus;
  }  

}
