package eu.comexis.napoleon.server.dao;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;

import eu.comexis.napoleon.shared.model.Association;
import eu.comexis.napoleon.shared.model.Company;
import eu.comexis.napoleon.shared.model.Condo;

public class HomeownerAssocDao extends NapoleonDao<Association>{

  public HomeownerAssocDao(){
    super();
  }
  public Association create(Key<Company> companyKey) {
    Association assoc = new Association();
    assoc.setCompany(companyKey);
    return assoc;
  }
  public Association create(String companyId) {
    Key<Company> companyKey = new Key<Company>(Company.class, companyId);
    return create(companyKey);
  }
  @Override
  public Association update(Association assoc){
    if (assoc.getCompany() != null){
      return update(assoc,assoc.getCompany());
    }else{
      // log error
      LOG.fatal("Parent Company is not set, cannot save HomeownerAssocociation");
      return null;
    }
  }
  public Association update(Association assoc,String companyId){
    Key<Company> companyKey = new Key<Company>(Company.class, companyId);
    return update(assoc,companyKey);
  }
  public Association update(Association assoc,Key<Company> companyKey){
    assoc.setCompany(companyKey);
    return super.update(assoc);
  }
  public List<String> getNames(String companyId){
    List<String> lst = new ArrayList<String>();
    for (Association assoc:this.listAll(companyId)){
      lst.add(assoc.getName());
    }
    return lst;
  }
}
