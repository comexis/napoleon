package eu.comexis.napoleon.client.rpc.callback;

import eu.comexis.napoleon.shared.command.owner.GetAllOwnerCommand;
import eu.comexis.napoleon.shared.command.owner.GetAllOwnerResponse;
import eu.comexis.napoleon.shared.command.owner.GetOwnerResponse;
import eu.comexis.napoleon.shared.model.Owner;

/**
 * Callback for {@link GetAllOwnerCommand} and {@link GetAllOwnerResponse}
 * 
 * @author jDramaix
 *
 */
public  abstract class GotOwner extends AbstractCallback<GetOwnerResponse>{

	@Override
	public void onSuccess(GetOwnerResponse result) {
		got(result.getOwner());
		
	}
	
	public abstract void got(Owner owner);

}
