package eu.comexis.napoleon.server.dao;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.logging.client.LogConfiguration;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Query;

import eu.comexis.napoleon.server.manager.UserManager;
import eu.comexis.napoleon.shared.model.ApplicationUser;
import eu.comexis.napoleon.shared.model.Client;
import eu.comexis.napoleon.shared.model.Company;

public class ApplicationUserDao  extends NapoleonDao<ApplicationUser>{
  Logger logger = Logger.getLogger(ApplicationUserDao.class.getName());
  public ApplicationUserDao(String companyId) {
    super(companyId);
  }
  public ApplicationUser create() {
    try{
      ApplicationUser user = new ApplicationUser();
      user.setCompany(companyKey);
      return user;
    }catch(Exception e){
       logger.severe("Cannot create ApplicationUser: " + e.getMessage());
      return null;
    }
  }
  @Override
  public ApplicationUser update(ApplicationUser user) {
    String userId = user.getId();
    
    if (userId == null || userId.length() == 0){
      UUID uuid = UUID.randomUUID();
      user.setId(uuid.toString());
      logger.info("Creating new Application user with Uuid " + uuid.toString());
    }else{
      logger.info("Update Application user with Uuid " + userId);
    }
    return super.update(user);
  }
  public ApplicationUser getByEMail(String email){
    try{
      Query<ApplicationUser> query = ofy().query(ApplicationUser.class);
      query.ancestor(companyKey);
      ApplicationUser user = query.filter("email", email).get();
      return user;
    }catch(Exception e){
      logger.warning("Cannot get ApplicationUser by email(" + email + "): " + e.getMessage());
      return null;
    }
  }
}
