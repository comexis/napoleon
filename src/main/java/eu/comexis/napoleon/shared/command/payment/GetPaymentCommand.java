package eu.comexis.napoleon.shared.command.payment;

import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.client.rpc.PaymentServiceAsync;
import eu.comexis.napoleon.shared.command.Action;
import eu.comexis.napoleon.shared.model.Payment;

public class GetPaymentCommand<T extends Payment> implements Action<GetPaymentResponse<T>> {

  private String leaseId;
  private String estateId;
  private String paymentId;
  private String type;
  
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public GetPaymentCommand() {
  }
  public GetPaymentCommand(String paymentId,String leaseId,String estateId,String type) {
    this.leaseId=leaseId;
    this.estateId = estateId;
    this.paymentId=paymentId;
    this.type = type;
  }
  
  @Override
  public void dispatch(AsyncCallback<GetPaymentResponse<T>> callback) {
    PaymentServiceAsync.INSTANCE.execute(this, callback);
  }

  public String getEstateId() {
    return estateId;
  }

  public String getLeaseId() {
    return leaseId;
  }

  public String getPaymentId() {
    return paymentId;
  }

  public void setEstateId(String estateId) {
    this.estateId = estateId;
  }

  public void setLeaseId(String leaseId) {
    this.leaseId = leaseId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }

}
