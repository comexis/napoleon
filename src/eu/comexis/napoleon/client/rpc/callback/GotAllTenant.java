package eu.comexis.napoleon.client.rpc.callback;

import java.util.List;

import eu.comexis.napoleon.shared.command.tenant.GetAllTenantCommand;
import eu.comexis.napoleon.shared.command.tenant.GetAllTenantResponse;
import eu.comexis.napoleon.shared.model.simple.SimpleTenant;

/**
 * Callback for {@link GetAllTenantCommand} and {@link GetAllTenantResponse}
 * 
 * @author jDramaix
 * 
 */
public abstract class GotAllTenant extends AbstractCallback<GetAllTenantResponse> {

  public abstract void got(List<SimpleTenant> tenants);

  @Override
  public void onSuccess(GetAllTenantResponse result) {
    got(result.getTenants());

  }

}
