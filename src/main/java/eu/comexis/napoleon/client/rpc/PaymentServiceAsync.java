package eu.comexis.napoleon.client.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.shared.command.payment.GetAllPaymentCommand;
import eu.comexis.napoleon.shared.command.payment.GetAllPaymentResponse;
import eu.comexis.napoleon.shared.command.payment.GetPaymentCommand;
import eu.comexis.napoleon.shared.command.payment.GetPaymentResponse;
import eu.comexis.napoleon.shared.command.payment.GetPaymentsBoardCommand;
import eu.comexis.napoleon.shared.command.payment.GetPaymentsBoardResponse;
import eu.comexis.napoleon.shared.command.payment.UpdatePaymentCommand;
import eu.comexis.napoleon.shared.command.payment.UpdatePaymentResponse;
import eu.comexis.napoleon.shared.model.Payment;

public interface PaymentServiceAsync {
  public PaymentServiceAsync INSTANCE = GWT.create(PaymentService.class);

  <T extends Payment> void execute(GetAllPaymentCommand<T> command, AsyncCallback<GetAllPaymentResponse<T>> callback);
  
  <T extends Payment> void execute(GetPaymentCommand<T> command, AsyncCallback<GetPaymentResponse<T>> callback);
  
  void execute(GetPaymentsBoardCommand command, AsyncCallback<GetPaymentsBoardResponse> callback);

  <T extends Payment> void execute(UpdatePaymentCommand<T> command, AsyncCallback<UpdatePaymentResponse<T>> callback);
}
