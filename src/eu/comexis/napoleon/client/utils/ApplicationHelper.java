package eu.comexis.napoleon.client.utils;

import com.google.gwt.core.client.JsArrayMixed;

import eu.comexis.napoleon.shared.model.AppUser;
import eu.comexis.napoleon.shared.model.Client;

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

	private AppUser user;
	private String logoutUrl;
	
	private ApplicationHelper() {
		readStaticInfo();
	}

	public AppUser getLoggedUser() {
		return user;
	}
	
	public String getLogoutUrl() {
		return logoutUrl;
	}
	
	private void readStaticInfo(){
		user = getUserFromJs();
		logoutUrl = getLogoutUrlFromJs();
	}
	
	private AppUser getUserFromJs() {
		AppUser userFromJs = new AppUser();
		JsArrayMixed array = getJsonUserFromJs();
		userFromJs = new AppUser();
		userFromJs.setEmail(array.getString(0));
		userFromJs.setFirstName(array.getString(1));
		userFromJs.setLastName(array.getString(2));
		
		Client c = new Client();
		JsArrayMixed clientArray = array.getObject(3).cast();
		int i = 0;
		c.setId(clientArray.getString(i++));
		c.setAddress(clientArray.getString(i++));
		c.setEmail(clientArray.getString(i++));
		c.setFax(clientArray.getString(i++));
		c.setName(clientArray.getString(i++));
		c.setTelephone(clientArray.getString(i++));
		c.setUrl(clientArray.getString(i++));
		
		userFromJs.setClient(c);
		
		return userFromJs;
	}

	private native JsArrayMixed getJsonUserFromJs()/*-{
		return $wnd.__GLOBALS[0];
	}-*/;
	
	
	private native String getLogoutUrlFromJs()/*-{
		return $wnd.__GLOBALS[1];
	}-*/;

}
