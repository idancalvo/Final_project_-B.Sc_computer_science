<%@page import="com.MG_IC.model.Theater"%>
<%@page import="com.MG_IC.dao.TheaterDao"%>
<%@page import="com.MG_IC.model.Movie"%>
<%@page import="com.MG_IC.dao.MovieDao"%>
<%@page import="com.MG_IC.model.Hall"%>
<%@page import="com.MG_IC.dao.HallDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>

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


	<div class="container" style="margin: 10px;">

		<div class="row" align="center">
			<h2>Add Screening :- ${param.msg}</h2>
		</div>

		<div>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</div>



		<%
		String tSelected = request.getParameter("TheaterId");
		int theaterId = 0;
		if (tSelected == null) {
		%>


		<form id="myform" class="form-horizontal" action="">

			<!-- Form: Theater name -->
			<div class="form-group">
				<label class="control-label col-sm-2" for="HallNo">Theater </label>
				<div class="col-md-5">
					<select name="TheaterId" id="TheaterId" class="form-control">
						<option value="0">--Select Theater--</option>
						<%
						ArrayList<Theater> theaters = TheaterDao.getAllTheater();
						for (Theater theater : theaters) {
						%>
						<option value=<%=theater.getTheaterId()%>><%=theater.getName()%></option>
						<%
						}
						%>
					</select>
				</div>
				<input type="submit" class="btn btn-primary" value="Submit">
			</div>
			
		</form>

		<%
		} else {
		theaterId = Integer.parseInt(tSelected);
		Theater theater = TheaterDao.getTheaterById(theaterId);
		%>

		<!-- form2  --------------------------------------------------------------------------------------------------->
		<form id="myform" class="form-horizontal" action="../AddScreening"
			method="post">


			<!-- Form: Theater name -->
			<div class="form-group">
				<label class="control-label col-sm-2" for="HallNo">Theater </label>
				<div class="col-md-5">
					<select name="TheaterId" id="TheaterId" class="form-control">
						<option value=<%=theater.getTheaterId()%>>
							<%=theater.getName()%>
						</option>
					</select>
				</div>
			</div>


			<!-- form: Hall No-->
			<div class="form-group">
				<label class="control-label col-sm-2" for="HallNo">Hall No </label>
				<div class="col-md-5">
					<select name="HallNo" id="HallNo" class="form-control" required>
						<option>--Select Hall--</option>
						<%
						ArrayList<Hall> halls = HallDao.getHallsByTheaterId(theaterId);
						for (Hall hall : halls) {
						%>
						<option value=<%=hall.getHallId()%>>
							<%=hall.getHallNum()%>
						</option>
						<%
						}
						%>
					</select>
				</div>
			</div>



			<!-- form: Movie Name -->
			<div class="form-group">
				<label class="control-label col-sm-2" for="MovieName">Movie
					Name</label>
				<div class="col-md-5">
					<select name="MovieName" id="MovieName" class="form-control"
						required>
						<option>--Select Movie--</option>
						<%
						ArrayList<Movie> movies = MovieDao.getAllMovie();
						for (Movie movie : movies) {
						%>
						<option value=<%=movie.getMovieId()%>>
							<%=movie.getName()%>
						</option>
						<%
						}
						%>
					</select>
				</div>
			</div>



			<!-- form: Date -->
			<div class="form-group">
				<label class="control-label col-sm-2" for="Date">Date</label>
				<div class="col-md-5">
					<input type="date" class="form-control" name="Date" id="Date"
						required>
				</div>
			</div>


			<!-- form: Start Time -->
			<div class="form-group">
				<label class="control-label col-sm-2" for="Time"> Start Time</label>
				<div class="col-md-5">
					<input type="time" class="form-control" name="Time" id="Time"
						required>
				</div>
			</div>





			<!-- form: submit & reset -->
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-5">
					<input type="submit" class="btn btn-primary" value="ADD"> <input
						type="reset" class="btn btn-primary" value="CANCEL">
				</div>
			</div>
		</form>
		<!-- /form2  -------------------------------------------------------------------------------------------------->
		<%
		} //else - form 2
		%>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="../js/jquery.js"></script>

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="../js/bootstrap.min.js"></script>

	<script src="../js/responsive-tabs.js"></script>

	<script type="text/javascript">
		$('#myTab a').click(function(e) {
			e.preventDefault();
			$(this).tab('show');
		});

		$('#moreTabs a').click(function(e) {
			e.preventDefault();
			$(this).tab('show');
		});

		(function($) {
			// Test for making sure event are maintained
			$('.js-alert-test').click(function() {
				alert('Button Clicked: Event was maintained');
			});
			fakewaffle.responsiveTabs([ 'xs', 'sm' ]);
		})(jQuery);
	</script>

	<script type="text/javascript">
		$(document).ready(function() {
			/*
			var defaults = {
				containerID: 'toTop', // fading element id
				containerHoverID: 'toTopHover', // fading element hover id
				scrollSpeed: 1200,
				easingType: 'linear' 
			};
			 */

			$().UItoTop({
				easingType : 'easeOutQuart'
			});

		});
	</script>

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