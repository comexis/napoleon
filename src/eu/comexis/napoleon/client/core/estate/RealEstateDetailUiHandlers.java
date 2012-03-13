package eu.comexis.napoleon.client.core.estate;

public interface RealEstateDetailUiHandlers {
  public interface HasRealEstateDetailUiHandlers {
    public void setRealEstateDetailUiHandler(RealEstateDetailUiHandlers handler);
  }

  void onButtonBackToListClick();

  void onButtonUpdateClick();
  
  void onButtonRentClick();
}
