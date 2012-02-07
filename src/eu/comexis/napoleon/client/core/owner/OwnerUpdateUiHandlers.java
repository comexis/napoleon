package eu.comexis.napoleon.client.core.owner;


public interface OwnerUpdateUiHandlers {
  public interface HasOwnerUpdateUiHandler {
    public void setOwnerUpdateUiHandler(OwnerUpdateUiHandlers handler);
  }
  public void onCitySelect(String selectedText);
  public void onCountrySelect(String selectedText);
  public void onButtonCancelClick();
  public void onButtonSaveClick();
}