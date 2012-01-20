package eu.comexis.napoleon.shared.model;


/**
 * represent the user connected (or able to conect to) the application.
 * An user is always linked to a client
 * @author jDramaix
 *
 */
public class AppUser {
	
	private String email;
	private String firstName;
	private String lastName;
	private Client client;
	
	public AppUser() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}	
}
