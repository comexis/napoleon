package eu.comexis.napoleon.server.dao;

import java.util.UUID;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Query;

import eu.comexis.napoleon.shared.model.ApplicationUser;
import eu.comexis.napoleon.shared.model.Client;
import eu.comexis.napoleon.shared.model.Company;

public class ApplicationUserDao  extends NapoleonDao<ApplicationUser>{
  
  public ApplicationUserDao(String companyId) {
    super(companyId);
  }
  public ApplicationUser create() {
    ApplicationUser user = new ApplicationUser();
    System.out.println("Set company key " + companyKey.toString());
    user.setCompany(companyKey);
    return user;
  }
  @Override
  public ApplicationUser update(ApplicationUser user) {
    String ownerId = user.getId();
    
    if (ownerId == null || ownerId.length() == 0){
      UUID uuid = UUID.randomUUID();
      System.out.println("Creating Uuid " + uuid.toString());
      user.setId(uuid.toString());
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
      e.printStackTrace();
      return null;
    }
  }
}
