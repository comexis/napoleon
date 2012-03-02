package eu.comexis.napoleon.server.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.comexis.napoleon.shared.model.AcademicYear;

public class AcademicYearDao extends NapoleonDao<AcademicYear>{

  public AcademicYearDao() {
    // TODO Auto-generated constructor stub
  }
  public List<String> getNames(String companyId){
    List<String> lst = new ArrayList<String>();
    for (AcademicYear year:this.listAll(companyId)){
      lst.add(year.getName());
    }
    Collections.sort(lst);
    return lst;
  }
}
