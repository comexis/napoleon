/**
 * 
 */
package eu.comexis.napoleon.shared.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.PrePersist;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Indexed;

/**
 * @author xavier
 * Location
 */
public class Lease {

	/**
	 * 
	 */
	public Lease() {
		// TODO Auto-generated constructor stub
	}
	@Id
	private Long id;
	public Long getId() {
		return id;
	}
	private Date startDate;
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date value) {
		startDate = value;
	}
	private Date endDate;
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date value) {
		endDate = value;
	}
	private Long tenantId;
	public Long getTenantId(){
		return tenantId;
	}
	public void setTenantId(Long id){
		tenantId = id;
	}
	private Long realEstateId;
	public Long getRealEstateId(){
		return realEstateId;
	}
	public void setRealEstateId(Long id){
		realEstateId = id;
	}
}
