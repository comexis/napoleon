package eu.comexis.napoleon.shared.command.association;

import eu.comexis.napoleon.shared.command.Action;

import com.google.gwt.user.client.rpc.AsyncCallback;
import eu.comexis.napoleon.client.rpc.AssocServiceAsync;


public class GetAllAssocCommand implements Action<GetAllAssocResponse>{

  public GetAllAssocCommand() {
    // TODO Auto-generated constructor stub
  }
  @Override
  public void dispatch(AsyncCallback<GetAllAssocResponse> callback) {
    AssocServiceAsync.INSTANCE.execute(this, callback);
  }
}
