<!doctype html>

<%@page import="eu.comexis.napoleon.server.utils.JSONHelper"%>
<%@page import="org.json.JSONObject"%>
<%@page import="eu.comexis.napoleon.shared.model.Client"%>
<%@page import="eu.comexis.napoleon.server.manager.UserManager"%>
<%@page import="eu.comexis.napoleon.shared.model.AppUser"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>

<%

// The part of the code will check quickly if the user is connected with a Google account
// and if the account has access to the application.
UserService userService = UserServiceFactory.getUserService();

//if user not logged !!
if (!userService.isUserLoggedIn()) {
	response.sendRedirect(userService.createLoginURL(request.getRequestURI()));
	return;
}

//user is logged, check if he has access to the application
AppUser user = UserManager.INSTANCE.getConnectedUser();

//the google acount is not authorize to access to the application
if (user == null){
	response.sendError(HttpServletResponse.SC_FORBIDDEN,
               	"You are not authorized to use the application.");
	return;
}

Client client = user.getClient();
%>

<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		

	<title><%=user.getClient().getName()%></title>
	
	<script type="text/javascript" language="javascript" src="napoleon/napoleon.nocache.js"></script>
	
	<script type="text/javascript">
	      var __GLOBALS = [<%=JSONHelper.toJSONArray(user)%>];
	      
	</script>
</head>

<body>

	<div id="loading">loading...</div>
	<iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1'
		style="position: absolute; width: 0; height: 0; border: 0"></iframe>

	<noscript>
		<div
			style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
			Your web browser must have JavaScript enabled in order for this
			application to display correctly.</div>
	</noscript>

	</body>
</html>
