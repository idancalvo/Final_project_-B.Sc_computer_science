<%@page import="com.MG_IC.model.Theater"%>
<%@page import="com.MG_IC.dao.TheaterDao"%>
<%@page import="com.MG_IC.dao.ScreeningDao"%>
<%@page import="com.MG_IC.model.ScreeningToDisplay"%>
<%@page import="java.util.ArrayList"%>


<%@page language="java" contentType="text/html; charset= windows-1255"
	pageEncoding="windows-1255"%>


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

			<div class="col-md-8 movies-dates">
				<div id="myTabContent" class="tab-content">
					<div role="tabpanel" class="tab-pane fade in active" id="home"
						aria-labelledby="home-tab">


						<!-- form  --------------------------------------------------------------------------------------------------->
						<form class="form-horizontal" action="ScreeningSelection.jsp">

							<!-- form: Theater Name-->
							<div class="form-group">
								<br /> <br /> <label class="control-label col-sm-2"
									for="TheaterName"> Theater Name</label>
								<div class="col-md-5">
									<select name="TheaterName" id="TheaterName"
										class="form-control" required>
										<option value="0">--Select Theater--</option>
										<%
										ArrayList<Theater> theaters = TheaterDao.getAllTheater();
										for (Theater theater : theaters) {
										%>
										<option value=<%=theater.getTheaterId()%>>
											<%=theater.getName()%>
										</option>
										<%
										}
										%>
									</select>
								</div>
							</div>



							<%
							try {
								String strMovieId = request.getParameter("MovieId");
								int movieId = 0;
								if (strMovieId != null) {
									movieId = Integer.parseInt(strMovieId);
							%>
							<!-- form: hidden Movie Id  -->
							<div class="form-group">
								<input type="hidden" class="form-control" id="MovieId"
									name="MovieId" value="<%=movieId%>">
							</div>
							<%
							}
							} catch (Exception e) {
							System.out.println(e);
							}
							%>

							<!-- form: Date -->
							<div class="form-group">
								<label class="control-label col-sm-2" for="Date">Date</label>
								<div class="col-md-5">
									<input type="date" class="form-control" name="Date" id="Date"
										required>
								</div>
							</div>

							<!-- form: submit & reset -->
							<div class="row">
								<div class="col-md-2"></div>
								<div class="col-md-5">
									<input type="submit" class="btn btn-primary" value="Next">
								</div>
							</div>
						</form>
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
