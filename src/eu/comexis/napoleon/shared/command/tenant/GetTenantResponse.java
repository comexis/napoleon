package eu.comexis.napoleon.shared.command.tenant;

import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.Tenant;

public class GetTenantResponse implements Response {

  private Tenant tenant;

  public GetTenantResponse() {
  }

  public Tenant getTenant() {
    return tenant;
  }

  public void setTenant(Tenant tenant) {
    this.tenant = tenant;
  }

}
