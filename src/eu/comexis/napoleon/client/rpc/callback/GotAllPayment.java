package eu.comexis.napoleon.client.rpc.callback;

import java.util.List;

import eu.comexis.napoleon.shared.command.payment.GetAllPaymentResponse;
import eu.comexis.napoleon.shared.model.Payment;

public abstract class GotAllPayment <T extends Payment> extends AbstractCallback<GetAllPaymentResponse<T>>{
  public abstract void got(String title,List<T> payments);
  @Override
  public void onSuccess(GetAllPaymentResponse<T> result) {
    got(result.getTitle(),result.getPayment());
  }

}
