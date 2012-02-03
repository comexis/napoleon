package eu.comexis.napoleon.client.core.owner;

import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

public interface OwnerUpdateUiHandlers {
  public interface HasOwnerUpdateUiHandler {
    public void setOwnerUpdateUiHandler(OwnerUpdateUiHandlers handler);
  }
  public void onCitySelect(String selectedText);
  public void onCountrySelect(String selectedText);
  public void onButtonCancelClick();
  public void onButtonSaveClick();
}
