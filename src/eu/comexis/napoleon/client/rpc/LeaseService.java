package eu.comexis.napoleon.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import eu.comexis.napoleon.shared.command.lease.GetAllLeaseCommand;
import eu.comexis.napoleon.shared.command.lease.GetAllLeaseResponse;
import eu.comexis.napoleon.shared.command.lease.GetLeaseCommand;
import eu.comexis.napoleon.shared.command.lease.GetLeaseResponse;
import eu.comexis.napoleon.shared.command.lease.UpdateLeaseCommand;
import eu.comexis.napoleon.shared.command.lease.UpdateLeaseResponse;

@RemoteServiceRelativePath("lease")
public interface LeaseService extends RemoteService{
  public GetLeaseResponse execute(GetLeaseCommand command);
  public GetAllLeaseResponse execute(GetAllLeaseCommand command);
  public UpdateLeaseResponse execute(UpdateLeaseCommand command);
}
