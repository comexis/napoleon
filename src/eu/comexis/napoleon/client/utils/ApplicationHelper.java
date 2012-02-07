package eu.comexis.napoleon.client.utils;

import com.google.gwt.core.client.JsArrayMixed;

import eu.comexis.napoleon.shared.model.ApplicationUser;
import eu.comexis.napoleon.shared.model.Company;

/**
 * Helper keeping data on connected user/client and other static stuff.
 * 
 * For example : the logout must be created by the server. It will inject into the index.jsp in a
 * javascript array (containing other settings needed by the application). This class will offer a
 * way to read and access to this property.
 * 
 * @author jDramaix
 * 
 */
// enum is a safe way to create a Singleton
public enum ApplicationHelper {
  INSTANCE;

  private ApplicationUser user;
  private Company company;
  private String logoutUrl;

  private ApplicationHelper() {
    readStaticInfo();
  }

  public Company getLoggedCompany() {
    return company;
  }

  public ApplicationUser getLoggedUser() {
    return user;
  }

  public String getLogoutUrl() {
    return logoutUrl;
  }

  private native JsArrayMixed getJsonUserFromJs()/*-{
                                                 return $wnd.__GLOBALS[0];
                                                 }-*/;

  private native String getLogoutUrlFromJs()/*-{
                                            return $wnd.__GLOBALS[1];
                                            }-*/;

  private void readStaticInfo() {

    user = new ApplicationUser();
    JsArrayMixed array = getJsonUserFromJs();
    user.setEmail(array.getString(0));
    user.setFirstName(array.getString(1));
    user.setLastName(array.getString(2));

    company = new Company();
    JsArrayMixed clientArray = array.getObject(3).cast();
    int i = 0;
    company.setId(clientArray.getString(i++));
    company.setAddress(clientArray.getString(i++));
    company.setEmail(clientArray.getString(i++));
    company.setFax(clientArray.getString(i++));
    company.setName(clientArray.getString(i++));
    company.setTelephone(clientArray.getString(i++));
    company.setUrl(clientArray.getString(i++));

    logoutUrl = getLogoutUrlFromJs();

  }

}
