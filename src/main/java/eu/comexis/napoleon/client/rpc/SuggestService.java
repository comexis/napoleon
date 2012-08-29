package eu.comexis.napoleon.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import eu.comexis.napoleon.shared.command.suggest.GetAllSuggestCommand;
import eu.comexis.napoleon.shared.command.suggest.GetAllSuggestResponse;

@RemoteServiceRelativePath("suggest")
public interface SuggestService extends RemoteService {
  
  public GetAllSuggestResponse execute(GetAllSuggestCommand command);
  
}
