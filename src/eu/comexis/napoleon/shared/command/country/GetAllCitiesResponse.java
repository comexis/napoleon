package eu.comexis.napoleon.shared.command.country;

import java.util.List;

import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.City;

public class GetAllCitiesResponse implements Response {

  private List<City> cities;

  public GetAllCitiesResponse() {
    // TODO Auto-generated constructor stub
  }

  public List<City> getCities() {
    return cities;
  }

  public void setCities(List<City> cities) {
    this.cities = cities;
  }
}
