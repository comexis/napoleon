package eu.comexis.napoleon.client.core.estate;

import eu.comexis.napoleon.shared.model.simple.SimpleRealEstate;

public interface RealEstateUpdateUiHandlers {
  public interface HasRealEstateUpdateUiHandler {
    public void setRealEstateUpdateUiHandler(RealEstateUpdateUiHandlers handler);
  }
  public void onCitySelect(String selectedText);
  public void onCountrySelect(String selectedText);
  public void onButtonCancelClick();
  public void onButtonSaveClick();
}
