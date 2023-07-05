<%@page import="java.util.ArrayList"%>
<%@page import="com.MG_IC.model.Movie"%>
<%@page import="com.MG_IC.dao.MovieDao"%>
<%@page import="com.MG_IC.dao.ImageDao"%>
<%@page import="java.util.Random"%>

<%@ page language="java" contentType="text/html; charset=windows-1255"
	pageEncoding="windows-1255"%>
<!DOCTYPE html>

<html>

<head>
<meta charset="windows-1255">

<title>LiveInMovie</title>


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

<script src="../js/easyResponsiveTabs.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#horizontalTab').easyResponsiveTabs({
			type : 'default', //Types: default, vertical, accordion           
			width : 'auto', //auto or any width like 600px
			fit : true
		// 100% fit in a container
		});
	});
</script>

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

<link rel="stylesheet" href="../css/flexslider.css" type="text/css"
	media="screen" />

</head>


<body>

	<%
	int loggedUserId = -1;
	try {
		//Login check
		loggedUserId = Integer.parseInt(session.getAttribute("UserId").toString());
	%>

	<div class="main-content">
		<div class="main-banner">
				<section class="slider">
					<div class="flexslider">
						<ul class="slides">
							<%
							ArrayList<Movie> oldArrMovies = MovieDao.getAllScreenedMovie();
							ArrayList<Movie> newArrMovies = new ArrayList<Movie>();
							Random rand = new Random();
							while (oldArrMovies.size() != 0 && newArrMovies.size() != 10) {
								
								int i = rand.nextInt(oldArrMovies.size());
								newArrMovies.add(oldArrMovies.get(i));
								oldArrMovies.remove(i);
							}

							for (Movie movie : newArrMovies) {
								String imagePath = ImageDao.getImageById(movie.getImageId()).getImagePath();
							%>
							<li>
								<div class="col-md-10 trailers-now-showing-grid text-center">

									<a href="MovieProfile.jsp?MovieId=<%=movie.getMovieId()%>">
										<img src="../images<%=imagePath%>" class="img-fluid" alt="" />

									</a>
									<%=movie.getName()%>
								</div>
							</li>
							<%
							}
							%>
						</ul>
					</div>
				</section>

				<!-- FlexSlider -->
				<script defer src="../js/jquery.flexslider.js"></script>

				<script type="text/javascript">
					$(function() {
						SyntaxHighlighter.all();
					});
					$(window).load(function() {
						$('.flexslider').flexslider({
							animation : "slide",
							animationLoop : true,
							itemWidth : 200,
							start : function(slider) {
								$('body').removeClass('loading');
							}
						});
					});
				</script>
		
		</div>
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