package eu.comexis.napoleon.shared.model;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Id;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.NotSaved;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.annotation.Unindexed;

import eu.comexis.napoleon.shared.model.simple.SimpleOwner;

/**
 * @author xavier Bien immobilier
 */
@Unindexed
public class RealEstate implements IsSerializable , Identifiable, HasFiles{

  @Id
  private String id;

  @Parent
  private Key<Company> company;

  private String box;

  private String number;
  @Indexed
  private Boolean flagActivated;

  private String city;

  private String country;

  private String street;

  private Date ownershipDate;

  private String dimension;

  private String reference;

  private String square;

  private RealEstateState state;

  private TypeOfRealEstate type;

  // nom de la compropriété
  private String condominium;

  // syndic de copropriété
  private String homeownerAssociation;

  // adresse du syndic
  private String assocAdresss;

  // adresse e-mail du syndic
  private String assocEmail;

  // n° de téléphone du syndic
  private String assocPhoneNumber;
  
  // n° de téléphone mobile (GSM) du syndic
  private String assocMobilePhoneNumber;
  @Indexed
  private Key<Owner> ownerKey;

  @NotSaved
  private SimpleOwner owner;

  private String postalCode;
  
  @Embedded
  private ArrayList<FileDescriptor> files;
  public RealEstate() {
    files = new ArrayList<FileDescriptor>();
  }
  @Override
  public void addFile(FileDescriptor file) {
    files.add(file);
  }
  public String getAssocAdresss() {
    return assocAdresss;
  }
  public String getAssocEmail() {
    return assocEmail;
  }

  public String getAssocMobilePhoneNumber() {
    return assocMobilePhoneNumber;
  }

  public String getAssocPhoneNumber() {
    return assocPhoneNumber;
  }

  public String getBox() {
    return box;
  }

  public String getCity() {
    return city;
  }

  public Key<Company> getCompany() {
    return company;
  }

  public String getCondominium() {
    return condominium;
  }

  public String getCountry() {
    return country;
  }

  public String getDimension() {
    return dimension;
  }

  @Override
  public ArrayList<FileDescriptor> getFiles() {
    return files;
  }

  public Boolean getFlagActivated() {
    return flagActivated;
  }

  public String getHomeownerAssociation() {
    return homeownerAssociation;
  }

  public String getId() {
    return id;
  }

  public String getNumber() {
    return number;
  }

  public SimpleOwner getOwner() {
    return owner;
  }

  public Key<Owner> getOwnerKey() {
    return ownerKey;
  }

  public Date getOwnershipDate() {
    return ownershipDate;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public String getReference() {
    return reference;
  }

  public String getSquare() {
    return square;
  }

  public RealEstateState getState() {
    return state;
  }

  public String getStreet() {
    return street;
  }

  public TypeOfRealEstate getType() {
    return type;
  }

  @Override
  public void removeFile(FileDescriptor file) {
    files.remove(file);
  }

  public void setAssocAdresss(String assocAdresss) {
    this.assocAdresss = assocAdresss;
  }

  public void setAssocEmail(String assocEmail) {
    this.assocEmail = assocEmail;
  }

  public void setAssocMobilePhoneNumber(String assocMobilePhoneNumber) {
    this.assocMobilePhoneNumber = assocMobilePhoneNumber;
  }

  public void setAssocPhoneNumber(String assocPhoneNumber) {
    this.assocPhoneNumber = assocPhoneNumber;
  }

  public void setBox(String box) {
    this.box = box;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setCompany(Key<Company> company) {
    this.company = company;
  }

  public void setCondominium(String condominium) {
    this.condominium = condominium;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public void setDimension(String dimension) {
    this.dimension = dimension;
  }

  public void setFiles(ArrayList<FileDescriptor> files) {
    this.files = files;
  }

  public void setFlagActivated(Boolean flagActivated) {
    this.flagActivated = flagActivated;
  }

  public void setHomeownerAssociation(String homeownerAssociation) {
    this.homeownerAssociation = homeownerAssociation;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public void setOwner(SimpleOwner owner) {
    this.owner = owner;
  }

  public void setOwnerKey(Key<Owner> ownerKey) {
    this.ownerKey = ownerKey;
  }

  public void setOwnershipDate(Date ownershipDate) {
    this.ownershipDate = ownershipDate;
  }

  public void setPostalCode(String string) {
    this.postalCode = string;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public void setSquare(String square) {
    this.square = square;
  }

  public void setState(RealEstateState state) {
    this.state = state;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public void setType(TypeOfRealEstate type) {
    this.type = type;
  }
}
