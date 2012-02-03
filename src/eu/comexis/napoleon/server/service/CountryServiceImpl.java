package eu.comexis.napoleon.server.service;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import eu.comexis.napoleon.client.rpc.CountryService;
import eu.comexis.napoleon.server.dao.CountryDao;
import eu.comexis.napoleon.server.manager.UserManager;
import eu.comexis.napoleon.shared.command.country.GetAllCitiesCommand;
import eu.comexis.napoleon.shared.command.country.GetAllCitiesResponse;
import eu.comexis.napoleon.shared.command.country.GetAllCountriesCommand;
import eu.comexis.napoleon.shared.command.country.GetAllCountriesResponse;
import eu.comexis.napoleon.shared.model.Country;

@SuppressWarnings("serial")
public class CountryServiceImpl extends RemoteServiceServlet implements CountryService{

  public CountryServiceImpl() {
    // TODO Auto-generated constructor stub
  }
  @Override
  public GetAllCountriesResponse execute(GetAllCountriesCommand command) {
    String companyId = UserManager.INSTANCE.getCompanyId();
    CountryDao countryData = new CountryDao(companyId);
    ArrayList<Country> countries = countryData.getList();

    GetAllCountriesResponse response = new GetAllCountriesResponse();
    response.setCountries(countries);

    return response;
  }
  @Override
  public GetAllCitiesResponse execute(GetAllCitiesCommand command) {
    String companyId = UserManager.INSTANCE.getCompanyId();
    CountryDao countryData = new CountryDao(companyId);
    ArrayList<String> cities = countryData.getListCities(command.getName());

    GetAllCitiesResponse response = new GetAllCitiesResponse();
    response.setCities(cities);

    return response;
  }
}
