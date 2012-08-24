package eu.comexis.napoleon.client.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.shared.command.condo.GetAllCondoCommand;
import eu.comexis.napoleon.shared.command.condo.GetAllCondoResponse;
import eu.comexis.napoleon.shared.command.condo.GetCondoCommand;
import eu.comexis.napoleon.shared.command.condo.GetCondoResponse;

public interface CondoServiceAsync {
  public CondoServiceAsync INSTANCE = GWT.create(CondoService.class);

  void execute(GetAllCondoCommand command, AsyncCallback<GetAllCondoResponse> callback);
  
  void execute(GetCondoCommand command, AsyncCallback<GetCondoResponse> callback);
}
