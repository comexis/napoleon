package eu.comexis.napoleon.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import eu.comexis.napoleon.shared.command.association.GetAllAssocCommand;
import eu.comexis.napoleon.shared.command.association.GetAllAssocResponse;
import eu.comexis.napoleon.shared.command.association.GetAssocCommand;
import eu.comexis.napoleon.shared.command.association.GetAssocResponse;

@RemoteServiceRelativePath("association")
public interface AssocService extends RemoteService {
  public GetAllAssocResponse execute(GetAllAssocCommand command);
  public GetAssocResponse execute(GetAssocCommand command);
}
