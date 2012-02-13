package eu.comexis.napoleon.client.core.tenant;

public interface TenantUpdateUiHandlers {
  public interface HasTenantUpdateUiHandler {
    public void setTenantUpdateUiHandler(TenantUpdateUiHandlers handler);
  }

  public void onButtonCancelClick();

  public void onButtonSaveClick();

  public void onCitySelect(String selectedText);

  public void onCountrySelect(String selectedText);
}