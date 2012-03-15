package eu.comexis.napoleon.shared.model.simple;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

import eu.comexis.napoleon.shared.model.Identifiable;

public class PaymentListItem implements IsSerializable, Identifiable {

  private String paymentTenantId;
  private String paymentOwnerId;
  private Date paymentTenantDate;
  private Date fromDate;
  private Date toDate;
  private Float rent;
  private Float fee;
  private Float expenses;
  private Float toBePaidToOwner;
  private Float balance;
  private Float paidToOwner;
  private Date paymentOwnerDate;

  public PaymentListItem() {
    // TODO Auto-generated constructor stub
  }

  public Float getBalance() {
    return balance;
  }

  public Float getExpenses() {
    return expenses;
  }

  public Float getFee() {
    return fee;
  }

  public Date getFromDate() {
    return fromDate;
  }

  public Float getPaidToOwner() {
    return paidToOwner;
  }

  public Date getPaymentOwnerDate() {
    return paymentOwnerDate;
  }

  public String getPaymentOwnerId() {
    return paymentOwnerId;
  }

  public Date getPaymentTenantDate() {
    return paymentTenantDate;
  }

  public String getPaymentTenantId() {
    return paymentTenantId;
  }

  public Float getRent() {
    return rent;
  }

  public Float getToBePaidToOwner() {
    return toBePaidToOwner;
  }

  public Date getToDate() {
    return toDate;
  }

  public void setBalance(Float balance) {
    this.balance = balance;
  }

  public void setExpenses(Float expenses) {
    this.expenses = expenses;
  }

  public void setFee(Float fee) {
    this.fee = fee;
  }

  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }

  public void setPaidToOwner(Float paidToOwner) {
    this.paidToOwner = paidToOwner;
  }

  public void setPaymentOwnerDate(Date paymentOwnerDate) {
    this.paymentOwnerDate = paymentOwnerDate;
  }

  public void setPaymentOwnerId(String paymentOwnerId) {
    this.paymentOwnerId = paymentOwnerId;
  }

  public void setPaymentTenantDate(Date paymentTenantDate) {
    this.paymentTenantDate = paymentTenantDate;
  }

  public void setPaymentTenantId(String paymentTenantId) {
    this.paymentTenantId = paymentTenantId;
  }

  public void setRent(Float rent) {
    this.rent = rent;
  }

  public void setToBePaidToOwner(Float toBePaidToOwner) {
    this.toBePaidToOwner = toBePaidToOwner;
  }

  public void setToDate(Date toDate) {
    this.toDate = toDate;
  }

  @Override
  public String getId() {
    // TODO Auto-generated method stub
    return this.paymentTenantId;
  }
}
