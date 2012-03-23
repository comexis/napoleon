package eu.comexis.napoleon.shared.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Id;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.NotSaved;
import com.googlecode.objectify.annotation.Parent;

public class Expense implements IsSerializable, Identifiable, HasFiles {

  @Id
  private String id;
  private Date datePrevenue;
  private Date dateIntervention;
  private Date dateFacture;
  private String reference;
  private Float amount;
  private Float toBePaidByOwner;
  private Float toBePaidByTenant;
  private Boolean isPaidByTenant;
  private Date datePaymentTenant;
  private Key<Contractor> contractor;
  private String contractorId;
  private Date chargedToOwnerPeriod;

  @Parent
  private Key<RealEstate> realEstateKey;
  @NotSaved
  private String realEstateId;

  @Embedded
  private ArrayList<FileDescriptor> files;

  public Expense() {
    files = new ArrayList<FileDescriptor>();
  }

  @Override
  public void addFile(FileDescriptor file) {
    files.add(file);

  }

  public Float getAmount() {
    return amount;
  }

  public Date getChargedToOwnerPeriod() {
    return chargedToOwnerPeriod;
  }

  public Key<Contractor> getContractor() {
    return contractor;
  }

  public String getContractorId() {
    return contractorId;
  }

  public Date getDateFacture() {
    return dateFacture;
  }

  public Date getDateIntervention() {
    return dateIntervention;
  }

  public Date getDatePaymentTenant() {
    return datePaymentTenant;
  }

  public Date getDatePrevenue() {
    return datePrevenue;
  }

  @Override
  public List<FileDescriptor> getFiles() {
    return files;
  }

  @Override
  public String getId() {
    return id;
  }

  public Boolean getIsPaidByTenant() {
    return isPaidByTenant;
  }

  public String getRealEstateId() {
    return realEstateId;
  }

  public Key<RealEstate> getRealEstateKey() {
    return realEstateKey;
  }

  public String getReference() {
    return reference;
  }

  public Float getToBePaidByOwner() {
    return toBePaidByOwner;
  }

  public Float getToBePaidByTenant() {
    return toBePaidByTenant;
  }

  @Override
  public void removeFile(FileDescriptor file) {
    files.remove(file);

  }

  public void setAmount(Float amount) {
    this.amount = amount;
  }

  public void setChargedToOwnerPeriod(Date chargedToOwnerPeriod) {
    this.chargedToOwnerPeriod = chargedToOwnerPeriod;
  }

  public void setContractor(Key<Contractor> contractor) {
    this.contractor = contractor;
  }

  public void setContractorId(String contractorId) {
    this.contractorId = contractorId;
  }

  public void setDateFacture(Date dateFacture) {
    this.dateFacture = dateFacture;
  }

  public void setDateIntervention(Date dateIntervention) {
    this.dateIntervention = dateIntervention;
  }

  public void setDatePaymentTenant(Date datePaymentTenant) {
    this.datePaymentTenant = datePaymentTenant;
  }

  public void setDatePrevenue(Date datePrevenue) {
    this.datePrevenue = datePrevenue;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setIsPaidByTenant(Boolean isPaidByTenant) {
    this.isPaidByTenant = isPaidByTenant;
  }

  public void setRealEstateId(String realEstateId) {
    this.realEstateId = realEstateId;
  }

  public void setRealEstateKey(Key<RealEstate> realEstateKey) {
    this.realEstateKey = realEstateKey;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public void setToBePaidByOwner(Float toBePaidByOwner) {
    this.toBePaidByOwner = toBePaidByOwner;
  }

  public void setToBePaidByTenant(Float toBePaidByTenant) {
    this.toBePaidByTenant = toBePaidByTenant;
  }

}
