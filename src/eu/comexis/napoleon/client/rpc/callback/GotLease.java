package eu.comexis.napoleon.client.rpc.callback;

import eu.comexis.napoleon.shared.command.lease.GetLeaseResponse;
import eu.comexis.napoleon.shared.model.Condo;
import eu.comexis.napoleon.shared.model.Lease;

public abstract class GotLease extends AbstractCallback<GetLeaseResponse>{

  public abstract void got(Lease lease);
  @Override
  public void onSuccess(GetLeaseResponse result) {
    got(result.getLease());
  }

}
