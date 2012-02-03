package eu.comexis.napoleon.shared.command.tenant;

import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.client.rpc.TenantServiceAsync;
import eu.comexis.napoleon.shared.command.Action;

public class GetAllTenantCommand implements Action<GetAllTenantResponse> {

	@Override
	public void dispatch(AsyncCallback<GetAllTenantResponse> callback) {
		TenantServiceAsync.INSTANCE.execute(this, callback);
	}

}
