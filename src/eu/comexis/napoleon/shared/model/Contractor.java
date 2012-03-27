package eu.comexis.napoleon.shared.model;

import java.util.ArrayList;

import javax.persistence.Embedded;
import javax.persistence.Id;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.NotSaved;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.annotation.Unindexed;
@Unindexed
public class Contractor implements IsSerializable {
  @Id
  private String name;
  @Parent
  private Key<Company> company;
  @NotSaved
  private String companyId;
  private String phone;
  private String mobile;
  private String account;
  private ArrayList<String> skills;

  public Contractor() {
    // TODO Auto-generated constructor stub
    skills = new ArrayList<String>();
  }

  public ArrayList<String> getSkills() {
    return skills;
  }
  public void addSkill(String skill){
    if (skill!=null && !skill.isEmpty() && !this.skills.contains(skill)){
      skills.add(skill);
    }
  }
  public Boolean hasSkill(String skill){
    return this.skills.contains(skill);
  }
  public void setSkills(ArrayList<String> skills) {
    this.skills = skills;
  }

  public String getAccount() {
    return account;
  }

  public Key<Company> getCompany() {
    return company;
  }

  public String getCompanyId() {
    return companyId;
  }

  public String getName() {
    return name;
  }

  public String getMobile() {
    return mobile;
  }

  public String getPhone() {
    return phone;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public void setCompany(Key<Company> company) {
    this.company = company;
  }

  public void setCompanyId(String companyId) {
    this.companyId = companyId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

}
