package eu.comexis.napoleon.shared.model;

import java.math.BigDecimal;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.annotation.Unindexed;

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
  private String unit;

  public Owner() {
    super();
  }

  public BigDecimal getFee() {
    if (fee != null) {
      return new BigDecimal(fee).divide(CONVERSION_FACTOR);
    } else {
      return new BigDecimal(0);
    }
  }

  public String getUnit() {
    return unit;
  }

  public void setFee(BigDecimal fee) {
    this.fee = fee.multiply(CONVERSION_FACTOR).longValueExact();
  }

  public void setFee(String fee) {
    BigDecimal bdFee = new BigDecimal(fee);
    this.setFee(bdFee);
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }
}
