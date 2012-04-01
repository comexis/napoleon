package eu.comexis.napoleon.client.rpc.callback;

import eu.comexis.napoleon.shared.command.payment.UpdatePaymentResponse;
import eu.comexis.napoleon.shared.model.Payment;

public abstract class UpdatedPayment<T extends Payment> extends AbstractCallback<UpdatePaymentResponse<T>>{

  public abstract void updated(T payment,String erroMsg);

  @Override
  public void onSuccess(UpdatePaymentResponse<T> result) {
    updated(result.getPayment(),result.getErrorMsg());
  }
}
