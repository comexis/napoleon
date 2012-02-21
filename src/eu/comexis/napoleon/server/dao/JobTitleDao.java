package eu.comexis.napoleon.server.dao;

import java.util.ArrayList;
import java.util.List;

import eu.comexis.napoleon.shared.model.JobTitle;

public class JobTitleDao extends NapoleonDao<JobTitle>{

  public JobTitleDao() {
    // TODO Auto-generated constructor stub
  }
  public List<String> getNames(String companyId){
    List<String> lst = new ArrayList<String>();
    for (JobTitle job:this.listAll(companyId)){
      lst.add(job.getName());
    }
    return lst;
  }
}
