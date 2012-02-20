package eu.comexis.napoleon.shared.command.association;

import java.util.List;

import eu.comexis.napoleon.shared.command.Response;

public class GetAllAssocResponse implements Response{

  public GetAllAssocResponse() {
    // TODO Auto-generated constructor stub
  }
  private List<String> homeownerAssociationNames;
  public List<String> getHomeownerAssociationNames() {
    return homeownerAssociationNames;
  }
  public void setHomeownerAssociationNames(List<String> homeownerAssociationNames) {
    this.homeownerAssociationNames = homeownerAssociationNames;
  }

}
