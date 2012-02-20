package eu.comexis.napoleon.shared.command.condo;

import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.Condo;

public class GetCondoResponse implements Response {

  private Condo cdo;

  public GetCondoResponse() {
    // TODO Auto-generated constructor stub
  }

  public Condo getCdo() {
    return cdo;
  }

  public void setCdo(Condo cdo) {
    this.cdo = cdo;
  }

}
