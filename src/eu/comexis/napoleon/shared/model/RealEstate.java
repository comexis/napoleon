/**
 * 
 */
package eu.comexis.napoleon.shared.model;

import javax.persistence.Id;
import javax.persistence.PrePersist;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Indexed;


/**
 * @author xavier
 * Bien immobilier
 */
public class RealEstate {

	/**
	 * 
	 */
	@Id
	private Long id;
	public Long getId() {
		return id;
	}
	// Propriétaire
	private Long ownerId;
	public Long getOwnerId(){
		return ownerId;
	}
	public void setOwnerId(Long id){
		ownerId = id;
	}
	// référence
	private String reference;
	public String getReference(){
		return reference;
	}
	public void setReference(String value){
		reference = value;
	}
	// copropriété
	private Long condoId;
	public Long getCondoId(){
		return condoId;
	}
	public void setCondoId(Long id){
		condoId = id;
	}
	// adresse du bien
	private String street;
	public void setStreet(String value){
		street = value;
	}
	public String getStreet(){
		return street;
	}
	// municipalité
	private String city;
	public void setCity(String value){
		city = value;
	}
	public String getCity(){
		return city;
	}
	// code postal
	private String postalCode;
	public void setPostalCode(String value){
		postalCode = value;
	}
	public String getPostalCode(){
		return postalCode;
	}
	// bte
	private String boite; 
	// quartier
	private String square;
	// type de bien
	private String type;
	// état
	private String state;
	// superficie
	private String dimension;
	public RealEstate() {
		// empty constructor needed by GWT
	}
	public RealEstate(String reference) {
		//
	}
	
}
