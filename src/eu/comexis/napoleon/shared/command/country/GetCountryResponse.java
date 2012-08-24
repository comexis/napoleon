package eu.comexis.napoleon.shared.command.country;

import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.Country;

public class GetCountryResponse implements Response {

  public GetCountryResponse() {
    // TODO Auto-generated constructor stub
  }
  private Country country;
  public Country getCountry() {
    return country;
  }
  public void setCountry(Country country) {
    this.country = country;
  }
  

}
