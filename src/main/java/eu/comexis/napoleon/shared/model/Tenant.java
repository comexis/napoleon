package eu.comexis.napoleon.shared.model;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.annotation.NotSaved;
import com.googlecode.objectify.annotation.Unindexed;

import eu.comexis.napoleon.shared.model.simple.SimpleRealEstate;

/**
 * Represent the Tenant (Proprio) of a RealEstate (Bien immo) 
 * 
 * @author xavier
 * 
 */
@Unindexed
public class Tenant extends Party implements IsSerializable {
  @NotSaved
  private SimpleRealEstate realEstate;

  public SimpleRealEstate getRealEstate() {
    return realEstate;
  }

  public void setRealEstate(SimpleRealEstate realEstate) {
    this.realEstate = realEstate;
  }

}
