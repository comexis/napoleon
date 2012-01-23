package eu.comexis.napoleon.server.service;

import java.util.ArrayList;

import eu.comexis.napoleon.client.rpc.OwnerService;
import eu.comexis.napoleon.shared.command.owner.GetAllOwnerCommand;
import eu.comexis.napoleon.shared.command.owner.GetAllOwnerResponse;
import eu.comexis.napoleon.shared.model.Owner;

/**
 * Temporary implementations !!! All methods implementation should query the database.
 * C'est a toi de jouer Xavier !
 * @author jDramaix
 *
 */
public class OwnerServiceImpl implements OwnerService {

	@Override
	public GetAllOwnerResponse execute(GetAllOwnerCommand command) {
		
		ArrayList<Owner> owners = new ArrayList<Owner>();
		owners.add(createOwner("Dramaix", "Julien", "Mons", "julien.dramaix@gmail.com"));
		owners.add(createOwner("Platiaux", "Xavier", "Mons", "xavier.platiaux@gmail.com"));
		owners.add(createOwner("Tytgat", "Benoit", "Bruxelles", "jbenoit.tytgat@comexis.eu"));
		
		GetAllOwnerResponse response = new GetAllOwnerResponse();
		response.setOwners(owners);
		
		return response;
	}
	
	//for temporary implementation
	private Owner createOwner(String name, String firstName, String city, String email){
		Owner o = new Owner(name);
		o.setFirstName(firstName);
		o.setCity(city);
		o.setEmail(email);
		
		return o;
		
	}

}
