package eu.comexis.napoleon.client.core.tenant;

import eu.comexis.napoleon.shared.model.simple.SimpleTenant;

public interface TenantUpdateUiHandlers {
  public interface HasTenantUpdateUiHandler {
    public void setTenantUpdateUiHandler(TenantUpdateUiHandlers handler);
  }
  public void onCitySelect(String selectedText);
  public void onCountrySelect(String selectedText);
  public void onButtonCancelClick();
  public void onButtonSaveClick();
}
