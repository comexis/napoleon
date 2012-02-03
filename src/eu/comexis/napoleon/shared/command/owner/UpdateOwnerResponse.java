package eu.comexis.napoleon.shared.command.owner;

import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.Owner;

public class UpdateOwnerResponse implements Response{

  public UpdateOwnerResponse() {
    // TODO Auto-generated constructor stub
  }
  private Owner owner;
  
  public Owner getOwner() {
    return owner;
  }
  
  public void setOwner(Owner owner) {
    this.owner = owner;
  }
}
