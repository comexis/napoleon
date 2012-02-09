package eu.comexis.napoleon.shared.command.estate;

import eu.comexis.napoleon.shared.command.Response;
import eu.comexis.napoleon.shared.model.Condo;
import eu.comexis.napoleon.shared.model.RealEstate;
import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

public class GetRealEstateResponse implements Response {

  private RealEstate realEstate;
  private SimpleOwner owner;
  private Condo condo;

  public GetRealEstateResponse() {
  }

  public Condo getCondo() {
    return condo;
  }

  public SimpleOwner getOwner() {
    return owner;
  }

  public RealEstate getRealEstate() {
    return realEstate;
  }

  public void setCondo(Condo condo) {
    this.condo = condo;
  }

  public void setOwner(SimpleOwner owner) {
    this.owner = owner;
  }

  public void setRealEstate(RealEstate realEstate) {
    this.realEstate = realEstate;
  }

}
