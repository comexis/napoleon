package eu.comexis.napoleon.client.core.owner;

public interface OwnerUpdateUiHandlers {
  public interface HasOwnerUpdateUiHandler {
    public void setOwnerUpdateUiHandler(OwnerUpdateUiHandlers handler);
  }

  public void onButtonCancelClick();

  public void onButtonSaveClick();

  public void onCitySelect(String selectedText);

  public void onCountrySelect(String selectedText);
}