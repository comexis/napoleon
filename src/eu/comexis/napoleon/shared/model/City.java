package eu.comexis.napoleon.shared.model;

import java.util.ArrayList;

import javax.persistence.Id;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Parent;

public class City implements IsSerializable {

  @Id
  private String id;

  @Parent
  private Key<Country> country;
  private String name;
  private ArrayList<String> SquareList;

  public City() {
    // TODO Auto-generated constructor stub
  }

  public Key<Country> getCountry() {
    return country;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public ArrayList<String> getSquareList() {
    return SquareList;
  }

  public void setCountry(Key<Country> country) {
    this.country = country;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSquareList(ArrayList<String> squareList) {
    SquareList = squareList;
  }

}
