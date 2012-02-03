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

	public String getCompanyId() {
		// should return a company id based on the session data
		// for the moment get id from data store + hardcoded name
		CompanyDao companyData = new CompanyDao();
		Company company = companyData.getByName("Agence de l'aiglon");
		System.out.println("Get company by mane: " + company.getId());
		return company.getId();
	}

	/**
	 * retrieve a user linked to an email. If no user exists for this email,
	 * returns null.
	 * 
	 * @param email
	 * @return
	 */
	public ApplicationUser getUser(String email) {

		String id = getCompanyId();
		ApplicationUserDao userData = new ApplicationUserDao(id);
		return userData.getByEMail(email);

	}
}