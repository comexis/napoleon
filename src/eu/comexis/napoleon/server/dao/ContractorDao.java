package eu.comexis.napoleon.server.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.googlecode.objectify.Key;

import eu.comexis.napoleon.shared.model.Association;
import eu.comexis.napoleon.shared.model.City;
import eu.comexis.napoleon.shared.model.Company;
import eu.comexis.napoleon.shared.model.Condo;
import eu.comexis.napoleon.shared.model.Contractor;
import eu.comexis.napoleon.shared.model.Country;
import eu.comexis.napoleon.shared.model.Owner;
import eu.comexis.napoleon.shared.model.RealEstate;

public class ContractorDao extends NapoleonDao<Contractor>{

  public ContractorDao() {
    super();
  }
  public Contractor create(Key<Company> companyKey) {
    Contractor cdo = new Contractor();
    System.out.println("Set company key " + companyKey.toString());
    cdo.setCompany(companyKey);
    return cdo;
  }
  public Contractor create(String companyId) {
    Key<Company> companyKey = new Key<Company>(Company.class, companyId);
    return create(companyKey);
  }
  @Override
  public Contractor update(Contractor cdo){
    if (cdo.getCompany() != null){
      return update(cdo,cdo.getCompany());
    }else{
      // log error
      LOG.fatal("Parent Company is not set, cannot save Contractor");
      return null;
    }
  }
  public Contractor update(Contractor cdo,String companyId){
    Key<Company> companyKey = new Key<Company>(Company.class, companyId);
    return update(cdo,companyKey);
  }
  public Contractor update(Contractor cdo,Key<Company> companyKey){
    cdo.setCompany(companyKey);
    return super.update(cdo);
  }
}
