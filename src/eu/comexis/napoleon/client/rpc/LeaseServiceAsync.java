package eu.comexis.napoleon.client.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.shared.command.lease.GetAllLeaseCommand;
import eu.comexis.napoleon.shared.command.lease.GetAllLeaseResponse;
import eu.comexis.napoleon.shared.command.lease.GetLeaseCommand;
import eu.comexis.napoleon.shared.command.lease.GetLeaseResponse;
import eu.comexis.napoleon.shared.command.lease.UpdateLeaseCommand;
import eu.comexis.napoleon.shared.command.lease.UpdateLeaseResponse;

public interface LeaseServiceAsync {
  public LeaseServiceAsync INSTANCE = GWT.create(LeaseService.class);
  void execute(GetLeaseCommand command, AsyncCallback<GetLeaseResponse> callback);
  void execute(GetAllLeaseCommand command, AsyncCallback<GetAllLeaseResponse> callback);
  void execute(UpdateLeaseCommand command, AsyncCallback<UpdateLeaseResponse> callback);
}
