package eu.comexis.napoleon.shared.command.association;

import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.Association;

public class GetAssocResponse implements Response{

  public GetAssocResponse() {
    // TODO Auto-generated constructor stub
  }
  private Association homeownerAssociation;
  public Association getHomeownerAssociation() {
    return homeownerAssociation;
  }
  public void setHomeownerAssociation(Association homeownerAssociation) {
    this.homeownerAssociation = homeownerAssociation;
  }

}
