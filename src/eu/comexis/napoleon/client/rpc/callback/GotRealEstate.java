package eu.comexis.napoleon.client.rpc.callback;

import eu.comexis.napoleon.shared.command.estate.GetAllRealEstateCommand;
import eu.comexis.napoleon.shared.command.estate.GetAllRealEstateResponse;
import eu.comexis.napoleon.shared.command.estate.GetRealEstateResponse;
import eu.comexis.napoleon.shared.model.Condo;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

/**
 * Callback for {@link GetAllRealEstateCommand} and {@link GetAllRealEstateResponse}
 * 
 * @author jDramaix
 * 
 */
public abstract class GotRealEstate extends AbstractCallback<GetRealEstateResponse> {

  public abstract void got(RealEstate realEstate);

  @Override
  public void onSuccess(GetRealEstateResponse result) {
    got(result.getRealEstate());
  }

}
