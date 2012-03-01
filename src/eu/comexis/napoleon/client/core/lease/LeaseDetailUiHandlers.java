package eu.comexis.napoleon.client.core.lease;

public interface LeaseDetailUiHandlers {
  public interface HasLeaseDetailUiHandlers {
    public void setLeaseDetailUiHandler(LeaseDetailUiHandlers handler);
  }

  void onButtonBackToListClick();

  void onButtonUpdateClick();
}
