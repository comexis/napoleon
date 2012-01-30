package eu.comexis.napoleon.server.manager;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import eu.comexis.napoleon.server.dao.ApplicationUserDao;
import eu.comexis.napoleon.server.dao.CompanyDao;
import eu.comexis.napoleon.shared.model.AppUser;
import eu.comexis.napoleon.shared.model.ApplicationUser;
import eu.comexis.napoleon.shared.model.Client;
import eu.comexis.napoleon.shared.model.Company;

/**
 * Class uses for manage interactions with user and client entities !!!
 * Should call the datastore via objectify to retrieve information concerning user and client.
 * 
 * @author jDramaix
 *
 */
//enum is a thread-safe and lazy-loading way to implements a Singleton !!!
public enum UserManager {
	INSTANCE; 
	

	private UserManager() {
	}
	
	/**
	 * Return the logged in user or null if the user is not logged in or doesn't exist
	 * @return
	 */
	public AppUser getConnectedUser(){
		UserService userService = UserServiceFactory.getUserService();

		//if user logged !!
		if (userService.isUserLoggedIn()) {
			return getUser(userService.getCurrentUser().getEmail());
		}else{
			//maybe throw an exception here to differenciate the case where the user is not logged or doesn't exist.
			return null;
		}
	}
	public String getCompanyId(){
	  // should return a company id based on the session data
	  // for the moment get id from data store + hardcoded name
	  CompanyDao companyData = new CompanyDao();
    Company company = companyData.getByName("Agence de l'aiglon");
    System.out.println("Get company by mane: " + company.getId());
    return company.getId();
	}
	/**
	 * retrieve a user linked to an email. If no user exists for this email, returns null.
	 * 
	 * @param email
	 * @return
	 */
	public AppUser getUser(String email){
		//for the moment I harcode the user... Change this code to fetch the user in the datastore
	  
		/*AppUser user = new AppUser();
		user.setEmail(email);
		user.setFirstName("biloute");
		user.setLastName("De Course");
		
		//client should be also comes from the datastore
		Client c = new Client();
		c.setName("Agence de l'aiglon");
		c.setAddress("Passage de l'Ergot, 44 - 1348 Louvain-la-Neuve");
		c.setEmail("aiglon@skynet.be");
		c.setUrl("http://www.aiglon.be");
		c.setTelephone("010/45.51.00");
		c.setFax(" 010/45.59.58");
		
		user.setClient(c);*/
	  try{
	    CompanyDao companyData = new CompanyDao();
	    String id = getCompanyId();
	    Company company = companyData.getById(getCompanyId());
	    ApplicationUserDao userData = new ApplicationUserDao(id);
	    ApplicationUser u = userData.getByEMail(email);
	    AppUser user = u.toAppUser();
	    user.setClient(company.toClient());
	    return user;
	  }catch (Exception e){
	    e.printStackTrace();
	    return null;
	  }
		
	}
}