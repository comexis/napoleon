package eu.comexis.napoleon.shared.command.lease;

import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.Lease;

public class GetLeaseResponse implements Response {
  private Lease lease;

  public GetLeaseResponse() {
    // TODO Auto-generated constructor stub
  }

  public Lease getLease() {
    return lease;
  }

  public void setLease(Lease lease) {
    this.lease = lease;
  }

}
