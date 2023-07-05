<%@page import="java.util.ArrayList"%>
<%@page import="com.MG_IC.model.User"%>
<%@page import="com.MG_IC.dao.UserDao"%>
<%@page import="com.MG_IC.model.Message"%>
<%@page import="com.MG_IC.dao.MessageDao"%>
<%@page import="java.util.ServiceLoader.Provider"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<title>LiveInMovie</title>

<!-- Custom Theme files -->
<link href="../css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="../css/bootstrap.min.css" rel="stylesheet" />

<!-- Custom Theme files -->
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="LiveInMovie" />

<script type="application/x-javascript">
	
			 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); 
			 		function hideURLbar(){ window.scrollTo(0,1); } 
		
</script>

<!--webfont-->
<link href='//fonts.googleapis.com/css?family=Oxygen:400,700,300'
	rel='stylesheet' type='text/css'>

<link
	href='//fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800'
	rel='stylesheet' type='text/css'>

<!-- start menu -->
<link href="../css/megamenu.css" rel="stylesheet" type="text/css"
	media="all" />

<script type="text/javascript" src="../js/megamenu.js"></script>

<script>
	$(document).ready(function() {
		$(".megamenu").megamenu();
	});
</script>

<script type="text/javascript" src="../js/jquery.leanModal.min.js"></script>

<link rel="stylesheet" href="../css/font-awesome.min.css" />
<link rel="stylesheet" href="../css/menu.css" />
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
		<![endif]-->
<!---- start-smoth-scrolling---->
<script type="text/javascript" src="../js/move-top.js"></script>
<script type="text/javascript" src="../js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1200);
		});
	});
</script>
<!---- start-smoth-scrolling---->

</head>


<!--  body  ------------------------------------------------------------------------->
<body>

	<%
	int loggedUserId = -1;
	try {
		//Login check
		loggedUserId = Integer.parseInt(session.getAttribute("UserId").toString());
		String admin = session.getAttribute("Admin").toString();
		if (admin != null) {
	%>
	<h2 align=center>
		<font color=black>Message list- ${param.msg}</font>
	</h2>

	<div class="table-responsive shadow p-3 mb-5 bg-white rounded">
		<table class="table table-striped" align="center" border=1 width=90%>
			<thead>
				<tr>
					<th>#</th>
					<th>E-Mail</th>
					<th>Message</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<%
				ArrayList<Message> messages = MessageDao.getAllMessage();
				
				int i = 0;
				for (Message message : messages) {
					int userId = message.getUserId();
					i++;
				%>
				<tr>
					<td align=center><%=i%></td>
					<% 
					if (userId!=0){
						User user = UserDao.getUserById(userId);
					%>
						<td align=center><%=user.getEmail()%></td>
					<% 
					}else{
					%>
						<td align=center>guest</td>
					<% 	
					}
					%>
						<td align=center><%=message.getMessage()%></td>

					<td align=center><a
						href="../Delete?Item=Message&MessageId=<%=message.getMessageId()%>"> <i
							class="fa fa-trash-o fa-lg"></i>
					</a></td>

				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>


	<%
	}
	} catch (Exception e) {
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.sendRedirect("");
	}
	%>
</body>

</html>