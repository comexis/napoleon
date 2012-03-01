package eu.comexis.napoleon.client.rpc.callback;

import java.util.List;

import eu.comexis.napoleon.shared.command.lease.GetAllLeaseResponse;
import eu.comexis.napoleon.shared.command.owner.GetAllOwnerResponse;
import eu.comexis.napoleon.shared.model.simple.SimpleLease;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

public abstract class GotAllLease extends AbstractCallback<GetAllLeaseResponse>{

  public abstract void got(List<SimpleLease> leaseList);

  @Override
  public void onSuccess(GetAllLeaseResponse result) {
    got(result.getLeaseList());
  }

}
