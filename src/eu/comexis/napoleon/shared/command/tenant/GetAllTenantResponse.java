package eu.comexis.napoleon.shared.command.tenant;

import java.util.ArrayList;

import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.simple.SimpleTenant;

public class GetAllTenantResponse implements Response {

  private ArrayList<SimpleTenant> tenants;

  public GetAllTenantResponse() {
  }

  public ArrayList<SimpleTenant> getTenants() {
    return tenants;
  }

  public void setTenants(ArrayList<SimpleTenant> tenants) {
    this.tenants = tenants;
  }

}
