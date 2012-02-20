package eu.comexis.napoleon.shared.command.condo;

import java.util.List;

import eu.comexis.napoleon.shared.command.Response;

public class GetAllCondoResponse implements Response{
  private List<String> condoNames;

  public GetAllCondoResponse() {
    // TODO Auto-generated constructor stub
  }

  public List<String> getCondoNames() {
    return condoNames;
  }

  public void setCondoNames(List<String> condoNames) {
    this.condoNames = condoNames;
  }
}
