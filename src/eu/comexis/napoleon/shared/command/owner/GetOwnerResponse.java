package eu.comexis.napoleon.shared.command.owner;

import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.Owner;

public class GetOwnerResponse implements Response{
	
	private Owner owner;
	
	public GetOwnerResponse() {
	}
	
	public Owner getOwner() {
		return owner;
	}
	
	public void setOwner(Owner owner) {
		this.owner = owner;
	}

}
