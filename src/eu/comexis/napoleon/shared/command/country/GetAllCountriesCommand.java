package eu.comexis.napoleon.shared.command.country;

import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.client.rpc.CountryServiceAsync;
import eu.comexis.napoleon.shared.command.Action;

public class GetAllCountriesCommand implements Action<GetAllCountriesResponse> {

  public GetAllCountriesCommand() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public void dispatch(AsyncCallback<GetAllCountriesResponse> callback) {
    CountryServiceAsync.INSTANCE.execute(this, callback);
  }
}
