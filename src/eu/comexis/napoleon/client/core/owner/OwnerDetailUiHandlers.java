package eu.comexis.napoleon.client.core.owner;

public interface OwnerDetailUiHandlers {
  public interface HasOwnerDetailUiHandlers {
    public void setOwnerDetailUiHandler(OwnerDetailUiHandlers handler);
  }

  void onButtonBackToDashBoardClick();

  void onButtonUpdateClick();
}
