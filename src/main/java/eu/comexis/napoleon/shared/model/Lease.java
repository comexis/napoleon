/**
 * 
 */
package eu.comexis.napoleon.shared.model;

// import java.util.Calendar;// Not supported by GWT :-(
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Id;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.NotSaved;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.annotation.Unindexed;

import eu.comexis.napoleon.shared.model.simple.SimpleRealEstate;
import eu.comexis.napoleon.shared.model.simple.SimpleTenant;

/**
 * @author xavier Location
 */
@Unindexed
public class Lease implements EnablableEntity, IsSerializable , Identifiable,HasFiles{

  @Id
  private String id;
  @Parent
  private Key<RealEstate> realEstateKey;
  @Indexed
  private Date startDate;
  @Indexed
  private Date endDate;
  @Indexed
  private Key<Tenant> tenantKey;
  private Float rent;
  private Float fee;
  @NotSaved
  private Float feeFromOwner;
  private FeeUnit unit;

  private Date entranceDate;

  private Date leaveDate;
  private Date eleDate;

  private Date elsDate;

  private Float securityDeposit;

  private Date depositDate;

  private Boolean depositInCash;
  private Boolean depositAgency;
  private String iban;
  private String bic;
  private String ibanOwner;
  private String bicOwner;
  private Boolean hasFurnituresRental;
  private Boolean hasFurnituresWithContract;
  private Float furnituresAnnualAmount;
  private Date furnituresDate;
  private Boolean furnituresPaymentOK;
  private TypeOfRent type;
  private Float serviceCharges;
  @Indexed
  private String bookkeepingReference;
  private Long duration;
  private AmountOfTimeUnit durationUnit;
  @Indexed
  private String academicYear;
  private String cooccupant;
  @NotSaved
  private SimpleRealEstate realEstate;
  @NotSaved
  private SimpleTenant tenant;
  @Embedded
  private ArrayList<FileDescriptor> files;
  
  private EntityStatus entityStatus;
  
  /**
	 * 
	 */
  public Lease() {
    files = new ArrayList<FileDescriptor>();
  }
  @Override
  public void addFile(FileDescriptor file) {
    files.add(file);
  }
  public String getAcademicYear() {
    return academicYear;
  }
  public String getBic() {
    return bic;
  }
  public String getBookkeepingReference() {
    return bookkeepingReference;
  }   
  public String getCooccupant() {
    return cooccupant;
  }

  public Date getDepositDate() {
    return depositDate;
  }

  public Boolean getDepositInCash() {
    return depositInCash;
  }

  public Long getDuration() {
    return duration;
  }

  public AmountOfTimeUnit getDurationUnit() {
    return durationUnit;
  }

  public Date getEleDate() {
    return eleDate;
  }

  public Date getElsDate() {
    return elsDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public Date getEntranceDate() {
    return entranceDate;
  }

  public Float getFee() {
    return fee;
  }

  public Float getFeeFromOwner() {
    return feeFromOwner;
  }

  @Override
  public ArrayList<FileDescriptor> getFiles() {
    return files;
  }

  public Float getFurnituresAnnualAmount() {
    return furnituresAnnualAmount;
  }

  public Date getFurnituresDate() {
    return furnituresDate;
  }

  public Boolean getFurnituresPaymentOK() {
    return furnituresPaymentOK;
  }

  public Boolean getHasFurnituresRental() {
    return hasFurnituresRental;
  }

  public Boolean getHasFurnituresWithContract() {
    return hasFurnituresWithContract;
  }

  public String getIban() {
    return iban;
  }

  public String getId() {
    return id;
  }

  public Date getLeaveDate() {
    return leaveDate;
  }

  public SimpleRealEstate getRealEstate() {
    return realEstate;
  }

  public Key<RealEstate> getRealEstateKey() {
    return realEstateKey;
  }

  public Float getRent() {
    return rent;
  }

  public Float getSecurityDeposit() {
    return securityDeposit;
  }

  public Float getServiceCharges() {
    return serviceCharges;
  }

  public Date getStartDate() {
    return startDate;
  }

  public SimpleTenant getTenant() {
    return tenant;
  }

  public Key<Tenant> getTenantKey() {
    return tenantKey;
  }

  public TypeOfRent getType() {
    return type;
  }

  public FeeUnit getUnit() {
    return unit;
  }

  @Override
  public void removeFile(FileDescriptor file) {
    files.remove(file);
  }

  public void setAcademicYear(String academicYear) {
    this.academicYear = academicYear;
  }

  public void setBic(String bic) {
    this.bic = bic;
  }

  public void setBookkeepingReference(String bookkeepingReference) {
    this.bookkeepingReference = bookkeepingReference;
  }  
  public void setCooccupant(String cooccupant) {
    this.cooccupant = cooccupant;
  }

  public void setDepositDate(Date depositDate) {
    this.depositDate = depositDate;
  }

  public void setDepositInCash(Boolean depositInCash) {
    this.depositInCash = depositInCash;
  }

  public void setDuration(int duration, AmountOfTimeUnit unit) {
    this.duration = new Long(duration);
    this.durationUnit = unit;
  }

  public void setDurationUnit(AmountOfTimeUnit durationUnit) {
    this.durationUnit = durationUnit;
  }

  public void setEleDate(Date eleDate) {
    this.eleDate = eleDate;
  }

  public void setElsDate(Date elsDate) {
    this.elsDate = elsDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public void setEntranceDate(Date entranceDate) {
    this.entranceDate = entranceDate;
  }

  public void setFee(Float fee) {
    this.fee = fee;
  }

  public void setFeeFromOwner(Float feeFromOwner) {
    this.feeFromOwner = feeFromOwner;
  }

  public void setFiles(ArrayList<FileDescriptor> files) {
    this.files = files;
  }

  public void setFurnituresAnnualAmount(Float furnituresAnnualAmount) {
    this.furnituresAnnualAmount = furnituresAnnualAmount;
  }

  public void setFurnituresDate(Date furnituresDate) {
    this.furnituresDate = furnituresDate;
  }

  public void setFurnituresPaymentOK(Boolean furnituresPaymentOK) {
    this.furnituresPaymentOK = furnituresPaymentOK;
  }

  public void setHasFurnituresRental(Boolean hasFurnituresRental) {
    this.hasFurnituresRental = hasFurnituresRental;
  }

  public void setHasFurnituresWithContract(Boolean hasFurnituresWithContract) {
    this.hasFurnituresWithContract = hasFurnituresWithContract;
  }

  public void setIban(String iban) {
    this.iban = iban;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setLeaveDate(Date leaveDate) {
    this.leaveDate = leaveDate;
  }

  public void setRealEstate(SimpleRealEstate realEstate) {
    this.realEstate = realEstate;
  }

  public void setRealEstateKey(Key<RealEstate> realEstateKey) {
    this.realEstateKey = realEstateKey;
  }

  public void setRent(float rent) {
    this.rent = rent;
  }

  public void setSecurityDeposit(Float securityDeposit) {
    this.securityDeposit = securityDeposit;
  }

  public void setServiceCharges(Float serviceCharges) {
    this.serviceCharges = serviceCharges;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public void setTenant(SimpleTenant tenant) {
    this.tenant = tenant;
  }

  public void setTenantKey(Key<Tenant> tenantKey) {
    this.tenantKey = tenantKey;
  }

  public void setType(TypeOfRent type) {
    this.type = type;
  }

  public void setUnit(FeeUnit unit) {
    this.unit = unit;
  }
  public Boolean getDepositAgency() {
	return depositAgency;
  }
  public void setDepositAgency(Boolean depositAgency) {
	this.depositAgency = depositAgency;
  }
  public String getIbanOwner() {
	return ibanOwner;
  }
  public void setIbanOwner(String ibanOwner) {
	this.ibanOwner = ibanOwner;
  }
  public String getBicOwner() {
	return bicOwner;
  }
  public void setBicOwner(String bicOwner) {
	this.bicOwner = bicOwner;
  }
  
  public EntityStatus getEntityStatus() {
	return entityStatus;
  }

  public void setEntityStatus(EntityStatus entityStatus) {
	this.entityStatus = entityStatus;
  }  
}
