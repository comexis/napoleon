package eu.comexis.napoleon.client.rpc.callback;

import eu.comexis.napoleon.shared.command.condo.GetCondoResponse;
import eu.comexis.napoleon.shared.model.Condo;

public abstract class GotCondo extends AbstractCallback<GetCondoResponse>{

  public abstract void got(Condo cdo);
  @Override
  public void onSuccess(GetCondoResponse result) {
    got(result.getCdo());
  }
}
