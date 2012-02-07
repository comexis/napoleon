package eu.comexis.napoleon.client.rpc.callback;

import eu.comexis.napoleon.shared.command.estate.UpdateRealEstateResponse;
import eu.comexis.napoleon.shared.model.RealEstate;

public abstract class UpdatedRealEstate extends AbstractCallback<UpdateRealEstateResponse> {

  public UpdatedRealEstate() {
    // TODO Auto-generated constructor stub
  }

  public abstract void got(RealEstate realEstate);

  @Override
  public void onSuccess(UpdateRealEstateResponse result) {
    got(result.getRealEstate());
  }
}
