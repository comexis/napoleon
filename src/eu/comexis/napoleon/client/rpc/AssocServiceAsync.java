package eu.comexis.napoleon.client.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.shared.command.association.GetAllAssocCommand;
import eu.comexis.napoleon.shared.command.association.GetAllAssocResponse;
import eu.comexis.napoleon.shared.command.association.GetAssocCommand;
import eu.comexis.napoleon.shared.command.association.GetAssocResponse;

public interface AssocServiceAsync {
  public AssocServiceAsync INSTANCE = GWT.create(AssocService.class);

  void execute(GetAllAssocCommand command, AsyncCallback<GetAllAssocResponse> callback);
  
  void execute(GetAssocCommand command, AsyncCallback<GetAssocResponse> callback);
}
