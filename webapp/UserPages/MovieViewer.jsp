<%@page import="java.util.ArrayList"%>
<%@page import="com.MG_IC.model.User"%>
<%@page import="com.MG_IC.dao.UserDao"%>
<%@page import="com.MG_IC.model.Movie"%>
<%@page import="com.MG_IC.dao.MovieDao"%>
<%@page import="com.MG_IC.model.Screening"%>
<%@page import="com.MG_IC.dao.ScreeningDao"%>
<%@page import="com.MG_IC.dao.ImageDao"%>



<!DOCTYPE html>
<html>

<head>
<link href="../css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- Custom Theme files -->
<link href="../css/style.css" rel="stylesheet" type="text/css"
	media="all" />
<!-- Custom Theme files -->
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="application/x-javascript">
	 addEventListener("load", function() { 
		 setTimeout(hideURLbar, 0); 
		 }, false); 
	 function hideURLbar(){
		 window.scrollTo(0,1); 
		} 

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
<!---- start-smoth-scrolling---->
</head>


<!--  body  ------------------------------------------------------------------------------------------------------>

<body>

	<%
	int loggedUserId = -1;
	try {
		//Login check
		loggedUserId = Integer.parseInt(session.getAttribute("UserId").toString());
	%>


	<div class="main-content">
		<div class="now-showing-list">
			<div >
				<h3 class="m-head">Movies</h3>
				<div class="trailers-now-showing">
					<h3>Now-Showing</h3>
					<div class="trailers-now-showing-grids">

						<%
						ArrayList<Movie> movies = MovieDao.getAllScreenedMovie();
						for (Movie movie : movies) {
							String imagePath = ImageDao.getImageById(movie.getImageId()).getImagePath();
						%>
						<div class="col-md-2 trailers-now-showing-grid text-center">
							<a href="MovieProfile.jsp?MovieId=<%=movie.getMovieId()%>"> <img
								src="..\images<%=imagePath%>" alt="" />
							</a>
							<%=movie.getName()%>
						</div>
						
						<%
						}
						%>

						
					</div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="clearfix"></div>
		
	</div>

	<%
	} catch (Exception e) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.sendRedirect("");
	}
	%>

</body>

</html>