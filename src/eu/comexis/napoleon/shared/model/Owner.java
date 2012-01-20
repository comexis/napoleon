package eu.comexis.napoleon.shared.model;

import java.math.BigDecimal;

/**
 * Represent the Owner (Proprio) of a RealEstate (Bien immo) (It's considered to be a single person)
 * 
 * @author xavier
 * 	
 */
public class Owner extends Party{
	
	public static final long CONVERSION_FACTOR = 100;
	// honoraire
	private Long fee;
	public void setFee(BigDecimal fee) {
		this.fee=fee.multiply(new BigDecimal(CONVERSION_FACTOR)).longValueExact();
	}
	public BigDecimal getFee() {
		return new BigDecimal(fee).divide(new BigDecimal(CONVERSION_FACTOR));
	}
	// unité ('EUR', '%')
	private String unit;
	public void setUnit(String unit) {
		this.unit=unit;
	}
	public String getUnit() {
		return unit;
	} 
	public Owner() {
		// empty constructor needed by GWT
	}
	public Owner(String name) {
		super(name);
	}
}
