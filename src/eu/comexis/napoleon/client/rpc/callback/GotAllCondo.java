package eu.comexis.napoleon.client.rpc.callback;

import java.util.List;

import eu.comexis.napoleon.shared.command.condo.GetAllCondoResponse;

public abstract class GotAllCondo extends AbstractCallback<GetAllCondoResponse>{
  public abstract void got(List<String> condoNames);
  
  public GotAllCondo(){ 
  }
  @Override
  public void onSuccess(GetAllCondoResponse result) {
    got(result.getCondoNames());

  }
}
