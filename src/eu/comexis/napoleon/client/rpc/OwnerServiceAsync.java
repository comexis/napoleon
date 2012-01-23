package eu.comexis.napoleon.client.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.shared.command.owner.GetAllOwnerCommand;
import eu.comexis.napoleon.shared.command.owner.GetAllOwnerResponse;

public interface OwnerServiceAsync {

	public OwnerServiceAsync INSTANCE = GWT.create(OwnerService.class);
	
	void execute(GetAllOwnerCommand command,
			AsyncCallback<GetAllOwnerResponse> callback);

}
