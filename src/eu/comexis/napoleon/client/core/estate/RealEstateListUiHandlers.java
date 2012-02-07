package eu.comexis.napoleon.client.core.estate;

import eu.comexis.napoleon.shared.model.simple.SimpleRealEstate;

/**
 * Interface of the {@link RealEstateListPresenter} used to communicate with the view
 * 
 * @author jDramaix
 * 
 */
public interface RealEstateListUiHandlers {

  /**
   * interface implemented by the view
   * 
   * @author jDramaix
   * 
   */
  public interface HasRealEstateListUiHandlers {
    public void setRealEstateListUiHandler(RealEstateListUiHandlers handler);
  }

  /**
   * Method call when the user select
   * 
   * @param selectedRealEstate
   */
  public void onSelect(SimpleRealEstate selectedRealEstate);
}
