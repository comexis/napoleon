package eu.comexis.napoleon.shared.command.estate;

import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.Condo;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

public class GetRealEstateResponse implements Response {

  private RealEstate realEstate;

  public GetRealEstateResponse() {
  }

  public RealEstate getRealEstate() {
    return realEstate;
  }

  public void setRealEstate(RealEstate realEstate) {
    this.realEstate = realEstate;
  }

}
