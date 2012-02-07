package eu.comexis.napoleon.shared.command.country;

import java.util.ArrayList;

import eu.comexis.napoleon.shared.command.Response;

public class GetAllCitiesResponse implements Response {

  private ArrayList<String> cities;

  public GetAllCitiesResponse() {
    // TODO Auto-generated constructor stub
  }

  public ArrayList<String> getCities() {
    return cities;
  }

  public void setCities(ArrayList<String> cities) {
    this.cities = cities;
  }
}
