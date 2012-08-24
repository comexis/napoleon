package eu.comexis.napoleon.client.rpc.callback;

import java.util.List;

import eu.comexis.napoleon.shared.command.condo.GetAllCondoResponse;
import eu.comexis.napoleon.shared.command.contractor.GetAllContractorResponse;
import eu.comexis.napoleon.shared.model.Contractor;

public abstract class GotAllContractor extends AbstractCallback<GetAllContractorResponse>{
  public abstract void got(List<Contractor> Contractors);
  
  public GotAllContractor(){ 
  }
  @Override
  public void onSuccess(GetAllContractorResponse result) {
    got(result.getListContractor());

  }
}
