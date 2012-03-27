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

import eu.comexis.napoleon.shared.model.simple.SimpleOwner;
import eu.comexis.napoleon.shared.model.simple.SimpleRealEstate;

public class Expense implements IsSerializable, Identifiable, HasFiles {

  @Id
  private String id;
  private Date dateInform;
  private Date dateWork;
  private Date dateInvoice;
  private String reference;
  private Float amount;
  private Float toBePaidByOwner;
  private Float toBePaidByTenant;
  private Boolean isPaidByTenant;
  private Date datePaymentTenant;
  private Key<Contractor> contractorKey;
  @NotSaved
  private Contractor contractor;
  private Date chargedToOwnerPeriod;

  private String typeOfWork;

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
  public Contractor getContractor() {
    return contractor;
  }

  public Key<Contractor> getContractorKey() {
    return contractorKey;
  }

  public Date getDateInform() {
    return dateInform;
  }

  public Date getDateInvoice() {
    return dateInvoice;
  }

  public Date getDatePaymentTenant() {
    return datePaymentTenant;
  }


  public Date getDateWork() {
    return dateWork;
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

  public String getTypeOfWork() {
    return typeOfWork;
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

  public void setContractor(Contractor contractor) {
    this.contractor = contractor;
  }

  public void setContractorKey(Key<Contractor> contractorKey) {
    this.contractorKey = contractorKey;
  }

  public void setDateInform(Date dateInform) {
    this.dateInform = dateInform;
  }

  public void setDateInvoice(Date dateInvoice) {
    this.dateInvoice = dateInvoice;
  }

  public void setDatePaymentTenant(Date datePaymentTenant) {
    this.datePaymentTenant = datePaymentTenant;
  }

  public void setDateWork(Date dateWork) {
    this.dateWork = dateWork;
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

  public void setTypeOfWork(String typeOfWork) {
    this.typeOfWork = typeOfWork;
  }

}
