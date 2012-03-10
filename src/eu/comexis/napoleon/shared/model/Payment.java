package eu.comexis.napoleon.shared.model;

import java.util.Date;

import javax.persistence.Id;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.NotSaved;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.annotation.Unindexed;

@Entity
@Unindexed
public class Payment implements IsSerializable, Identifiable  {

  @Id
  private String id;
  @Parent
  private Key<Lease> leaseKey;
  @Indexed
  private Date paymentDate;
  private String account;
  private Float amount;
  @Indexed
  private Date periodStartDate;
  @Indexed
  private Date periodEndDate;
  @NotSaved
  private String leaseId;
  @NotSaved
  private String estateId;

  public Payment() {
    // TODO Auto-generated constructor stub
  }

  public String getAccount() {
    return account;
  }

  public Float getAmount() {
    return amount;
  }

  public String getEstateId() {
    return estateId;
  }

  public String getId() {
    return id;
  }

  public String getLeaseId() {
    return leaseId;
  }

  public Key<Lease> getLeaseKey() {
    return leaseKey;
  }

  public Date getPaymentDate() {
    return paymentDate;
  }

  public Date getPeriodEndDate() {
    return periodEndDate;
  }

  public Date getPeriodStartDate() {
    return periodStartDate;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public void setAmount(Float amount) {
    this.amount = amount;
  }

  public void setEstateId(String estateId) {
    this.estateId = estateId;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setLeaseId(String leaseId) {
    this.leaseId = leaseId;
  }

  public void setLeaseKey(Key<Lease> leaseKey) {
    this.leaseKey = leaseKey;
  }

  public void setPaymentDate(Date paymentDate) {
    this.paymentDate = paymentDate;
  }

  public void setPeriodEndDate(Date periodEndDate) {
    this.periodEndDate = periodEndDate;
  }

  public void setPeriodStartDate(Date periodStartDate) {
    this.periodStartDate = periodStartDate;
  }

}
