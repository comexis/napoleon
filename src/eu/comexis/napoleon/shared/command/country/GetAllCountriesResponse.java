package eu.comexis.napoleon.shared.command.country;

import java.util.ArrayList;

import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.Country;

public class GetAllCountriesResponse implements Response{

  public GetAllCountriesResponse() {
    // TODO Auto-generated constructor stub
  }
  private ArrayList<Country> countries;
  public ArrayList<Country> getCountries() {
    return countries;
  }
  public void setCountries(ArrayList<Country> countries) {
    this.countries = countries;
  }

}
