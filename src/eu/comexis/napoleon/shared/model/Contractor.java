package eu.comexis.napoleon.shared.model;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.NotSaved;
import com.googlecode.objectify.annotation.Parent;

import javax.persistence.Id;

public class Contractor implements IsSerializable , Identifiable{
  @Id
  private String id;
  @Parent
  private Key<Company> company;
  @NotSaved
  private String companyId;
  
  public String getCompanyId() {
    return companyId;
  }
  public void setCompanyId(String companyId) {
    this.companyId = companyId;
  }
  public Key<Company> getCompany() {
    return company;
  }
  public void setCompany(Key<Company> company) {
    this.company = company;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public Contractor() {
    // TODO Auto-generated constructor stub
  }

}
