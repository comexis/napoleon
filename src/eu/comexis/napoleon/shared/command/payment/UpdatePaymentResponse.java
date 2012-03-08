package eu.comexis.napoleon.shared.command.payment;

import java.util.List;

import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.Payment;
import eu.comexis.napoleon.shared.model.PaymentOwner;
import eu.comexis.napoleon.shared.model.PaymentTenant;
import eu.comexis.napoleon.shared.model.simple.PaymentListItem;

public class UpdatePaymentResponse<T extends Payment> implements Response{
  private T payment;

  public T getPayment() {
    return payment;
  }

  public void setPayment(T payment) {
    this.payment = payment;
  }

}
