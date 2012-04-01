package eu.comexis.napoleon.shared.command.payment;

import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.Payment;

public class GetPaymentResponse<T extends Payment> implements Response {
  private T payment;
  private String errorMsg;

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  public T getPayment() {
    return payment;
  }

  public void setPayment(T payment) {
    this.payment = payment;
  }

}
