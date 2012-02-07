package eu.comexis.napoleon.shared.command.tenant;

import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.Tenant;

public class UpdateTenantResponse implements Response {

  private Tenant tenant;

  public UpdateTenantResponse() {
    // TODO Auto-generated constructor stub
  }

  public Tenant getTenant() {
    return tenant;
  }

  public void setTenant(Tenant tenant) {
    this.tenant = tenant;
  }
}
