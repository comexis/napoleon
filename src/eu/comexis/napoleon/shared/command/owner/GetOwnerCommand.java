package eu.comexis.napoleon.shared.command.owner;

import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.client.rpc.OwnerServiceAsync;
import eu.comexis.napoleon.shared.command.Action;

public class GetOwnerCommand implements Action<GetOwnerResponse> {

	private Long id;

	public GetOwnerCommand() {
	}

	public GetOwnerCommand(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void dispatch(AsyncCallback<GetOwnerResponse> callback) {
		OwnerServiceAsync.INSTANCE.execute(this, callback);

	}

}
