package eu.comexis.napoleon.shared.command.lease;

import java.util.List;

import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.simple.SimpleLease;

;

public class GetAllLeaseResponse implements Response {

  private List<SimpleLease> leaseList;

  public List<SimpleLease> getLeaseList() {
    return leaseList;
  }

  public void setLeaseList(List<SimpleLease> leaseList) {
    this.leaseList = leaseList;
  }

}
