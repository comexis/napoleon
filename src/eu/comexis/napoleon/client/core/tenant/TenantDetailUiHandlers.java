package eu.comexis.napoleon.client.core.tenant;

public interface TenantDetailUiHandlers {
  public interface HasTenantDetailUiHandlers {
    public void setTenantDetailUiHandler(TenantDetailUiHandlers handler);
  }

  void onButtonBackToListClick();

  void onButtonUpdateClick();
}
