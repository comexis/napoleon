package eu.comexis.napoleon.client.rpc.callback;

import eu.comexis.napoleon.shared.command.country.GetCountryResponse;
import eu.comexis.napoleon.shared.model.Country;

public abstract class GotCountry extends AbstractCallback<GetCountryResponse>{
  public abstract void got(Country cnty);

  @Override
  public void onSuccess(GetCountryResponse result) {
    got(result.getCountry());
  }
}
