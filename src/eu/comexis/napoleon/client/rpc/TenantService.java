package eu.comexis.napoleon.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import eu.comexis.napoleon.shared.command.tenant.GetAllTenantCommand;
import eu.comexis.napoleon.shared.command.tenant.GetAllTenantResponse;
import eu.comexis.napoleon.shared.command.tenant.GetTenantCommand;
import eu.comexis.napoleon.shared.command.tenant.GetTenantResponse;
import eu.comexis.napoleon.shared.command.tenant.UpdateTenantCommand;
import eu.comexis.napoleon.shared.command.tenant.UpdateTenantResponse;

@RemoteServiceRelativePath("tenant")
public interface TenantService extends RemoteService {
	
	public GetAllTenantResponse execute(GetAllTenantCommand command);
	
	public GetTenantResponse execute(GetTenantCommand command);
	
	public UpdateTenantResponse execute(UpdateTenantCommand command);

}
