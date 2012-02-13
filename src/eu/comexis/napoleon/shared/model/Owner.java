package eu.comexis.napoleon.shared.model;

import java.math.BigDecimal;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.annotation.NotSaved;
import com.googlecode.objectify.annotation.Unindexed;

import eu.comexis.napoleon.shared.model.simple.SimpleRealEstate;

/**
 * Represent the Owner (Proprio) of a RealEstate (Bien immo) (It's considered to be a single person)
 * 
 * @author xavier
 * 
 */
@Unindexed
public class Owner extends Party implements IsSerializable {

  public static final BigDecimal CONVERSION_FACTOR = new BigDecimal(100);

  private Long fee;

  private FeeUnit unit;

  @NotSaved
  private List<SimpleRealEstate> estates;

  public Owner() {
    super();
  }

  public List<SimpleRealEstate> getEstates() {
    return estates;
  }

  public BigDecimal getFee() {
    if (fee != null) {
      return new BigDecimal(fee).divide(CONVERSION_FACTOR);
    } else {
      return new BigDecimal(0);
    }
  }

  public FeeUnit getUnit() {
    return unit;
  }

  public void setEstates(List<SimpleRealEstate> estates) {
    this.estates = estates;
  }

  public void setFee(BigDecimal fee) {
    this.fee = fee.multiply(CONVERSION_FACTOR).longValueExact();
  }

  public void setFee(String fee) {
    BigDecimal bdFee = new BigDecimal(fee);
    this.setFee(bdFee);
  }

  public void setUnit(FeeUnit unit) {
    this.unit = unit;
  }
}
