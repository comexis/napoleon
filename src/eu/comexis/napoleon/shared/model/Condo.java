/**
 * 
 */
package eu.comexis.napoleon.shared.model;

import javax.persistence.Id;
import javax.persistence.PrePersist;

/**
 * @author xavier
 * Coopropriété (Condominium)
 */
public class Condo {

	/**
	 * 
	 */
	public Condo() {
		// empty constructor needed by GWT
	}
	@Id
	private Long id;
	public Long getId() {
		return id;
	}
	// Syndicat de copropriété.
	private String homeownerAssociation;
	public void setHomeownerAssociation(String value){
		homeownerAssociation = value;
	}
	public String getHomeownerAssociation(){
		return homeownerAssociation;
	}
	// adresse
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
	// pays
	private String country;
	public void setCountry(String value){
		country = value;
	}
	public String getCountry(){
		return country;
	}
	// adresse e-mail
	private String email;
	public void setEmail(String value){
		email = value;
	}
	public String getEmail(){
		return email;
	}
	// n° de téléphone
	private String phoneNumber;
	public void setPhoneNumber(String value){
		phoneNumber = value;
	}
	public String getPhoneNumber(){
		return phoneNumber;
	}
	// n° de téléphone mobile (GSM)
	private String mobilePhoneNumber;
	public void setMobilePhoneNumber(String value){
		mobilePhoneNumber = value;
	}
	public String getMobilePhoneNumber(){
		return mobilePhoneNumber;
	}
}
