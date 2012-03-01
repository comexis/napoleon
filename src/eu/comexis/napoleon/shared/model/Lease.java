/**
 * 
 */
package eu.comexis.napoleon.shared.model;

//import java.util.Calendar;// Not supported by GWT :-(
import java.text.SimpleDateFormat;
import java.util.Date;

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
public class Lease implements IsSerializable {

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
  private float rent;
  private Date entranceDate;
  private Date leaveDate;
  private Date eleDate;
  private Date elsDate;
  private float securityDeposit;
  private Date depositDate;
  private Boolean depositInCash;
  private String iban;
  private String bic;
  private Boolean hasFurnituresRental;
  private Boolean hasFurnituresWithContract;
  private Float furnituresAnnualAmount;
  private Date furnituresDate;
  private Boolean furnituresPaymentOK;
  private TypeOfRent type;
  private float serviceCharges;
  private String bookkeepingReference;
  private Long duration;
  private AmountOfTimeUnit durationUnit;
  private String academicYear;
  @NotSaved
  private SimpleRealEstate realEstate;

  @NotSaved
  private SimpleTenant tenant;

  /**
	 * 
	 */
  public Lease() {
    // TODO Auto-generated constructor stub
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

  public float getRent() {
    return rent;
  }

  public float getSecurityDeposit() {
    return securityDeposit;
  }

  public float getServiceCharges() {
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

  public void setAcademicYear(String academicYear) {
    this.academicYear = academicYear;
  }

  public void setBic(String bic) {
    this.bic = bic;
  }

  public void setBookkeepingReference(String bookkeepingReference) {
    this.bookkeepingReference = bookkeepingReference;
  }

  public void setDepositDate(Date depositDate) {
    this.depositDate = depositDate;
  }

  public void setDepositInCash(Boolean depositInCash) {
    this.depositInCash = depositInCash;
  }

/*Not supported by GWT :-(
  public void setDuration() {
    if (this.startDate != null && this.endDate != null) {
      Calendar cal1 = Calendar.getInstance();
      cal1.setTime(this.startDate);
      Calendar cal2 = Calendar.getInstance();
      cal2.setTime(this.endDate);
      this.duration = (cal2.getTimeInMillis() - cal1.getTimeInMillis()) / (24 * 60 * 60 * 1000);
      this.durationUnit = AmountOfTimeUnit.DAY;
    }
  }
*/
  public void setDuration(int duration, AmountOfTimeUnit unit) {
    this.duration = new Long(duration);
    this.durationUnit = unit;
    /* Not supported by GWT :-(
    if (this.startDate != null) {
      Calendar cal = Calendar.getInstance();
      cal.setTime(this.startDate);
      if (unit.equals(AmountOfTimeUnit.MONTH)) {
        cal.add(Calendar.MONTH, duration);
      } else if (unit.equals(AmountOfTimeUnit.DAY)) {
        cal.add(Calendar.DAY_OF_MONTH, duration);
      } else {
        cal.add(Calendar.MONTH, duration);
      }
      this.endDate = cal.getTime();
    }
    */
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
    /* Not supported by GWT :-(
    // if the duration is given, compute the end date
    if (this.duration != null) {
      int duration = this.duration.intValue();
      AmountOfTimeUnit unit = this.durationUnit;
      Calendar cal = Calendar.getInstance();
      cal.setTime(this.startDate);
      if (unit.equals(AmountOfTimeUnit.MONTH)) {
        cal.add(Calendar.MONTH, -duration);
      } else {
        cal.add(Calendar.DAY_OF_MONTH, -duration);
      }
      this.startDate = cal.getTime();
    }
    // if the end date is given, compute the duration in days
    if (this.startDate != null) {
      if (this.endDate.before(this.startDate)) {
        this.endDate = this.startDate;
        this.duration = 0L;
      } else {
        setDuration();
      }
    }
    */
  }

  public void setEntranceDate(Date entranceDate) {
    this.entranceDate = entranceDate;
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

  public void setSecurityDeposit(float securityDeposit) {
    this.securityDeposit = securityDeposit;
  }

  public void setServiceCharges(float serviceCharges) {
    this.serviceCharges = serviceCharges;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
    /* Not supported by GWT :-(
    // if the duration is given, compute the end date
    if (this.duration != null) {
      int duration = this.duration.intValue();
      AmountOfTimeUnit unit = this.durationUnit;
      Calendar cal = Calendar.getInstance();
      cal.setTime(this.startDate);
      if (unit.equals(AmountOfTimeUnit.MONTH)) {
        cal.add(Calendar.MONTH, duration);
      } else {
        cal.add(Calendar.DAY_OF_MONTH, duration);
      }
      this.endDate = cal.getTime();
    }
    // if the end date is given, compute the duration in days
    if (this.endDate != null) {
      if (this.endDate.before(this.startDate)) {
        this.endDate = this.startDate;
        this.duration = 0L;
      } else {
        setDuration();
      }
    }*/
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
}
