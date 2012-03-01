package eu.comexis.napoleon.client.core.lease;

public interface LeaseUpdateUiHandlers {
  public interface HasLeaseUpdateUiHandler {
    public void setLeaseUpdateUiHandler(LeaseUpdateUiHandlers handler);
  }
  public void onButtonCancelClick();

  public void onButtonSaveClick();

}
