package eu.comexis.napoleon.client.rpc.callback;

import eu.comexis.napoleon.shared.command.payment.GetPaymentResponse;
import eu.comexis.napoleon.shared.model.Lease;
import eu.comexis.napoleon.shared.model.Payment;

public abstract class  GotPayment<T extends Payment> extends AbstractCallback<GetPaymentResponse<T>>{

  public abstract void got(T payment,String errorMsg);

  @Override
  public void onSuccess(GetPaymentResponse<T> result) {
    got(result.getPayment(),result.getErrorMsg());
  }

}
