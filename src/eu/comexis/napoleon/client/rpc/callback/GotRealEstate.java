package eu.comexis.napoleon.client.rpc.callback;

import java.util.logging.Logger;

import eu.comexis.napoleon.shared.command.estate.GetAllRealEstateCommand;
import eu.comexis.napoleon.shared.command.estate.GetAllRealEstateResponse;
import eu.comexis.napoleon.shared.command.estate.GetRealEstateResponse;
import eu.comexis.napoleon.shared.model.RealEstate;

/**
 * Callback for {@link GetAllRealEstateCommand} and {@link GetAllRealEstateResponse}
 * 
 * @author jDramaix
 *
 */
public  abstract class GotRealEstate extends AbstractCallback<GetRealEstateResponse>{

	@Override
	public void onSuccess(GetRealEstateResponse result) {
		got(result.getRealEstate());
	}
	
	public abstract void got(RealEstate realEstate);

}
