package eu.comexis.napoleon.shared.model;

import javax.persistence.Id;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Parent;

public class ApplicationUser {

  @Id
  private String id;
  private String email;
  private String firstName;
  private String lastName;
  @Parent
  private Key<Company> company;

  public ApplicationUser() {
  }

  public Key<Company> getCompany() {
    return company;
  }

  public String getEmail() {
    return email;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getId() {
    return id;
  }

  public String getLastName() {
    return lastName;
  }

  public void setCompany(Key<Company> company) {
    this.company = company;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
