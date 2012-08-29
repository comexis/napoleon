package eu.comexis.napoleon.shared.command.lease;

import java.util.ArrayList;

import eu.comexis.napoleon.shared.command.Response;

public class GetTenantEmailsByCondoResponse implements Response {

  private ArrayList<String> emailsList;

  public ArrayList<String> getEmailsList() {
    return emailsList;
  }

  public void setLeaseList(ArrayList<String> emailsList) {
    this.emailsList = emailsList;
  }

}
