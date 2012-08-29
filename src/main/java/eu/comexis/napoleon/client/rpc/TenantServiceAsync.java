package eu.comexis.napoleon.client.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.shared.command.lease.GetTenantEmailsByCondoCommand;
import eu.comexis.napoleon.shared.command.lease.GetTenantEmailsByCondoResponse;
import eu.comexis.napoleon.shared.command.tenant.GetAllTenantCommand;
import eu.comexis.napoleon.shared.command.tenant.GetAllTenantResponse;
import eu.comexis.napoleon.shared.command.tenant.GetTenantCommand;
import eu.comexis.napoleon.shared.command.tenant.GetTenantResponse;
import eu.comexis.napoleon.shared.command.tenant.UpdateTenantCommand;
import eu.comexis.napoleon.shared.command.tenant.UpdateTenantResponse;

public interface TenantServiceAsync {

  public TenantServiceAsync INSTANCE = GWT.create(TenantService.class);

  void execute(GetAllTenantCommand command, AsyncCallback<GetAllTenantResponse> callback);

  void execute(GetTenantCommand command, AsyncCallback<GetTenantResponse> callback);

  void execute(UpdateTenantCommand command, AsyncCallback<UpdateTenantResponse> callback);

	void execute(GetTenantEmailsByCondoCommand getTenantEmailsByCondoCommand,
		AsyncCallback<GetTenantEmailsByCondoResponse> callback);

}
