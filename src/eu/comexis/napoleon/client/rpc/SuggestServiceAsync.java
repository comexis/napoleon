package eu.comexis.napoleon.client.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.shared.command.suggest.GetAllSuggestCommand;
import eu.comexis.napoleon.shared.command.suggest.GetAllSuggestResponse;

public interface SuggestServiceAsync {

  public SuggestServiceAsync INSTANCE = GWT.create(SuggestService.class);

  void execute(GetAllSuggestCommand command, AsyncCallback<GetAllSuggestResponse> callback);

}
