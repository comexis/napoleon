package eu.comexis.napoleon.server.service;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import eu.comexis.napoleon.client.rpc.SuggestService;
import eu.comexis.napoleon.server.dao.AcademicYearDao;
import eu.comexis.napoleon.server.dao.JobTitleDao;
import eu.comexis.napoleon.server.dao.NationalityDao;
import eu.comexis.napoleon.server.dao.TypeOfWorkDao;
import eu.comexis.napoleon.server.manager.UserManager;
import eu.comexis.napoleon.shared.command.suggest.GetAllSuggestCommand;
import eu.comexis.napoleon.shared.command.suggest.GetAllSuggestResponse;

public class SuggestServiceImpl extends RemoteServiceServlet implements SuggestService{

  public SuggestServiceImpl() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public GetAllSuggestResponse execute(GetAllSuggestCommand command) {
    // TODO Auto-generated method stub
    String companyId = UserManager.INSTANCE.getCompanyId();
    List<String> lst = null;
    if (command.getName().equals("JobTitle")){
      JobTitleDao jobDao = new JobTitleDao();
      lst = jobDao.getNames(companyId);
    }else if (command.getName().equals("Nationality")){
      NationalityDao natDao = new NationalityDao();
      lst = natDao.getNames(companyId);
    }else if (command.getName().equals("AcademicYear")){
      AcademicYearDao ayDao = new AcademicYearDao();
      lst = ayDao.getNames(companyId);
    }else if (command.getName().equals("TypeOfWork")){
      TypeOfWorkDao twDao = new TypeOfWorkDao();
      lst = twDao.getNames(companyId);
    }
    GetAllSuggestResponse response = new GetAllSuggestResponse();
    response.setSuggestList(lst);
    return response;
  }

}
