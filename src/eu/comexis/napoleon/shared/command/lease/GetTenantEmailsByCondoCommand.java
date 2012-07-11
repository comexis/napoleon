package eu.comexis.napoleon.shared.command.lease;

import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.client.rpc.TenantServiceAsync;
import eu.comexis.napoleon.shared.command.Action;

public class GetTenantEmailsByCondoCommand implements
		Action<GetTenantEmailsByCondoResponse> {

	private String condo;

	public GetTenantEmailsByCondoCommand() {
		// TODO Auto-generated constructor stub
	}

	public GetTenantEmailsByCondoCommand(String condo) {
		this.condo = condo;
	}

	@Override
	public void dispatch(AsyncCallback<GetTenantEmailsByCondoResponse> callback) {
		TenantServiceAsync.INSTANCE.execute(this, callback);
	}

	public String getCondo() {
		return condo;
	}

	public void setCondo(String condo) {
		this.condo = condo;
	}

}
