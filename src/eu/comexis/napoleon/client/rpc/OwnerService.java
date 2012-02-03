package eu.comexis.napoleon.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import eu.comexis.napoleon.shared.command.owner.GetAllOwnerCommand;
import eu.comexis.napoleon.shared.command.owner.GetAllOwnerResponse;
import eu.comexis.napoleon.shared.command.owner.GetOwnerCommand;
import eu.comexis.napoleon.shared.command.owner.GetOwnerResponse;
import eu.comexis.napoleon.shared.command.owner.UpdateOwnerCommand;
import eu.comexis.napoleon.shared.command.owner.UpdateOwnerResponse;

@RemoteServiceRelativePath("owner")
public interface OwnerService extends RemoteService {
	
	public GetAllOwnerResponse execute(GetAllOwnerCommand command);
	
	public GetOwnerResponse execute(GetOwnerCommand command);
	
	public UpdateOwnerResponse execute(UpdateOwnerCommand command);

}
