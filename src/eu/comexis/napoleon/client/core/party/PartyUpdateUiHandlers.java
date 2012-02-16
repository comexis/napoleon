package eu.comexis.napoleon.client.core.party;

public interface PartyUpdateUiHandlers {
  
  public interface HasPresenter {
    public void setPresenter(PartyUpdateUiHandlers presenter);
  }

  public void onButtonCancelClick();

  public void onButtonSaveClick();

  public void onCountrySelect(String selectedText);
  
  public void onPostalCodeSelect(String selectedPostalCode);
}