package eu.comexis.napoleon.shared.command.condo;

import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.client.rpc.CondoServiceAsync;
import eu.comexis.napoleon.shared.command.Action;

public class GetAllCondoCommand implements Action<GetAllCondoResponse>{

  public GetAllCondoCommand() {
    // TODO Auto-generated constructor stub
  }
  @Override
  public void dispatch(AsyncCallback<GetAllCondoResponse> callback) {
    CondoServiceAsync.INSTANCE.execute(this, callback);
  }
}
