package eu.comexis.napoleon.shared.command.payment;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.client.rpc.PaymentServiceAsync;
import eu.comexis.napoleon.shared.command.Action;
import eu.comexis.napoleon.shared.model.Payment;

public class GetAllPaymentCommand<T extends Payment> implements Action<GetAllPaymentResponse<T>> {

  private String leaseId;
  private String estateId;
  private String type;
  
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public GetAllPaymentCommand() {
    
  }

  public GetAllPaymentCommand(String leaseId, String estateId, String type) {
    this.leaseId = leaseId;
    this.estateId = estateId;
    this.type = type;
  }

  @Override
  public void dispatch(AsyncCallback<GetAllPaymentResponse<T>> callback) {
    PaymentServiceAsync.INSTANCE.execute(this, callback);
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
