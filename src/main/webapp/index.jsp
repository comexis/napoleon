<!doctype html>

<%@page import="eu.comexis.napoleon.shared.model.Company"%>
<%@page import="eu.comexis.napoleon.shared.model.ApplicationUser"%>
<%@page import="eu.comexis.napoleon.server.utils.JSONHelper"%>
<%@page import="org.json.JSONObject"%>
<%@page import="eu.comexis.napoleon.server.manager.UserManager"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>

<%
UserService userService = UserServiceFactory.getUserService();

ApplicationUser user = UserManager.INSTANCE.getConnectedUser();

//This test is normally unusefull because the SecurityFilter class should stop the application before
if (user == null){
  response.sendError(HttpServletResponse.SC_FORBIDDEN);
}

Company client = UserManager.INSTANCE.getConnectedCompany();
%>

<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta name="gwt:property" content="locale=fr">

	<title><%client.getName();%></title>
	
	<script type="text/javascript" language="javascript" src="napoleon/napoleon.nocache.js"></script>
	
	<script type="text/javascript">
	      var __GLOBALS = [<%=JSONHelper.toJSONArray(user)%>,
	                       "<%= userService.createLogoutURL("/index.jsp") %>"];
	      
	</script>
	
	<style type="text/css">
		#loading {
			width: 100%;
			margin-top:50px;
		}
		
		#loading > div {
			background-image: url(resources/loading.gif);
			background-repeat: no-repeat;
			width: 250px;
			height: 40px;
			margin-left: auto;
			margin-right: auto;
			padding-left: 60px;
			background-position: 10px 10px;
			padding-top: 15px;
			border: solid 1px black;
		}
		
		#loading > div {
		
		}
	</style>
</head>

<body>

	<div id="loading"><div>Lancement de l'application...</div></div>
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
