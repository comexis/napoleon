package eu.comexis.napoleon.client.rpc.callback;

import java.util.List;

import eu.comexis.napoleon.shared.command.country.GetAllCitiesResponse;
import eu.comexis.napoleon.shared.model.City;

public abstract class GotAllCities extends AbstractCallback<GetAllCitiesResponse> {
  public GotAllCities() {
    // TODO Auto-generated constructor stub
  }

  public abstract void got(List<City> cities);

  @Override
  public void onSuccess(GetAllCitiesResponse result) {
    got(result.getCities());

  }
}
