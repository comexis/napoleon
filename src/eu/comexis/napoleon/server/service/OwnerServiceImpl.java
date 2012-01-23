package eu.comexis.napoleon.server.service;

import java.util.ArrayList;

import eu.comexis.napoleon.client.rpc.OwnerService;
import eu.comexis.napoleon.shared.command.owner.GetAllOwnerCommand;
import eu.comexis.napoleon.shared.command.owner.GetAllOwnerResponse;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

/**
 * Temporary implementations !!! All methods implementation should query the database.
 * C'est a toi de jouer Xavier !
 * @author jDramaix
 *
 */
public class OwnerServiceImpl implements OwnerService {

	@Override
	public GetAllOwnerResponse execute(GetAllOwnerCommand command) {
		
		ArrayList<SimpleOwner> owners = new ArrayList<SimpleOwner>();
		owners.add(createOwner("Dramaix", "7000", "Mons", "Residence de la bascule, 44", "065/438765", "0497/063970"));
		owners.add(createOwner("Platiaux", "7100", "Ath", "Rue de l'orval, 69","064/435627", "0497/084352"));
		owners.add(createOwner("Tytgat", "1000", "Bruxelles", "Chaussee de Mons, 71", "02/4348765", "0498/987645"));
		
		GetAllOwnerResponse response = new GetAllOwnerResponse();
		response.setOwners(owners);
		
		return response;
	}
	
	//for temporary implementation
	private SimpleOwner createOwner(String name, String postalCode, String city, String address, String tel, String mobile){
		SimpleOwner o = new SimpleOwner();
		o.setName(name);
		o.setPostalCode(postalCode);
		o.setCity(city);
		o.setAddress(address);
		o.setMobile(mobile);
		o.setName(name);
		
		return o;
		
	}

}
