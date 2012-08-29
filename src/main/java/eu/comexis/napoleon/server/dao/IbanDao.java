package eu.comexis.napoleon.server.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.comexis.napoleon.shared.model.Iban;

public class IbanDao extends NapoleonDao<Iban>{
  public IbanDao(){
    // TODO Auto-generated constructor stub
  }
  public List<String> getNames(String companyId){
    List<String> lst = new ArrayList<String>();
    for (Iban iban:this.listAll(companyId)){
      lst.add(iban.getName());
    }
    Collections.sort(lst);
    return lst;
  }
}
