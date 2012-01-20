package eu.comexis.napoleon.server.manager;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import eu.comexis.napoleon.shared.model.AppUser;
import eu.comexis.napoleon.shared.model.Client;

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
	
	/**
	 * retrieve a user linked to an email. If no user exists for this email, returns null.
	 * 
	 * @param email
	 * @return
	 */
	public AppUser getUser(String email){
		//for the moment I harcode the user... Change this code to fetch the user in the datastore
		AppUser user = new AppUser();
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
		
		user.setClient(c);
		
		return user;
		
	}
}