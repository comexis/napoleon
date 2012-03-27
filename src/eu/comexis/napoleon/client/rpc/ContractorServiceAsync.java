
package eu.comexis.napoleon.client.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.shared.command.contractor.GetAllContractorCommand;
import eu.comexis.napoleon.shared.command.contractor.GetAllContractorResponse;
public interface ContractorServiceAsync {
  public ContractorServiceAsync INSTANCE = GWT.create(ContractorService.class);
  void execute(GetAllContractorCommand command, AsyncCallback<GetAllContractorResponse> callback);
}
