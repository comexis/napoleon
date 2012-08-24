package eu.comexis.napoleon.client.rpc.callback;

import eu.comexis.napoleon.shared.command.association.GetAssocResponse;
import eu.comexis.napoleon.shared.model.Association;

public abstract class GotAssoc extends AbstractCallback<GetAssocResponse>{

  public abstract void got(Association assoc);

  @Override
  public void onSuccess(GetAssocResponse result) {
    got(result.getHomeownerAssociation());
  }

}
