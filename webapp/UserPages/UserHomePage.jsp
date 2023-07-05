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


</head>

<!--  body  ------------------------------------------------------------------------------------------------------>
<body>

	<%
	int loggedUserId = -1;
	try {
		//Login check
		loggedUserId = Integer.parseInt(session.getAttribute("UserId").toString());
		User user = UserDao.getUserById(loggedUserId);
	%>


	<!---header-top-strip------------------------------------------------------------------------->

	<div class="header-top-strip" id="home">
		<div class="container">



			<!-- header (top-left) -->
			<div class="header-top-left">
				<p>
					Welcome, <%=user.getFirstName() +" "+ user.getLastName()%>
				</p>
			</div>
			<!-- end:header (top-left) -->





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
	<!---end: header-top-strip-------------------------------------------------------------------->



	<div class="container">
		<div class="main-content">

			<!-- Header: LiveInMovie -->
			<div class="header">
				<div class="logo">
					<h1>
						<a href="UserHomePage.jsp"><img align="middle" height="45px" src="..\images\Logo.jpg" alt=""/></a>
					</h1>
				</div>
				<div class="clearfix"></div>
			</div>


			<!--- top bar menu ---------------------------------------->
			<div class="bootstrap_container">
				<nav class="navbar navbar-default megamenu" role="navigation">
					<div id="defaultmenu" class="navbar-collapse collapse">


						<!-- Top menu bar left-->
						<ul class="nav navbar-nav">

							<li>&nbsp; &nbsp;</li>

							<li class="navbar-header"><a href="" class="dropdown-toggle">
									<i class="fa fa-home fa-2x"></i>
							</a></li>

							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>

							<li class="navbar-header"><a
								href="UserProfile.jsp?UserId=<%=loggedUserId%>"
								target="UserPageCenter" class="dropdown-toggle"> <i
									class="fa fa-user fa-2x" aria-hidden="true"></i>
							</a></li>

							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>

							<li class="navbar-header"><a href="MovieViewer.jsp"
								target="UserPageCenter" class="dropdown-toggle"> <i
									class="fa fa-film fa-2x" aria-hidden="true"></i>
							</a></li>


							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>
							<li>&nbsp;</li>

							<li class="navbar-header"><a href="TheaterSelection.jsp"
								target="UserPageCenter" class="dropdown-toggle"> <i
									class="fa fa-building-o fa-2x" aria-hidden="true"></i>
							</a></li>



						</ul>
						<!-- end: Top menu bar left-->



						<!-- Top menu bar right-->
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a href="" data-toggle="dropdown"
								class="dropdown-toggle"> Contact Us <b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li>
										<!-- form  Contact Us --------------->
										<form id="contact1" action="../AddMessage" name="contact1"
											method="post">


											<!-- form: Message-->
											<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
												<textarea class="form-control" name="Message" id="Message"
													rows="6" placeholder="Your Message ..."></textarea>
											</div>

											<!-- form: hidden User Id-->
											<div class="form-group">
												<input type="hidden" class="form-control" id="UserId"
													name="UserId" value="<%=loggedUserId%>">
											</div>

											<!-- form: submit-->
											<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
												<div class="pull-right">
													<input type="submit" value="SEND" id="submit1"
														class="btn btn-primary small">
												</div>
											</div>

											<div class="clearfix"></div>
										</form> <!-- end: form  Contact Us --------------->

									</li>
								</ul></li>
						</ul>
						<!-- end: Top menu bar right-->



					</div>
				</nav>
			</div>
			<!---end: top bar menu ------------------------------->





			<div class="main-banner">

				<!-- Page Center -->
				<p align="center">
					<iframe height="1000px" width="100%" name="UserPageCenter"
						src="UserInfo.jsp" style="border: none;"> </iframe>
				</p>

				<!-- end: Page Center -->


				<!-- Bottom bar    -->
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
				<!-- end: Bottom bar    -->



				<div class="clearfix"></div>
			</div>
	<div class="copy-rights text-center">
				<p>©LiveInMovie 2021</p>
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
	} catch (Exception e) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.sendRedirect("../index.jsp");
	}
	%>

</body>
</html>