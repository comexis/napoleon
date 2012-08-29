package eu.comexis.napoleon.shared.command.suggest;

import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.client.rpc.SuggestServiceAsync;
import eu.comexis.napoleon.shared.command.Action;

public class GetAllSuggestCommand implements Action<GetAllSuggestResponse>{

  public GetAllSuggestCommand() {
  }
  
  public GetAllSuggestCommand(String name) {
    this.name = name;
  }
  @Override
  public void dispatch(AsyncCallback<GetAllSuggestResponse> callback) {
    SuggestServiceAsync.INSTANCE.execute(this, callback);
  }
  private String name;
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
}
