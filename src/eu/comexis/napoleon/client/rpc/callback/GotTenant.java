package eu.comexis.napoleon.client.rpc.callback;

import java.util.logging.Logger;

import eu.comexis.napoleon.shared.command.tenant.GetAllTenantCommand;
import eu.comexis.napoleon.shared.command.tenant.GetAllTenantResponse;
import eu.comexis.napoleon.shared.command.tenant.GetTenantResponse;
import eu.comexis.napoleon.shared.model.Tenant;

/**
 * Callback for {@link GetAllTenantCommand} and {@link GetAllTenantResponse}
 * 
 * @author jDramaix
 *
 */
public  abstract class GotTenant extends AbstractCallback<GetTenantResponse>{

	@Override
	public void onSuccess(GetTenantResponse result) {
		got(result.getTenant());
	}
	
	public abstract void got(Tenant tenant);

}
