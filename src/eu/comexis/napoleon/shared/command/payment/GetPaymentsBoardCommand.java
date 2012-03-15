package eu.comexis.napoleon.shared.command.payment;

import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.shared.command.Action;

public class GetPaymentsBoardCommand implements Action<GetPaymentsBoardResponse> {
  private String leaseId;
  private String estateId;

  public GetPaymentsBoardCommand() {
    // TODO Auto-generated constructor stub
  }
  
  public GetPaymentsBoardCommand(String leaseId,String estateId) {
    this.leaseId = leaseId;
    this.estateId = estateId;
  }

  @Override
  public void dispatch(AsyncCallback<GetPaymentsBoardResponse> callback) {
    // TODO Auto-generated method stub

  }

  public String getEstateId() {
    return estateId;
  }

  public String getLeaseId() {
    return leaseId;
  }

  public void setEstateId(String estateId) {
    this.estateId = estateId;
  }

  public void setLeaseId(String leaseId) {
    this.leaseId = leaseId;
  }

}
