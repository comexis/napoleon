package eu.comexis.napoleon.client.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.shared.command.country.GetAllCitiesCommand;
import eu.comexis.napoleon.shared.command.country.GetAllCitiesResponse;
import eu.comexis.napoleon.shared.command.country.GetAllCountriesCommand;
import eu.comexis.napoleon.shared.command.country.GetAllCountriesResponse;
import eu.comexis.napoleon.shared.command.country.GetCountryCommand;
import eu.comexis.napoleon.shared.command.country.GetCountryResponse;

public interface CountryServiceAsync {
  public CountryServiceAsync INSTANCE = GWT.create(CountryService.class);

  void execute(GetAllCitiesCommand command, AsyncCallback<GetAllCitiesResponse> callback);

  void execute(GetAllCountriesCommand command, AsyncCallback<GetAllCountriesResponse> callback);
  
  void execute(GetCountryCommand command, AsyncCallback<GetCountryResponse> callback);
}
