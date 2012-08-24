package eu.comexis.napoleon.shared.command.association;

import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.client.rpc.AssocServiceAsync;
import eu.comexis.napoleon.shared.command.Action;

public class GetAssocCommand implements Action<GetAssocResponse>{

  public GetAssocCommand() {
    // TODO Auto-generated constructor stub
  }
  private String name;
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  @Override
  public void dispatch(AsyncCallback<GetAssocResponse> callback) {
    AssocServiceAsync.INSTANCE.execute(this, callback);
  }

}
