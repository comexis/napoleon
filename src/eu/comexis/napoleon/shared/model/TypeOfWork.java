package eu.comexis.napoleon.shared.model;

import javax.persistence.Id;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Parent;

public class TypeOfWork {

  public TypeOfWork() {
    // TODO Auto-generated constructor stub
  }
  @Id
  private String name;
  @Parent
  private Key<Company> company;
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
