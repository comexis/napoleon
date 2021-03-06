package eu.comexis.napoleon.client.rpc.callback;

import eu.comexis.napoleon.shared.command.owner.UpdateOwnerResponse;
import eu.comexis.napoleon.shared.model.Owner;

public abstract class UpdatedOwner extends AbstractCallback<UpdateOwnerResponse> {

  public UpdatedOwner() {
    // TODO Auto-generated constructor stub
  }

  public abstract void updated(Owner owner);

  @Override
  public void onSuccess(UpdateOwnerResponse result) {
    updated(result.getOwner());
  }
}
