package eu.comexis.napoleon.shared.command.owner;

import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.client.rpc.OwnerServiceAsync;
import eu.comexis.napoleon.shared.command.Action;
import eu.comexis.napoleon.shared.model.Owner;

public class UpdateOwnerCommand implements Action<UpdateOwnerResponse>{

  public UpdateOwnerCommand() {
    // TODO Auto-generated constructor stub
  }
  public UpdateOwnerCommand(Owner owner) {
    this.owner = owner;
  }
  private Owner owner;
  
  public Owner getOwner() {
    return owner;
  }
  
  public void setOwner(Owner owner) {
    this.owner = owner;
  }
  @Override
  public void dispatch(AsyncCallback<UpdateOwnerResponse> callback) {
    OwnerServiceAsync.INSTANCE.execute(this, callback);

  }
}
