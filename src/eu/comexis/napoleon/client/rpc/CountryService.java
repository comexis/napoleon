package eu.comexis.napoleon.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import eu.comexis.napoleon.shared.command.country.GetAllCitiesCommand;
import eu.comexis.napoleon.shared.command.country.GetAllCitiesResponse;
import eu.comexis.napoleon.shared.command.country.GetAllCountriesCommand;
import eu.comexis.napoleon.shared.command.country.GetAllCountriesResponse;

@RemoteServiceRelativePath("country")
public interface CountryService extends RemoteService {
  public GetAllCitiesResponse execute(GetAllCitiesCommand command);

  public GetAllCountriesResponse execute(GetAllCountriesCommand command);
}
