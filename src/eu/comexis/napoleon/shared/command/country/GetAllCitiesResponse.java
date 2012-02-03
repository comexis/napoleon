package eu.comexis.napoleon.shared.command.country;

import java.util.ArrayList;

import eu.comexis.napoleon.shared.command.Response;

public class GetAllCitiesResponse implements Response{

  public GetAllCitiesResponse() {
    // TODO Auto-generated constructor stub
  }
  private ArrayList<String> cities;
  public ArrayList<String> getCities() {
    return cities;
  }
  public void setCities(ArrayList<String> cities) {
    this.cities = cities;
  }
}
