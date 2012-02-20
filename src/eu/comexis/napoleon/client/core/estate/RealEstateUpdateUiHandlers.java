package eu.comexis.napoleon.client.core.estate;

public interface RealEstateUpdateUiHandlers {
  public interface HasRealEstateUpdateUiHandler {
    public void setRealEstateUpdateUiHandler(RealEstateUpdateUiHandlers handler);
  }

  public void onButtonCancelClick();

  public void onButtonSaveClick();

  public void onCitySelect(String selectedCity);

  public void onCountrySelect(String selectedText);
  
  public void onCondoSelect(String selectedText);
  
  public void onAssocSelect(String selectedText);
  
  public void onPostalCodeSelect(String selectedPostalCode);
}
