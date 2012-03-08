package eu.comexis.napoleon.shared.command.payment;

import java.util.List;

import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.Payment;

public class GetAllPaymentResponse<T extends Payment> implements Response {
  private List<T> payments;

  public List<T> getPayment() {
    return payments;
  }

  public void setPayment(List<T> payments) {
    this.payments = payments;
  }
  
}
