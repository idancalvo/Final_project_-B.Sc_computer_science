<%@page import="com.MG_IC.dao.ImageDao"%>
<%@page import="com.MG_IC.model.User"%>
<%@page import="com.MG_IC.dao.UserDao"%>
<%@page import="com.MG_IC.model.Ticket"%>
<%@page import="com.MG_IC.dao.TicketDao"%>
<%@page import="com.MG_IC.model.TicketToDisplay"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

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
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements aressend media queries -->
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
	%>

	<h2 align=center>
		<font color=black>User profile :</font>
	</h2>

	<br />
	<br />

	<%
	try {
		int userId = Integer.parseInt(request.getParameter("UserId"));
		User user = UserDao.getUserById(userId);
		int imageId = user.getImageId();
		String imagePath;
		try {
			imagePath = ImageDao.getImageById(imageId).getImagePath();
		} catch (Exception e) {
			imagePath = "\\" + ImageDao.DEFALT_IMAGE_NAME;
		}
	%>


	<div class="container">
		<div class="main">
			<div class="row">

				<div class="col-md-4 mt-1">
					<div class="card text-center sidebar">
						<div class="card-body">
							<img src="..\images<%=imagePath%>" class="rounded-circle"
								width="150">
							<div class="mt-3">
								<h3><%=user.getFirstName() + " " + user.getLastName()%></h3>
								<a class="list-group-item"
									href="UpdateUser.jsp?UserId=<%=userId%>">Edit</a> <a
									class="list-group-item"
									href="UpdatePassword.jsp?UserId=<%=userId%>">Change
									password</a>
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-8 mt-1">
					<div class="card mb-3 content">

						<div class="row">
							<div class="col-md-3">
								<h5>First Name</h5>
							</div>
							<div class="col-md-9 text-secondary">
								<%=user.getFirstName()%>
							</div>
						</div>
						<hr>

						<div class="row">
							<div class="col-md-3">
								<h5>Last Name</h5>
							</div>
							<div class="col-md-9 text-secondary">
								<%=user.getLastName()%>
							</div>
						</div>
						<hr>

						<div class="row">
							<div class="col-md-3">
								<h5>Email</h5>
							</div>
							<div class="col-md-9 text-secondary">
								<%=user.getEmail()%>
							</div>
						</div>
						<hr>

						<div class="row">
							<div class="col-md-3">
								<h5>Phone</h5>
							</div>
							<div class="col-md-9 text-secondary">
								<%=user.getPhone()%>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


		<!--Tickets table  -->
		<div class="card mb-3 content">
			<h3 class="m-3">My tickets</h3>
			<div class="table-responsive shadow p-3 mb-5 bg-white rounded">
				<table class="table table-striped" align="center" border=1 width=90%>
					<thead>
						<tr>
							<th>#</th>
							<th>Theater Name</th>
							<th>Hall No.</th>
							<th>Movie Name</th>
							<th>Seat</th>
							<th>Movie Length</th>
							<th>Date</th>
						</tr>
					</thead>

					<tbody>
						<%
						ArrayList<TicketToDisplay> tickets = TicketDao.getTicketsDisByUserId(user.getUserId());
						int i = 0;
						for (TicketToDisplay ticket : tickets) {
							i++;
						%>
						<tr>
							<td align=center><%=i%></td>
							<td align=center><%=ticket.getTheaterName()%></td>
							<td align=center><%=ticket.getHallNum()%></td>
							<td align=center><%=ticket.getMovieName()%></td>
							<td align=center><%=ticket.getSeatRow() + " : " + ticket.getSeatNum()%>
							</td>
							<td align=center><%=ticket.getMovieLength()%></td>
							<td align=center><%=ticket.getDateAndTime()%></td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
		</div>
		<!--End Tickets table  -->

	</div>
	<%
	} catch (Exception e) {
	System.out.println(e);
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

