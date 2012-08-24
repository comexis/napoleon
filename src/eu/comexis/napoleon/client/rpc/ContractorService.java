
package eu.comexis.napoleon.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import eu.comexis.napoleon.shared.command.contractor.GetAllContractorCommand;
import eu.comexis.napoleon.shared.command.contractor.GetAllContractorResponse;
@RemoteServiceRelativePath("contractor")
public interface ContractorService extends RemoteService{
  public GetAllContractorResponse execute(GetAllContractorCommand command);
}
