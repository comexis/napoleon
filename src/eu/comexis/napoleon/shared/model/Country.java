package eu.comexis.napoleon.shared.model;

import javax.persistence.Id;

public class Country {
  @Id
  private Long id;

  private String name;

  public Country() {
    // TODO Auto-generated constructor stub
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
