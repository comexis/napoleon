package eu.comexis.napoleon.shared.command.owner;

import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.client.rpc.OwnerServiceAsync;
import eu.comexis.napoleon.shared.command.Action;

public class GetAllOwnerCommand implements Action<GetAllOwnerResponse> {

  @Override
  public void dispatch(AsyncCallback<GetAllOwnerResponse> callback) {
    OwnerServiceAsync.INSTANCE.execute(this, callback);
  }

}
