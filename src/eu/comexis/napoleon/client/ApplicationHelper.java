package eu.comexis.napoleon.client;

/**
 * Helper keeping data on connected user/client and other static stuff. 
 * 
 * For example : the logout must be created by the server. It will inject into the
 * index.jsp in a javascript array (containing other settings needed by the
 * application). This class will offer a way to read and access to this property. 
 * 
 * @author jDramaix
 * 
 */
// enum is a safe way to create a Singleton
public enum ApplicationHelper {
	INSTANCE;

	private ApplicationHelper() {
		// TODO Auto-generated constructor stub
	}

}
