package eu.comexis.napoleon.shared.model;

import javax.persistence.Id;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Parent;

public class Iban implements IsSerializable {

  @Id
  private String name;
  @Parent
  private Key<Company> company;
  
  public Iban() {
    // TODO Auto-generated constructor stub
  }
  public Key<Company> getCompany() {
    return company;
  }

  public String getName() {
    return name;
  }

  public void setCompany(Key<Company> company) {
    this.company = company;
  }
  public void setName(String name) {
    this.name = name;
  }
}
