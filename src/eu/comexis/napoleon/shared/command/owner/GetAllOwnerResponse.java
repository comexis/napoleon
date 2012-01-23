package eu.comexis.napoleon.shared.command.owner;

import java.util.ArrayList;

import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.Owner;

public class GetAllOwnerResponse implements Response {
	
	private ArrayList<Owner> owners;
	
	public GetAllOwnerResponse() {}
	
	public ArrayList<Owner> getOwners() {
		return owners;
	}
	
	public void setOwners(ArrayList<Owner> owners) {
		this.owners = owners;
	}

}
