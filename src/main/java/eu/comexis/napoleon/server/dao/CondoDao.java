package eu.comexis.napoleon.server.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.googlecode.objectify.Key;

import eu.comexis.napoleon.shared.model.Association;
import eu.comexis.napoleon.shared.model.City;
import eu.comexis.napoleon.shared.model.Company;
import eu.comexis.napoleon.shared.model.Condo;
import eu.comexis.napoleon.shared.model.Country;
import eu.comexis.napoleon.shared.model.Owner;
import eu.comexis.napoleon.shared.model.RealEstate;

public class CondoDao extends NapoleonDao<Condo>{

  public CondoDao() {
    super();
  }
  public Condo create(Key<Company> companyKey) {
    Condo cdo = new Condo();
    System.out.println("Set company key " + companyKey.toString());
    cdo.setCompany(companyKey);
    return cdo;
  }
  public Condo create(String companyId) {
    Key<Company> companyKey = new Key<Company>(Company.class, companyId);
    return create(companyKey);
  }
  @Override
  public Condo update(Condo cdo){
    if (cdo.getCompany() != null){
      return update(cdo,cdo.getCompany());
    }else{
      // log error
      LOG.fatal("Parent Company is not set, cannot save condo");
      return null;
    }
  }
  public Condo update(Condo cdo,String companyId){
    Key<Company> companyKey = new Key<Company>(Company.class, companyId);
    return update(cdo,companyKey);
  }
  public Condo update(Condo cdo,Key<Company> companyKey){
    cdo.setCompany(companyKey);
    return super.update(cdo);
  }
  public List<String> getNames(String companyId){
    List<String> lst = new ArrayList<String>();
    for (Condo cdo:this.listAll(companyId)){
      lst.add(cdo.getName());
    }
    return lst;
  }
}
