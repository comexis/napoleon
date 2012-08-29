package eu.comexis.napoleon.shared.command.lease;

import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.Lease;

public class UpdateLeaseResponse implements Response {
  private Lease lease;
  private String errorMsg;

  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  public Lease getLease() {
    return lease;
  }

  public void setLease(Lease lease) {
    this.lease = lease;
  }

  public UpdateLeaseResponse() {
    // TODO Auto-generated constructor stub
  }

}
