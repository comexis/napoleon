package eu.comexis.napoleon.client.rpc.callback;

import eu.comexis.napoleon.shared.command.lease.UpdateLeaseResponse;
import eu.comexis.napoleon.shared.model.Lease;

public abstract class UpdatedLease extends AbstractCallback<UpdateLeaseResponse> {

  public UpdatedLease() {
    // TODO Auto-generated constructor stub
  }

  public abstract void got(Lease lease);

  @Override
  public void onSuccess(UpdateLeaseResponse result) {
    got(result.getLease());
  }
}
