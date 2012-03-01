package eu.comexis.napoleon.shared.command.lease;

import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.client.rpc.LeaseServiceAsync;
import eu.comexis.napoleon.shared.command.Action;

public class GetAllLeaseCommand implements Action<GetAllLeaseResponse> {
  private String realEstateId;

  public GetAllLeaseCommand() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public void dispatch(AsyncCallback<GetAllLeaseResponse> callback) {
    LeaseServiceAsync.INSTANCE.execute(this, callback);
  }

  public String getRealEstateId() {
    return realEstateId;
  }

  public void setRealEstateId(String realEstateId) {
    this.realEstateId = realEstateId;
  }

}
