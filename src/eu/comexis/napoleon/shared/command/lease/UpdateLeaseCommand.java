package eu.comexis.napoleon.shared.command.lease;

import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.client.rpc.LeaseServiceAsync;
import eu.comexis.napoleon.shared.command.Action;
import eu.comexis.napoleon.shared.model.Lease;

public class UpdateLeaseCommand implements Action<UpdateLeaseResponse>{
  private Lease lease;

  public UpdateLeaseCommand() {
    // TODO Auto-generated constructor stub
  }

  public Lease getLease() {
    return lease;
  }

  public void setLease(Lease lease) {
    this.lease = lease;
  }

  @Override
  public void dispatch(AsyncCallback<UpdateLeaseResponse> callback) {
    LeaseServiceAsync.INSTANCE.execute(this, callback);
  }

}
