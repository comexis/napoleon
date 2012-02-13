package eu.comexis.napoleon.client.core.tenant;

import eu.comexis.napoleon.shared.model.simple.SimpleTenant;

/**
 * Interface of the {@link TenantListPresenter} used to communicate with the view
 * 
 * @author jDramaix
 * 
 */
public interface TenantListUiHandlers {

  /**
   * interface implemented by the view
   * 
   * @author jDramaix
   * 
   */
  public interface HasTenantListUiHandlers {
    public void setTenantListUiHandler(TenantListUiHandlers handler);
  }

  public void onButtonBackToDashBoardClick();

  public void onButtonNewClick();

  /**
   * Method call when the user select
   * 
   * @param selectedTenant
   */
  public void onSelect(SimpleTenant selectedTenant);
}
