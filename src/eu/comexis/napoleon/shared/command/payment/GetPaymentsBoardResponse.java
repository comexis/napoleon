package eu.comexis.napoleon.shared.command.payment;

import java.util.List;

import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.simple.PaymentListItem;

public class GetPaymentsBoardResponse implements Response {
  private String title = "";
  private List<PaymentListItem> listPayments;

  public GetPaymentsBoardResponse() {
    // TODO Auto-generated constructor stub
  }

  public List<PaymentListItem> getListPayments() {
    return listPayments;
  }

  public String getTitle() {
    return title;
  }

  public void setListPayments(List<PaymentListItem> listPayments) {
    this.listPayments = listPayments;
  }

  public void setTitle(String title) {
    this.title = title;
  }

}
