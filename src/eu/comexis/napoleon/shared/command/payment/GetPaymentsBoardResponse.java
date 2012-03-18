package eu.comexis.napoleon.shared.command.payment;

import java.util.List;

import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.simple.PaymentListItem;

public class GetPaymentsBoardResponse implements Response {
  private List<PaymentListItem> listPayments;

  public GetPaymentsBoardResponse() {
    // TODO Auto-generated constructor stub
  }

  public List<PaymentListItem> getListPayments() {
    return listPayments;
  }

  public void setListPayments(List<PaymentListItem> listPayments) {
    this.listPayments = listPayments;
  }

}
