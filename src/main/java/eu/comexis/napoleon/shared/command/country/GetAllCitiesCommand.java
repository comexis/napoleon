package eu.comexis.napoleon.shared.command.country;

import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.client.rpc.CountryServiceAsync;
import eu.comexis.napoleon.shared.command.Action;

public class GetAllCitiesCommand implements Action<GetAllCitiesResponse> {
  private String name;

  public GetAllCitiesCommand() {
    // TODO Auto-generated constructor stub
  }

  public GetAllCitiesCommand(String name) {
    this.name = name;
  }

  @Override
  public void dispatch(AsyncCallback<GetAllCitiesResponse> callback) {
    CountryServiceAsync.INSTANCE.execute(this, callback);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
