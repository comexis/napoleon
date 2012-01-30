/**
 * 
 */
package eu.comexis.napoleon.shared.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.PrePersist;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Parent;

/**
 * @author xavier
 * Location
 */
public class Lease implements IsSerializable{

	/**
	 * 
	 */
	public Lease() {
		// TODO Auto-generated constructor stub
	}
	@Id
  private String id;
  @Parent
  private Key<Company> company;
	private Date startDate;
	private Date endDate;
	private Key<Tenant> tenant;
	private Key<RealEstate> realEstate;
}
