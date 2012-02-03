package eu.comexis.napoleon.client.rpc.callback;

import java.util.List;
import java.util.logging.Logger;

import eu.comexis.napoleon.shared.command.country.GetAllCountriesResponse;
import eu.comexis.napoleon.shared.model.Country;

public abstract class GotAllCountries extends AbstractCallback<GetAllCountriesResponse>{

  public GotAllCountries() {
    // TODO Auto-generated constructor stub
  }
  @Override
  public void onSuccess(GetAllCountriesResponse result) {
    got(result.getCountries());
    
  }
  public abstract void got(List<Country> countries);
}
