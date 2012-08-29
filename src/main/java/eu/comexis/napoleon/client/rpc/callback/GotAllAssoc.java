package eu.comexis.napoleon.client.rpc.callback;

import java.util.List;

import eu.comexis.napoleon.shared.command.association.GetAllAssocResponse;

public abstract class GotAllAssoc extends AbstractCallback<GetAllAssocResponse>{

  public GotAllAssoc() {
    // TODO Auto-generated constructor stub
  }
  public abstract void got(List<String> associations);

  @Override
  public void onSuccess(GetAllAssocResponse result) {
    got(result.getHomeownerAssociationNames());
  }
}
