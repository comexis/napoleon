package eu.comexis.napoleon.shared.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Id;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Parent;

public class Expense implements IsSerializable , Identifiable, HasFiles{

  @Id
  private String id;
  private Date datePrevenue;
  //private TypeIntervention datePrevenue;
  private Date dateIntervention;
  private Date dateFacture;
  private String reference;
  private Float amount;
  private Float toBePaidByOwner;
  private Float toBePaidByTenant;
  private Boolean isPaidByTenant;
  private Date datePaymentTenant;
  @Parent
  private Key<RealEstate> realEstateKey;

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
