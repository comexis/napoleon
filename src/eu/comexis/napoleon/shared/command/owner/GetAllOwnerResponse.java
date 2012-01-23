package eu.comexis.napoleon.shared.command.owner;

import java.util.ArrayList;

import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

public class GetAllOwnerResponse implements Response {
	
	private ArrayList<SimpleOwner> owners;
	
	public GetAllOwnerResponse() {}
	
	public ArrayList<SimpleOwner> getOwners() {
		return owners;
	}
	
	public void setOwners(ArrayList<SimpleOwner> owners) {
		this.owners = owners;
	}

}
