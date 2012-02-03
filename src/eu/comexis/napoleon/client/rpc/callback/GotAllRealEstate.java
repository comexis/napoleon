package eu.comexis.napoleon.client.rpc.callback;

import java.util.List;
import java.util.logging.Logger;

import eu.comexis.napoleon.shared.command.estate.GetAllRealEstateCommand;
import eu.comexis.napoleon.shared.command.estate.GetAllRealEstateResponse;
import eu.comexis.napoleon.shared.model.simple.SimpleRealEstate;

/**
 * Callback for {@link GetAllRealEstateCommand} and {@link GetAllRealEstateResponse}
 * 
 * @author jDramaix
 *
 */
public  abstract class GotAllRealEstate extends AbstractCallback<GetAllRealEstateResponse>{

	@Override
	public void onSuccess(GetAllRealEstateResponse result) {
		got(result.getRealEstates());
		
	}
	
	public abstract void got(List<SimpleRealEstate> realEstates);

}
