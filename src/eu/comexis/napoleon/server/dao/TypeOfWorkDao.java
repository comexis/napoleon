package eu.comexis.napoleon.server.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.comexis.napoleon.shared.model.TypeOfWork;

public class TypeOfWorkDao extends NapoleonDao<TypeOfWork>{

  public TypeOfWorkDao() {
    // TODO Auto-generated constructor stub
  }
  public List<String> getNames(String companyId){
    List<String> lst = new ArrayList<String>();
    for (TypeOfWork job:this.listAll(companyId)){
      lst.add(job.getName());
    }
    Collections.sort(lst);
    return lst;
  }
}
