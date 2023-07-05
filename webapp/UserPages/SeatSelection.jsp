<%@page import="com.MG_IC.model.TicketToDisplay"%>
<%@page import="com.MG_IC.dao.TicketDao"%>
<%@page import="com.MG_IC.model.Ticket"%>
<%@page import="com.MG_IC.model.ScreeningToDisplay"%>
<%@page import="com.MG_IC.dao.ScreeningDao"%>
<%@page import="com.MG_IC.dao.SeatDao"%>
<%@page import="com.MG_IC.model.Screening"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.MG_IC.dao.ScreeningDao"%>
<%@page import="java.util.Calendar"%>


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
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->
<!---- start-smoth-scrolling---->
<script type="text/javascript" src="../js/move-top.js"></script>
<script type="text/javascript" src="../js/easing.js"></script>
<!---- start-smoth-scrolling---->

<link href='//fonts.googleapis.com/css?family=Kotta+One'
	rel='stylesheet' type='text/css'>
<link
	href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
	rel='stylesheet' type='text/css'>
<script src="../js/jquery-1.11.0.min.js"></script>
<script src="../js/jquery.seat-charts.js"></script>
<link href="../css/jquery.seat-charts.css" rel="stylesheet"
	type="text/css" media="all" />

</head>

<body>

	<%
	int loggedUserId = -1;
	try {
		//Login check
		loggedUserId = Integer.parseInt(session.getAttribute("UserId").toString());

		try {

			int screeningId = Integer.parseInt(request.getParameter("ScreeningId"));
			ScreeningToDisplay screeningToDisplay = ScreeningDao.getScreeningDisplayById(screeningId);

			Screening screening = ScreeningDao.getScreeningById(screeningId);
			int rows = SeatDao.getNumRow(screening.getHallId());
			int seats = SeatDao.getSeatNum(screening.getHallId());

			String seatStructure = "";

			for (int i = 0; i < rows; i++) {
		seatStructure += "'";
		for (int j = 0; j < seats; j++) {
			seatStructure += "a";
		}
		seatStructure += "'";
		if (i + 1 != rows) {
			seatStructure += ',';
		}
			}

			String unavailableSeats = " ";
			ArrayList<TicketToDisplay> tickets = TicketDao.getTicketsDisByScreeningId(screeningId);

			for (TicketToDisplay ticket : tickets) {
		if (ticket.getStatus() != Ticket.AVAILABLE) {
			unavailableSeats += "'" + ticket.getSeatRow() + "_" + ticket.getSeatNum() + "',";
		}

			}
			if (unavailableSeats.charAt(unavailableSeats.length() - 1) == ',') {
		unavailableSeats = unavailableSeats.substring(0, unavailableSeats.length() - 1);
			}
	%>



	<div class="main-content">

		<div class="seat-section">
			<div class="col-md-8 seat-left">
				<h1>Choose your seat</h1>
				<div class="main">
					<div class="demo">
						<div id="seat-map"></div>
						<div style="clear: both"></div>
					</div>
					<script type="text/javascript">
						var price = 40; //price
						$(document)
								.ready(
										function() {
											var $cart = $('#selected-seats'), //Sitting Area
											$counter = $('#counter'), //Votes
											$total = $('#total'); //Total money  
											document.getElementById("theprice").innerHTML = price;
											var sc = $('#seat-map')
													.seatCharts(
															{
																map : [
					<%=seatStructure%>
						],
																naming : {
																	top : false,
																	getLabel : function(
																			character,
																			row,
																			column) {
																		return column;
																	}
																},
																legend : { //Definition legend
																	node : $('#legend'),
																	items : [
																			[
																					'a',
																					'available',
																					'Available' ],
																			[
																					'a',
																					'unavailable',
																					'Sold' ],
																			[
																					'a',
																					'selected',
																					'Selected' ] ]
																},
																click : function() { //Click event
																	if (this
																			.status() == 'available') {//optional seat
																		$(
																				'<li>Row'
																						+ (this.settings.row + 1)
																						+ ' Seat'
																						+ this.settings.label
																						+ '</li>')
																				.attr(
																						'id',
																						'cart-item-'
																								+ this.settings.id)
																				.data(
																						'seatId',
																						this.settings.id)
																				.appendTo(
																						$cart);
																		$counter
																				.text(sc
																						.find('selected').length + 1);
																		$total
																				.text(recalculateTotal(sc)
																						+ price);

																		let i = parseInt(this.settings.row);
																		let j = parseInt(this.settings.column);
																		$(
																				"#Seat"
																						+ i
																						+ "_"
																						+ j)
																				.val(
																						1);

																		return 'selected';
																	} else if (this
																			.status() == 'selected') {//Checked
																		//Update Number
																		$counter
																				.text(sc
																						.find('selected').length - 1);
																		//update totalnum
																		$total
																				.text(recalculateTotal(sc)
																						- price);
																		//Delete reservation
																		$(
																				'#cart-item-'
																						+ this.settings.id)
																				.remove();
																		//optional

																		let i = parseInt(this.settings.row);
																		let j = parseInt(this.settings.column);
																		$(
																				"#Seat"
																						+ i
																						+ "_"
																						+ j)
																				.val(
																						"");

																		return 'available';
																	} else if (this
																			.status() == 'unavailable') { //sold
																		return 'unavailable';
																	} else {
																		return this
																				.style();
																	}
																}
															});
											//sold seat
											sc.get([
					<%=unavailableSeats%>
						])
													.status('unavailable');
										});
						//sum total money
						function recalculateTotal(sc) {
							var total = 0;
							sc.find('selected').each(function() {
								total += price;
							});
							return total;
						}
					</script>

					<script type="text/javascript">
						function additem() {
							var textb = document.getElementById("combo1");
							var textc = document.getElementById("addsnack");
							textc.value += textb.value;
						}
					</script>
				</div>
			</div>

			<div class="col-md-4">
				<div class="payment-right">


					<h3>Seats Summary</h3>
					<h6>
						<span>Movie-Name:</span><%=screeningToDisplay.getMovieName()%>
					</h6>
					<p>
						<span>Theatre:</span>
						<%=screeningToDisplay.getTheatreName()%>
					</p>
					<p>
						<span>Date:</span>
						<%=screeningToDisplay.getDate()%>
					</p>
					<p>
						<span>Time:</span>
						<%=screeningToDisplay.getTime()%>
					</p>
					<p>
						<span>Seat Info:</span><br>
					<ul id="selected-seats" class="scrollbar scrollbar1"></ul>
					</p>
					<p>
						<span>Qty:</span> <span id="counter">0</span>
					</p>
					<p>
						<span>Total (NIS) :</span><span id="total">0</span>
					</p>


					<form class="form-horizontal" action="../SeatSelection"
						method="post">

						<input type="hidden" class="form-control" name="ScreeningId"
							id="ScreeningId" value="<%=screeningId%>"> <input
							type="hidden" class="form-control" name="UserId" id="UserId"
							value="<%=loggedUserId%>">

						<%
						for (int i = 0; i < rows; i++) {
							for (int j = 0; j < seats; j++) {
						%>
						<input type="hidden" class="form-control"
							name="<%="Seat" + i + "_" + j%>" id="<%="Seat" + i + "_" + j%>">
						<%
						}
						}
						%>

						<input type="submit" class="btn btn-primary" value="Book Now">
					</form>




				</div>
				<div class="ticket-note">

					<h3>Note:</h3>
					<ol>
						<p>
							<span>Ticket price (NIS) : </span><span id="theprice">0</span>
						</p>
						<div id="legend"></div>

					</ol>
				</div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>

	<!--start-carrer-->



	<div class="clearfix"></div>




	<div class="e-payment-section">
		<div class="col-md-8 payment-left">
			<!-- payment -->
			<div class="payment-options">
				<h3>payment-options</h3>
				<br />
				<div id="tab1" class="tab-grid">
					<form >

						<div>
							<img src="../images/payment.png" alt="payment" />
						</div>

						<div>
							<input type="text" class="payment" value="Enter Your Card Number"
								pattern="[0-9]+" maxlength="16">
						</div>

						<div>
							<input type="text" class="payment" value="Your Name On Card"
								maxlength="30">
						</div>


						<div>
							<p>
								Expiry: <select class="month">
									<option value="">Select</option>
									<option value="01">01</option>
									<option value="02">02</option>
									<option value="03">03</option>
									<option value="04">04</option>
									<option value="05">05</option>
									<option value="06">06</option>
									<option value="07">07</option>
									<option value="08">08</option>
									<option value="09">09</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
								</select> <select class="year">
									<option value="">Year</option>
									<%
									int year = Calendar.getInstance().get(Calendar.YEAR);
									for (int i = year; i < year + 30; i++) {
									%>
									<option value="<%=i%>"><%=i%></option>
									<%
									}
									%>
								</select> <input type="text" class="cvv" value="cvv" pattern="[0-9]+"
									maxlength="3">

							</p>
						</div>

					</form>


				</div>
			</div>
			<!-- end:payment     -->
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