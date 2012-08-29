package eu.comexis.napoleon.shared.command.lease;

import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.client.rpc.LeaseServiceAsync;
import eu.comexis.napoleon.shared.command.Action;

public class GetLeaseCommand implements Action<GetLeaseResponse> {
  private String id;
  private String realEstateId;

  public GetLeaseCommand() {
    // TODO Auto-generated constructor stub
  }
  public GetLeaseCommand(String id,String realEstateId) {
    this.id = id;
    this.realEstateId = realEstateId;
  }

  @Override
  public void dispatch(AsyncCallback<GetLeaseResponse> callback) {
    LeaseServiceAsync.INSTANCE.execute(this, callback);
  }

  public String getId() {
    return id;
  }

  public String getRealEstateId() {
    return realEstateId;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setRealEstateId(String relaEstateId) {
    this.realEstateId = relaEstateId;
  }

}
