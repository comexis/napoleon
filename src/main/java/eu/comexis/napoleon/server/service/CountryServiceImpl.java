package eu.comexis.napoleon.server.service;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import eu.comexis.napoleon.client.rpc.CountryService;
import eu.comexis.napoleon.server.dao.CountryDao;
import eu.comexis.napoleon.server.manager.UserManager;
import eu.comexis.napoleon.shared.command.country.GetAllCitiesCommand;
import eu.comexis.napoleon.shared.command.country.GetAllCitiesResponse;
import eu.comexis.napoleon.shared.command.country.GetAllCountriesCommand;
import eu.comexis.napoleon.shared.command.country.GetAllCountriesResponse;
import eu.comexis.napoleon.shared.command.country.GetCountryCommand;
import eu.comexis.napoleon.shared.command.country.GetCountryResponse;
import eu.comexis.napoleon.shared.model.City;
import eu.comexis.napoleon.shared.model.Country;

@SuppressWarnings("serial")
public class CountryServiceImpl extends RemoteServiceServlet implements CountryService {

  public CountryServiceImpl() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public GetAllCitiesResponse execute(GetAllCitiesCommand command) {
    String companyId = UserManager.INSTANCE.getCompanyId();
    CountryDao countryData = new CountryDao();
    List<City> cities = countryData.getListCities(command.getName());
    GetAllCitiesResponse response = new GetAllCitiesResponse();
    response.setCities(cities);

    return response;
  }
  
  @Override
  public GetCountryResponse execute(GetCountryCommand command) {
    String companyId = UserManager.INSTANCE.getCompanyId();
    CountryDao countryData = new CountryDao();
    Country cnty = countryData.getFullCountryByName(command.getName(),companyId);
    GetCountryResponse response = new GetCountryResponse();
    response.setCountry(cnty);

    return response;
  }

  @Override
  public GetAllCountriesResponse execute(GetAllCountriesCommand command) {
    String companyId = UserManager.INSTANCE.getCompanyId();
    CountryDao countryData = new CountryDao();
    ArrayList<Country> countries = countryData.getList(companyId);

    GetAllCountriesResponse response = new GetAllCountriesResponse();
    response.setCountries(countries);

    return response;
  }
}
