package eu.comexis.napoleon.shared.model;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class PaymentOwner extends Payment {
  private Float balance;
  private Float previousbalance;
  private Float expense;
  

  public Float getExpense() {
    return expense;
  }
  public void setExpense(Float expense) {
    this.expense = expense;
  }

  private Long nbrPeriod;

  public PaymentOwner() {
    super();
  }
  public Float getBalance() {
    return balance;
  }

  public Long getNbrPeriod() {
    return nbrPeriod;
  }

  public Float getPreviousbalance() {
    return previousbalance;
  }

  public void setBalance(Float balance) {
    this.balance = balance;
  }

  public void setNbrPeriod(Long nbrPeriod) {
    this.nbrPeriod = nbrPeriod;
  }

  public void setPreviousbalance(Float previousbalance) {
    this.previousbalance = previousbalance;
  }

}
