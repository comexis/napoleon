package eu.comexis.napoleon.server.manager;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import eu.comexis.napoleon.server.dao.ApplicationUserDao;
import eu.comexis.napoleon.server.dao.CompanyDao;
import eu.comexis.napoleon.shared.model.ApplicationUser;
import eu.comexis.napoleon.shared.model.Company;

/**
 * Class uses for manage interactions with user and client entities !!! Should
 * call the datastore via objectify to retrieve information concerning user and
 * client.
 * 
 * @author jDramaix
 * 
 */
// enum is a thread-safe and lazy-loading way to implements a Singleton !!!
public enum UserManager {
	INSTANCE;

	private UserManager() {
	}

	/**
	 * Return the logged in user or null if the user is not logged in or doesn't
	 * exist
	 * 
	 * @return
	 */
	public ApplicationUser getConnectedUser() {
		UserService userService = UserServiceFactory.getUserService();

		// if user logged !!
		if (userService.isUserLoggedIn()) {
			return getUser(userService.getCurrentUser().getEmail());
		} else {
			// maybe throw an exception here to differenciate the case where the
			// user is not logged or doesn't exist.
			return null;
		}
	}
	
	public Company getConnectedCompany(){
		CompanyDao companyData = new CompanyDao();
		return companyData.getById(getConnectedUser().getCompany().getName());	
	}

	public String getCompanyId() {
		return getConnectedUser().getCompany().getName();
	}
	

	/**
	 * retrieve a user linked to an email. If no user exists for this email,
	 * returns null.
	 * 
	 * @param email
	 * @return
	 */
	public ApplicationUser getUser(String email) {
		ApplicationUserDao userData = new ApplicationUserDao();
		return userData.getByEMail(email);

	}
}