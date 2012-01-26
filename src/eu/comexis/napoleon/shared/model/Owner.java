package eu.comexis.napoleon.shared.model;

import java.math.BigDecimal;

/**
 * Represent the Owner (Proprio) of a RealEstate (Bien immo) (It's considered to
 * be a single person)
 * 
 * @author xavier
 * 
 */
public class Owner extends Party {

	public static final BigDecimal CONVERSION_FACTOR = new BigDecimal(100);

	private Long fee;
	private String unit;

	public Owner() {
		super();
	}

	public BigDecimal getFee() {
		return new BigDecimal(fee).divide(CONVERSION_FACTOR);
	}

	public String getUnit() {
		return unit;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee.multiply(CONVERSION_FACTOR)
				.longValueExact();
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}
