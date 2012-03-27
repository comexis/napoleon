
package eu.comexis.napoleon.shared.command.contractor;
import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.client.rpc.ContractorServiceAsync;
import eu.comexis.napoleon.shared.command.Action;

public class GetAllContractorCommand implements Action<GetAllContractorResponse>{
  
	public GetAllContractorCommand (){
	}

  @Override
  public void dispatch(AsyncCallback<GetAllContractorResponse> callback) {
    ContractorServiceAsync.INSTANCE.execute(this, callback); 
  }
}
