package eu.comexis.napoleon.client.rpc.callback;

import java.util.List;

import eu.comexis.napoleon.shared.command.country.GetAllCitiesResponse;

public abstract class GotAllCities extends AbstractCallback<GetAllCitiesResponse> {
  public GotAllCities() {
    // TODO Auto-generated constructor stub
  }

  public abstract void got(List<String> cities);

  @Override
  public void onSuccess(GetAllCitiesResponse result) {
    got(result.getCities());

  }
}
