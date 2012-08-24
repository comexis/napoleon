package eu.comexis.napoleon.shared.command.country;

import com.google.gwt.user.client.rpc.AsyncCallback;

import eu.comexis.napoleon.client.rpc.CountryServiceAsync;
import eu.comexis.napoleon.shared.command.Action;

public class GetCountryCommand implements Action<GetCountryResponse>{
  private String name;
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public GetCountryCommand() {
    // TODO Auto-generated constructor stub
  }
  @Override
  public void dispatch(AsyncCallback<GetCountryResponse> callback) {
    CountryServiceAsync.INSTANCE.execute(this, callback);
  }

}
