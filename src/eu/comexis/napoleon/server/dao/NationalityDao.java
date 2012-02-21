package eu.comexis.napoleon.server.dao;

import java.util.ArrayList;
import java.util.List;

import eu.comexis.napoleon.shared.model.Nationality;

public class NationalityDao extends NapoleonDao<Nationality>{
  public NationalityDao(){
    // TODO Auto-generated constructor stub
  }
  public List<String> getNames(String companyId){
    List<String> lst = new ArrayList<String>();
    for (Nationality nat:this.listAll(companyId)){
      lst.add(nat.getName());
    }
    return lst;
  }
}
