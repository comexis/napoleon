package eu.comexis.napoleon.shared.command.payment;

import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.client.rpc.PaymentServiceAsync;
import eu.comexis.napoleon.shared.command.Action;
import eu.comexis.napoleon.shared.model.Payment;
import eu.comexis.napoleon.shared.model.PaymentOwner;
import eu.comexis.napoleon.shared.model.PaymentTenant;

public class UpdatePaymentCommand<T extends Payment> implements Action<UpdatePaymentResponse<T>> {
  private T payment;
  private String type;
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public UpdatePaymentCommand() {
  }
  public UpdatePaymentCommand(T payment,String type) {
    this.payment = payment;
    this.type = type;
  }

  @Override
  public void dispatch(AsyncCallback<UpdatePaymentResponse<T>> callback) {
    PaymentServiceAsync.INSTANCE.execute(this, callback);
  }

  public T getPayment() {
    return payment;
  }

  public void setPayment(T payment) {
    this.payment = payment;
  }

}
