package eu.comexis.napoleon.server.dao;

import java.util.UUID;

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
    String assocId = assoc.getId();
    if (assocId == null || assocId.length() == 0) {
      UUID uuid = UUID.randomUUID();
      System.out.println("Creating Uuid " + uuid.toString());
      assoc.setId(uuid.toString());
      assoc.setCompany(companyKey);
    }
    return super.update(assoc);
  }
}
