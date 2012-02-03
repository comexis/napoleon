package eu.comexis.napoleon.shared.command.tenant;

import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.client.rpc.TenantServiceAsync;
import eu.comexis.napoleon.shared.command.Action;

public class GetTenantCommand implements Action<GetTenantResponse> {

	private String id;

	public GetTenantCommand() {
	}

	public GetTenantCommand(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void dispatch(AsyncCallback<GetTenantResponse> callback) {
		TenantServiceAsync.INSTANCE.execute(this, callback);

	}

}
