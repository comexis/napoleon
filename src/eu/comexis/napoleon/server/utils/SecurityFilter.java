package eu.comexis.napoleon.server.utils;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import eu.comexis.napoleon.server.manager.UserManager;
import eu.comexis.napoleon.shared.model.ApplicationUser;

public class SecurityFilter implements Filter {

  private static String uri = "/index.jsp";

  private static Logger log = Logger.getLogger(SecurityFilter.class.getName());

  @Override
  public void destroy() {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    if (checkIfUserIsLoggedIn((HttpServletResponse) response, (HttpServletRequest) request)) {
      chain.doFilter(request, response);
    }

  }

  @Override
  public void init(FilterConfig arg0) throws ServletException {
  }

  /**
   * Check if the user is logged in and have access to the application. Return true if it is the
   * case or false otherwise.
   * 
   * @param response
   * @return
   * @throws IOException
   */
  private boolean checkIfUserIsLoggedIn(HttpServletResponse response, HttpServletRequest request)
      throws IOException {
    UserService userService = getUserService();

    // is user logged in ?
    if (!userService.isUserLoggedIn()) {

      response.sendRedirect(userService.createLoginURL(uri));

      return false;
    }

    // user is logged, check if he has access to the application
    ApplicationUser user = UserManager.INSTANCE.getConnectedUser();

    // the google account is not authorize to access to the application
    if (user == null) {

      log.severe("User " + userService.getCurrentUser().getEmail()
          + " is not authorize to access the application");

      response.sendError(HttpServletResponse.SC_FORBIDDEN,
          "You are not authorized to use the application.");

      return false;
    }

    System.err.println("user is logged in");
    return true;

  }

  public static UserService getUserService() {
    return UserServiceFactory.getUserService();
  }
}
