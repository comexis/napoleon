package eu.comexis.napoleon.shared.model;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.PrePersist;

import com.googlecode.objectify.annotation.Indexed;

/**
 * Une des parties d'un contract de bail. C'est la classe de base étendue par la classe proprio (Owner) et locataire (Tenant)
 * 
 * @author xavier
 * 
 */
public abstract class Party {

	/**
	 * 
	 */
	public Party() {
		// TODO Auto-generated constructor stub
	}
	public Party(String name) {
		// TODO Auto-generated constructor stub
		this.lastName = name;
	}
	@Id
	private Long id;
	public Long getId() {
		return id;
	}
	// civilité (Mrs, Mme, Mlle)
	private String title;
	public void setTitle(String value){
		title = value;
	}
	public String getTitle(){
		return title;
	}
	// prénom
	private String firstName;
	public void setFirstName(String value){
		firstName = value;
	}
	public String getFirstName(){
		return firstName;
	}
	// nom
	@Indexed
	private String lastName;
	public void setLastName(String value){
		lastName = value;
	}
	public String getLastName(){
		return lastName;
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
	// n° de fax
	private String fax;
	public void setFax(String value){
		fax = value;
	}
	public String getFax(){
		return fax;
	}
	// n° de compte bancaire
	private String bankAccountNumber;
	public void setBankAccountNumber(String value){
		bankAccountNumber = value;
	}
	public String getBankAccountNumber(){
		return bankAccountNumber;
	}
	// IBAN
	private String iban;
	public void setIBAN(String value){
		iban = value;
	}
	public String getIBAN(){
		return iban;
	}
	// BIC
	private String bic;
	public void setBIC(String value){
		bic = value;
	}
	public String getBIC(){
		return bic;
	}
	// Statut
	private String status; // personne physique/morale
	public void setStatus(String value){
		status = value;
	}
	public String getStatus(){
		return status;
	}
	// date de naissance
	private Date dateOfBirth;
	public void setDateOfBirth(Date value){
		dateOfBirth = value;
	}
	public Date getDateOfBirth(){
		return dateOfBirth;
	}
	// lieu de naissance
	private String placeOfBirth;
	public void setPlaceOfBirth(String value){
		placeOfBirth = value;
	}
	public String getPlaceOfBirth(){
		return placeOfBirth;
	}
	// nationalité
	private String nationality;
	public void setNationality(String value){
		nationality = value;
	}
	public String getNationality(){
		return nationality;
	}
	// profession
	private String jobTitle;
	public void setJobTitle(String value){
		jobTitle = value;
	}
	public String getJobTitle(){
		return jobTitle;
	}
	// registre national
	private String nationalRegisterNumber;
	public void setNationalRegisterNumber(String value){
		nationalRegisterNumber = value;
	}
	public String getNationalRegisterNumber(){
		return nationalRegisterNumber;
	}
	// marié ? Célibataire ?
	private String maritalStatus;
	public void setMaritalStatus(String value){
		maritalStatus = value;
	}
	public String getMaritalStatus(){
		return maritalStatus;
	}
	// régime matrimonial
	private String matrimonialRegime;
	public void setMatrimonialRegime(String value){
		matrimonialRegime = value;
	}
	public String getMatrimonialRegime(){
		return matrimonialRegime;
	}
	/**
	 * Auto-increment version # whenever persisted
	 */
}
