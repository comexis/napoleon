package eu.comexis.napoleon.client.core.estate;

public interface RealEstateUpdateUiHandlers {
  public interface HasRealEstateUpdateUiHandler {
    public void setRealEstateUpdateUiHandler(RealEstateUpdateUiHandlers handler);
  }

  public void onButtonCancelClick();

  public void onButtonSaveClick();

  public void onCitySelect(String selectedText);

  public void onCountrySelect(String selectedText);
}
