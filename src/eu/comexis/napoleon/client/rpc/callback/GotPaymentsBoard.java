package eu.comexis.napoleon.client.rpc.callback;

import java.util.List;

import eu.comexis.napoleon.shared.command.payment.GetPaymentsBoardResponse;
import eu.comexis.napoleon.shared.model.simple.PaymentListItem;

public abstract class GotPaymentsBoard extends AbstractCallback<GetPaymentsBoardResponse>{
  public abstract void got(String title,List<PaymentListItem> payments);

  @Override
  public void onSuccess(GetPaymentsBoardResponse result) {
    got(result.getTitle(),result.getListPayments());
  }
}
