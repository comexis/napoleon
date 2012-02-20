package eu.comexis.napoleon.client.core.party;

public interface PartyDetailsUiHandlers {
  public interface HasPresenter {
    public void setPresenter(PartyDetailsUiHandlers handler);
  }

  void onButtonBackToListClick();

  void onButtonUpdateClick();
}
