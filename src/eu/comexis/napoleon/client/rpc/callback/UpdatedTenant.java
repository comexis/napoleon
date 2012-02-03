package eu.comexis.napoleon.client.rpc.callback;

import eu.comexis.napoleon.shared.command.tenant.UpdateTenantResponse;
import eu.comexis.napoleon.shared.model.Tenant;

public abstract class UpdatedTenant extends AbstractCallback<UpdateTenantResponse>{

  public UpdatedTenant() {
    // TODO Auto-generated constructor stub
  }
  @Override
  public void onSuccess(UpdateTenantResponse result) {
    got(result.getTenant());
  }
  public abstract void got(Tenant tenant);
}
