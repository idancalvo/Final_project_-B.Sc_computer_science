<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>

<html>

	<head>
		<title>LiveInMovie</title>
		
		<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
		
		<!-- Custom Theme files -->
		<link href="css/style.css" rel="stylesheet" type="text/css"
			media="all" />
		
		<!-- Custom Theme files -->
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		
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
		<link href="css/megamenu.css" rel="stylesheet" type="text/css"
			media="all" />
		
		<script type="text/javascript" src="js/megamenu.js"></script>
		
		<script>
			$(document).ready(function() {
				$(".megamenu").megamenu();
			});
		</script>
		
		<script type="text/javascript" src="js/jquery.leanModal.min.js"></script>
		<link rel="stylesheet" href="css/font-awesome.min.css" />
		<link rel="stylesheet" href="css/menu.css" />
		
		<script src="js/easyResponsiveTabs.js" type="text/javascript"></script>
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
		<script type="text/javascript" src="js/move-top.js"></script>
		<script type="text/javascript" src="js/easing.js"></script>
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

	
<!---header-top-strip------------------------------------------------------------------------->
	

	
	<div class="header-top-strip" id="home">
		<div class="container">
			
			
			
	<!-- header (top-left) -->
			<div class="header-top-left">
				<p>
					Welcome, guest
				</p>
			</div>
	<!-- end:header (top-left) -->



	<!---header (top right) --->
			<div class="header-top-right">

				<!---pop-up-box---->
				<link href="css/popuo-box.css" rel="stylesheet" type="text/css"
					media="all" />
				<script src="js/jquery.magnific-popup.js" type="text/javascript"></script>
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
				<button class="btn btn-primary" data-toggle="modal"
					data-target="#myModal">Login</button>
			</div>
			
	<!---end: header (top right) --->
		
		
		
		
		
		<!---Login-up-box---->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myLargeModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">

						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">
								Login/Registration</h4>
						</div>



						<div class="modal-body">
							<div class="row">
	<script>
		$('#myModal').modal('show');
	</script>

							<!-- tabs Login|Registration-->
								<ul class="nav nav-tabs">
									<li class="active"><a href="#Login" data-toggle="tab">Login
											<font color='red'>${param.msg}</font>
									</a></li>
									<li><a href="#Registration" data-toggle="tab">Registration
											<font color='green'>${param.msg1}</font><font color='red'>${param.msg2}</font>
									</a></li>
								</ul>
							<!-- end: tabs Login|Registration-->



								<div class="tab-content">

							<!-- Login form-->
									<div class="tab-pane active" id="Login">
										<form role="form" class="form-horizontal" action="Login"
											method="post">
											<div class="form-group">
												<label for="Email" class="col-sm-2 control-label">
													Email</label>
												<div class="col-sm-5">
													<input type="email" name="Email" class="form-control"
														id="Email" placeholder="Email" />
												</div>
											</div>
											<div class="form-group">
												<label for="exampleInputPassword1"
													class="col-sm-2 control-label"> Password</label>
												<div class="col-sm-5">
													<input type="password" name="Password" class="form-control"
														id="Password" placeholder="password" />
												</div>
											</div>
											<div class="row">
												<div class="col-sm-2"></div>
												<div class="col-sm-10">
													<button type="submit" class="btn btn-primary btn-sm">
														Submit</button>
													<!-- <a href="javascript:;">Forgot your password?</a> -->
													
												</div>
											</div>
										</form>
									</div>
								<!-- end: Login form-->


								<!-- Registration form-->
									<div class="tab-pane" id="Registration">
										<form role="form" class="form-horizontal" action="Register"
											method="post">

											<div class="form-group">
												<label for="FirstName" class="col-sm-2 control-label">
													First name</label>
												<div class="col-sm-5">
													<input type="text" class="form-control" name="FirstName"
														id="FirstName" placeholder="First name" 
														maxlength="30"/>
												</div>
											</div>


											<div class="form-group">
												<label for="LastName" class="col-sm-2 control-label">
													Last name</label>
												<div class="col-sm-5">
													<input type="text" class="form-control" name="LastName"
														id="LastName" placeholder="Last name" 
														maxlength="30"/>
												</div>
											</div>


											<div class="form-group">
												<label for="Email" class="col-sm-2 control-label">
													Email</label>
												<div class="col-sm-5">
													<input type="email" name="Email" class="form-control"
														id="Email" placeholder="Email" 
														maxlength="100" required/>
												</div>
											</div>

											<div class="form-group">
												<label for="Phone" class="col-sm-2 control-label">
													Mobile</label>
												<div class="col-sm-5">
													<input type="tel" class="form-control" id="Phone"
														name="Phone" placeholder="Mobile" 
														maxlength="10" pattern="[0-9]*" required />
												</div>
											</div>

											<div class="form-group">
												<label for="Password" class="col-sm-2 control-label">
													Password</label>
												<div class="col-sm-5">
													<input type="password" class="form-control" id="Password"
														name="Password" placeholder="Password" 
														maxlength="100" required/>
												</div>
											</div>

											<div class="row">
												<div class="col-sm-2"></div>
												<div class="col-sm-10">
													<button type="submit" class="btn btn-primary btn-sm">
														Save & Continue</button>
													<button type="reset" class="btn btn-default btn-sm">
														Cancel</button>
												</div>
											</div>
										</form>
									</div>
								<!-- end:Registration form-->

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		<!---end:Login-up-box---->
		
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
						<a href="index.jsp"><img align="middle" height="45px" src="images\Logo.jpg" alt=""/></a>
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
							
							<li class="navbar-header">
								<a href="" class="dropdown-toggle"> 
									<i class="fa fa-home fa-2x"></i>
								</a>
							</li>

						</ul>
					<!-- end: Top menu bar left-->
						
						
							
					<!-- Top menu bar right-->
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown">
								<a href="" data-toggle="dropdown"	class="dropdown-toggle">
									Contact Us <b class="caret"></b></a>
								<ul class="dropdown-menu">
									<li>
										
									<!-- form  Contact Us --------------->
										<form id="contact1" action="AddMessage" name="contact1"
											method="post">
											
																					
										<!-- form: Message-->
											<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
												<textarea class="form-control" name="Message" 
													id="Message" rows="6" placeholder="Your Message ..."
													></textarea>
											</div>
											
										
										<!-- form: submit-->
											<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
												<div class="pull-right">
													<input type="submit" value="SEND" id="submit1"
														class="btn btn-primary small">
												</div>
											</div>
											
											<div class="clearfix"></div>
										</form>
									<!-- end: form  Contact Us --------------->	
										
									</li>
								</ul>
							</li>
						</ul>
					<!-- end: Top menu bar right-->
						
						
						
					</div>
				</nav>
			</div>
	<!---end: top bar menu ------------------------------->




			<div>
				<div class="main-banner">

				<!-- Page Center -->
					<p align="center">
						<iframe  height="900px" width="100%" frameborder="0" name="ifra" 
						style="background-image:url(images/background.jpg);background-size:100%"></iframe>
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
									<li><a href="https://twitter.com" target="_blank"> 
										<i class="fa fa-twitter"></i>
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
					
					
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="copy-rights text-center">
				<p>©LiveInMovie 2021</p>
			</div>
		</div>
	</div>


	<script src="js/responsive-tabs.js"></script>

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
</body>
</html>