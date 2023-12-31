
<%@page import="com.MG_IC.dao.UserDao"%>
<%@page import="com.MG_IC.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>

<html>

<head>
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


</head>

<!--  body  ------------------------------------------------------------------------------------------------------>
<body>

	<%
	int loggedUserId = -1;
	try {
		//Login check
		loggedUserId = Integer.parseInt(session.getAttribute("UserId").toString());
		User user = UserDao.getUserById(loggedUserId);
		String admin = session.getAttribute("Admin").toString();
		if (admin != null){
	%>


	
		
		<!-- header-section-starts -->
	<div class="header-top-strip" id="home">
		<div class="container">


			<div class="header-top-left">
				<h4>
					Welcome, <%=user.getFirstName() +" "+ user.getLastName()%>
				</h4>
			</div>

			<!---header (top right) --->
			<div class="header-top-right">

				<!---pop-up-box---->
				<link href="../css/popuo-box.css" rel="stylesheet" type="text/css"
					media="all" />
				<script src="../js/jquery.magnific-popup.js" type="text/javascript"></script>
				<script>
					$(document).ready(function() {
						$('.popup-with-zoom-anim').magnificPopup({
							type : 'inline',
							fixedContentPos : false,
							fixedBgPos : true,
							overflowY : 'auto',
							closeBtnInside : true,
							preloader : false,
							midClick : true,
							removalDelay : 300,
							mainClass : 'my-mfp-zoom-in'
						});
					});
				</script>

				<!-- Login button (top-right) -->

				<a href="../Login">
					<button class="btn btn-primary" data-toggle="modal" name="Logout"
						id="Logout">Logout</button>
				</a>
			</div>

			<div class="clearfix"></div>

		</div>
		<!---end:container top bar---->

	</div>

	<script>
		$('#myModal').modal('no');
	</script>

	</div>

	<div class="clearfix"></div>

	<div class="container">
		<div class="main-content">

			<!-- Header: LiveInMovie -->
			<div class="header">
				<div class="logo">
					<h1>
						<a href="..\UserPages\UserHomePage.jsp"><img align="middle" height="45px" src="..\images\Logo.jpg" alt=""/></a>
					</h1>
				</div>
				<div class="clearfix"></div>
			</div>


			<!-- Top menu bar -->
			<div class="bootstrap_container">

				<nav class="navbar navbar-default megamenu" role="navigation">


					<!-- menu -->
					<div id="defaultmenu" class="navbar-collapse collapse">
						<ul class="nav navbar-nav">

							<li>&nbsp; &nbsp;</li>
							<li class="navbar-header"><a href="AdminHomePage.jsp"
								class="dropdown-toggle"> <i class="fa fa-home fa-2x"></i>
							</a></li>

							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>

							<li class="navbar-header"><a href="ViewUsers.jsp"
								target="AdminPageCenter" class="dropdown-toggle"> <i
									class="fa fa-user fa-2x" aria-hidden="true"></i>

							</a></li>

							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>

							<li class="dropdown"><a href="#" data-toggle="dropdown"
								class="dropdown-toggle"> <i class="fa fa-film fa-2x"
									aria-hidden="true"></i> <b class="caret"></b>
							</a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="AddMovie.jsp" target="AdminPageCenter">Add
											Movie</a></li>
									<li><a href="ViewMovies.jsp" target="AdminPageCenter">View
											Movie</a></li>
								</ul></li>

							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>

							<li class="dropdown"><a href="#" data-toggle="dropdown"
								class="dropdown-toggle"> <i class="fa fa-building-o fa-2x"
									aria-hidden="true"></i> <b class="caret"></b>
							</a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="AddTheater.jsp" target="AdminPageCenter">Add
											Theater</a></li>
									<li><a href="ViewTheaters.jsp" target="AdminPageCenter">View
											Theater</a></li>
								</ul></li>

							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>

							<li class="dropdown"><a href="#" data-toggle="dropdown"
								class="dropdown-toggle"> <i class="fa fa-video-camera fa-2x"
									aria-hidden="true"></i> <b class="caret"></b>
							</a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="AddScreening.jsp" target="AdminPageCenter">Add
											Screening</a></li>
									<li><a href="ViewScreenings.jsp" target="AdminPageCenter">View
											Screenings</a></li>
								</ul></li>

							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							
							
							<li class="navbar-header"><a href="ViewMessages.jsp"
								target="AdminPageCenter" class="dropdown-toggle"> <i
									class="fa fa-comments fa-2x" aria-hidden="true"></i>

							</a></li>
							
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							
							
							<li><a href="Definitions.jsp" class="navbar-fixed-bottom"
								target="AdminPageCenter"> <i class="fa fa-cog fa-spin fa-2x"></i>

							</a></li>


						</ul>
					</div>
					<!-- END: menu -->
				</nav>
			</div>
			<!-- END: Top menu bar -->





			<div>
				<div class="main-banner">

					<!-- Page Center -->
					<p align="center">
						<iframe height="1000px" width="90%" name="AdminPageCenter"
							src="AdminInfo.jsp" style="border: none;"> </iframe>
					</p>

					<!-- Bottom bar -->
					<div class="clearfix"></div>
					<div class="col-md-12">
						<div class="footer-right">
							<div class="follow-us">
								<h5 class="f-head">Follow us</h5>
								<ul class="social-icons">
									<li><a href="https://www.facebook.com" target="_blank">
											<i class="fa fa-facebook"></i>
									</a></li>
									<li><a href="https://twitter.com" target="_blank"> <i
											class="fa fa-twitter"></i>
									</a></li>
									<li><a href="https://www.youtube.com/" target="_blank">
											<i class="fa fa-youtube"></i>
									</a></li>
									<li><a href="https://www.pinterest.com/" target="_blank">
											<i class="fa fa-pinterest"></i>
									</a></li>
									<li><a href="https://www.linkedin.com/" target="_blank">
											<i class="fa fa-linkedin"></i>
									</a></li>
								</ul>
								<div class="clearfix"></div>
							</div>


							<div class="clearfix"></div>
						</div>
					</div>

				</div>
				<div class="clearfix"></div>
			</div>
			<div class="copy-rights text-center">
				<p>ŠLiveInMovie 2021</p>
			</div>
		</div>
	</div>


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
	<a href="#home" class="scroll" id="toTop" style="display: block;">
		<span id="toTopHover" style="opacity: 1;"> </span>
	</a>
	
	<%
		}
	} catch (Exception e) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.sendRedirect("../index.jsp");
	}
	%>
</body>


</html>