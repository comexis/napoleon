package eu.comexis.napoleon.shared.command.tenant;

import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.client.rpc.TenantServiceAsync;
import eu.comexis.napoleon.shared.command.Action;
import eu.comexis.napoleon.shared.model.Tenant;

public class UpdateTenantCommand implements Action<UpdateTenantResponse> {

  private Tenant tenant;

  public UpdateTenantCommand() {
    // TODO Auto-generated constructor stub
  }

  public UpdateTenantCommand(Tenant tenant) {
    this.tenant = tenant;
  }

  @Override
  public void dispatch(AsyncCallback<UpdateTenantResponse> callback) {
    TenantServiceAsync.INSTANCE.execute(this, callback);

  }

  public Tenant getTenant() {
    return tenant;
  }

  public void setTenant(Tenant tenant) {
    this.tenant = tenant;
  }
}
