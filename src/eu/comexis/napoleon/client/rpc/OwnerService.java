package eu.comexis.napoleon.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;

import eu.comexis.napoleon.shared.command.owner.GetAllOwnerCommand;
import eu.comexis.napoleon.shared.command.owner.GetAllOwnerResponse;

public interface OwnerService extends RemoteService {
	
	public GetAllOwnerResponse execute(GetAllOwnerCommand command);

}
