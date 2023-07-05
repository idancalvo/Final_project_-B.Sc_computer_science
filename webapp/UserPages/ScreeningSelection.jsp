<%@page import="com.MG_IC.dao.ScreeningDao"%>
<%@page import="com.MG_IC.dao.MovieDao"%>
<%@page import="com.MG_IC.model.Movie"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.MG_IC.dao.MyTimestamp"%>
<%@page import="com.MG_IC.model.ScreeningToDisplay"%>



<%@page language="java" contentType="text/html; charset= windows-1255"
	pageEncoding="windows-1255"%>





<!DOCTYPE html>

<HTML>

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
<meta name="keywords" content="LiveinMovie Cinema" />
<script type="application/x-javascript">
	

	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 

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
<script src="https://oss.maxcdn.com/libs/respond.../js/1.3.0/respond.min.js"></script>
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



<body>
	<%
	int loggedUserId = -1;
	try {
		//Login check
		loggedUserId = Integer.parseInt(session.getAttribute("UserId").toString());

		try {
			MyTimestamp dateAndTime = MyTimestamp.StringConvert(request.getParameter("Date"), "00:00");
			int theaterId = Integer.parseInt(request.getParameter("TheaterName"));

			String strMovieId = request.getParameter("MovieId");
			int movieId = -1;
			if (strMovieId != null) {
		movieId = Integer.parseInt(strMovieId);
			}
	%>
	<div class="main-content">


		<!-- AddThis Smart Layers END -->
		<div class="pic-a-movie"></div>
		<div class="now-showing-list">
			<div class="col-md-8 movies-dates">
				<div class="select-day-booking-ticket">
					<div class="tabs">
						<div class="sap_tabs">
							<div id="horizontalTab"
								style="display: block; width: 100%; margin: 0px;">

								<!-- dates: 5 days  -->
								<ul class="resp-tabs-list" id="tabs">
									<%
									ArrayList<Movie>[] allMovies = new ArrayList[5];
									dateAndTime.addDay(-2);
									for (int i = 0; i < allMovies.length; i++) {
										if (movieId == -1) {
											allMovies[i] = MovieDao.getMovieByTheaterDate(theaterId, dateAndTime);
										} else {
											ArrayList<Movie> temp = new ArrayList<Movie>();
											temp.add(MovieDao.getMovieById(movieId));
											allMovies[i] = temp;
										}
									%>

									<li class="resp-tab-item" aria-controls="tab_item-<%=i%>" id="tab_item-<%=i%>" 
									name="tab_item-<%=i%>" role="tab" >
										<span><%=dateAndTime.dateToString()%></span>
									</li>
									<%
										dateAndTime.addDay(1);
									}
									%>
			
									<div class="clearfix"></div>
								</ul>
								<!-- end: dates: 5 days  -->

								<!-- table: movies and times  -->
								<div class="resp-tabs-container">
									<%
									dateAndTime.addDay(-5);
									for (int i = 0; i < allMovies.length; i++) {
									%>
									<div class="tab-1 resp-tab-content"
										aria-labelledby="tab_item-<%=i%>">
										<div class="show-times-with-movies">
											<table class="table table-hover">
												<thead>
													<tr>
														<th scope="col"><h4 class="show">Movie</h4></th>
														<th scope="col"><h4 class="show">ShowTime</h4></th>
													</tr>
												</thead>
												<tbody>
													<%
													for (Movie movie : allMovies[i]) {
														ArrayList<ScreeningToDisplay> screenings = ScreeningDao.getScreeningToDisByTheaterMovieDate(theaterId,
														movie.getMovieId(), dateAndTime);
													%>
													<tr>
														<td><%=movie.getName()%></td>
														<td>
														<%
														for (ScreeningToDisplay screening : screenings) {
														%>
														<a href="SeatSelection.jsp?ScreeningId=<%=screening.getScreeningId()%>">
																<button type="button" class="btn btn-primary"><%=screening.getTime()%></button>
														</a>
														&nbsp;
														<%
														}
														%>
														</td>
													</tr>
													<%
													}
													%>
												</tbody>
											</table>
										</div>
									</div>
									<%
									dateAndTime.addDay(1);
									}
									%>
								</div>
								<!-- end: table: movies and times  -->

							</div>

						</div>
					</div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>





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
	
	<a href="#home" class="scroll" id="toTop" style="display: block;">
		<span id="toTopHover" style="opacity: 1;"> </span>
	</a>


		<script type="text/javascript">
			$("#horizontalTab").tabs("option", "active", 2);
		</script>

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
