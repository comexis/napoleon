package eu.comexis.napoleon.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import eu.comexis.napoleon.shared.command.payment.GetAllPaymentCommand;
import eu.comexis.napoleon.shared.command.payment.GetAllPaymentResponse;
import eu.comexis.napoleon.shared.command.payment.GetPaymentCommand;
import eu.comexis.napoleon.shared.command.payment.GetPaymentResponse;
import eu.comexis.napoleon.shared.command.payment.UpdatePaymentCommand;
import eu.comexis.napoleon.shared.command.payment.UpdatePaymentResponse;
import eu.comexis.napoleon.shared.model.Payment;

@RemoteServiceRelativePath("payment")
public interface PaymentService extends RemoteService {
  public <T extends Payment> GetPaymentResponse<T> execute(GetPaymentCommand<T> command);
  public <T extends Payment> GetAllPaymentResponse<T> execute(GetAllPaymentCommand<T> command);
  public <T extends Payment> UpdatePaymentResponse<T> execute(UpdatePaymentCommand<T> command);
}
