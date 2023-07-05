<%@page import="com.MG_IC.model.Theater"%>
<%@page import="com.MG_IC.dao.TheaterDao"%>
<%@page import="com.MG_IC.model.Hall"%>
<%@page import="com.MG_IC.dao.HallDao"%>
<%@page import="com.MG_IC.model.Seat"%>
<%@page import="com.MG_IC.dao.SeatDao"%>
<%@page import="com.MG_IC.model.Movie"%>
<%@page import="com.MG_IC.dao.MovieDao"%>
<%@page import="com.MG_IC.model.Screening"%>
<%@page import="com.MG_IC.dao.ScreeningDao"%>
<%@page import="com.MG_IC.model.User"%>
<%@page import="com.MG_IC.dao.UserDao"%>
<%@page import="com.MG_IC.model.Ticket"%>
<%@page import="com.MG_IC.dao.TicketDao"%>
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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css">
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
		if (admin != null){
	%>

	<h2 align=center></h2>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h1>
					Chain info <small> Overview</small>
				</h1>
			</div>
			<div class="row">
				<div class="col-md-3">
					<div class="panel panel-default ">
						<div class="panel-body panel-tw panel-content">
							<div class="col-xs-5">
								<i class="fa fa-building fa-4x"></i>
							</div>
							<div class="col-xs-5 text-right">
								<p class="alerts-heading tex">
									<%
									ArrayList<Theater> theaters = TheaterDao.getAllTheater();
									out.println(theaters.size());
									%>
								</p>
								<p class="alerts-text tex">Theaters</p>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="panel panel-default ">
						<div class="panel-body panel-fb panel-content">
							<div class="col-xs-5">
								<i class="fa fa-th fa-4x"></i>
							</div>
							<div class="col-xs-5 text-right">
								<p class="alerts-heading tex ">
									<%
									ArrayList<Hall> halls = HallDao.getAllHall();
									out.println(halls.size());
									%>
								</p>
								<p class="alerts-text tex">Halls</p>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="panel panel-default ">
						<div class="panel-body panel-in panel-content">
							<div class="col-xs-5">
								<i class="fa fa-chair fa-4x"></i>
							</div>
							<div class="col-xs-5 text-right">
								<p class="alerts-heading tex">
									<%
									ArrayList<Seat> seats = SeatDao.getAllSeat();
									out.println(seats.size());
									%>
								</p>
								<p class="alerts-text tex">Seats</p>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="panel panel-default ">
						<div class="panel-body panel-dl panel-content">
							<div class="col-xs-5">
								<i class="fa fa-users fa-4x"></i>
							</div>
							<div class="col-xs-5 text-right">
								<p class="alerts-heading tex">
									<%
									ArrayList<User> users = UserDao.getAllUser();
									out.println(users.size());
									%>
								</p>
								<p class="alerts-text tex">Users</p>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="panel panel-default ">
						<div class="panel-body panel-dl panel-content">
							<div class="col-xs-5">
								<i class="fa fa-film fa-4x"></i>
							</div>
							<div class="col-xs-5 text-right">
								<p class="alerts-heading tex">
									<%
									ArrayList<Movie> movies = MovieDao.getAllMovie();
									out.println(movies.size());
									%>
								</p>
								<p class="alerts-text tex">Movies</p>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="panel panel-default ">
						<div class="panel-body panel-dl panel-content">
							<div class="col-xs-5">
								<i class="fa fa-video-camera fa-4x"></i>
							</div>
							<div class="col-xs-5 text-right">
								<p class="alerts-heading tex">
									<%
									ArrayList<Screening> screenings = ScreeningDao.getAllScreening();
									out.println(screenings.size());
									%>
								</p>
								<p class="alerts-text tex">Screenings</p>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="panel panel-default ">
						<div class="panel-body panel-dl panel-content">
							<div class="col-xs-5">
								<i class="fa fa-ticket-alt fa-4x"></i>
							</div>
							<div class="col-xs-5 text-right">
								<p class="alerts-heading tex">
									<%
									ArrayList<Ticket> tickets = TicketDao.getAllTicket();
									out.println(tickets.size());
									%>
								</p>
								<p class="alerts-text tex">Tickets</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
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