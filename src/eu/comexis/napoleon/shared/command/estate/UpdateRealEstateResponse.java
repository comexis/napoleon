package eu.comexis.napoleon.shared.command.estate;

import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.RealEstate;

public class UpdateRealEstateResponse implements Response {

  private RealEstate realEstate;

  public UpdateRealEstateResponse() {
    // TODO Auto-generated constructor stub
  }

  public RealEstate getRealEstate() {
    return realEstate;
  }

  public void setRealEstate(RealEstate realEstate) {
    this.realEstate = realEstate;
  }
}
