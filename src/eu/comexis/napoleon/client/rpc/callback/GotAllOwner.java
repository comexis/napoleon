package eu.comexis.napoleon.client.rpc.callback;

import java.util.List;

import eu.comexis.napoleon.shared.command.owner.GetAllOwnerCommand;
import eu.comexis.napoleon.shared.command.owner.GetAllOwnerResponse;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

/**
 * Callback for {@link GetAllOwnerCommand} and {@link GetAllOwnerResponse}
 * 
 * @author jDramaix
 * 
 */
public abstract class GotAllOwner extends AbstractCallback<GetAllOwnerResponse> {

  public abstract void got(List<SimpleOwner> owners);

  @Override
  public void onSuccess(GetAllOwnerResponse result) {
    got(result.getOwners());

  }

}
