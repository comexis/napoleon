package eu.comexis.napoleon.shared.command.condo;

import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.client.rpc.CondoServiceAsync;
import eu.comexis.napoleon.shared.command.Action;

public class GetCondoCommand implements Action<GetCondoResponse>{

  private String name;

  public GetCondoCommand() {
    // TODO Auto-generated constructor stub
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  @Override
  public void dispatch(AsyncCallback<GetCondoResponse> callback) {
    CondoServiceAsync.INSTANCE.execute(this, callback);
  }

}
