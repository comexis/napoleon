package eu.comexis.napoleon.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import eu.comexis.napoleon.shared.command.condo.GetAllCondoCommand;
import eu.comexis.napoleon.shared.command.condo.GetAllCondoResponse;
import eu.comexis.napoleon.shared.command.condo.GetCondoCommand;
import eu.comexis.napoleon.shared.command.condo.GetCondoResponse;

@RemoteServiceRelativePath("condominium")
public interface CondoService extends RemoteService {
  public GetAllCondoResponse execute(GetAllCondoCommand command);
  public GetCondoResponse execute(GetCondoCommand command);
}