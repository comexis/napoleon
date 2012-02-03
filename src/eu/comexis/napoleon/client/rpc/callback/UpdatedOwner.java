package eu.comexis.napoleon.client.rpc.callback;

import eu.comexis.napoleon.shared.command.owner.UpdateOwnerResponse;
import eu.comexis.napoleon.shared.model.Owner;

public abstract class UpdatedOwner extends AbstractCallback<UpdateOwnerResponse>{

  public UpdatedOwner() {
    // TODO Auto-generated constructor stub
  }
  @Override
  public void onSuccess(UpdateOwnerResponse result) {
    got(result.getOwner());
  }
  public abstract void got(Owner owner);
}
