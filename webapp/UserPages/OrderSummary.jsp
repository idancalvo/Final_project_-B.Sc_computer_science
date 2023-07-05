<%@page import="com.MG_IC.dao.ScreeningDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.MG_IC.model.TicketToDisplay"%>
<%@page import="com.MG_IC.model.ScreeningToDisplay"%>
<%@page import="com.MG_IC.dao.TicketDao"%>





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
</head>

<!--  body  ------------------------------------------------------------------------------------------------------>

<body>


	<%
	int loggedUserId = -1;
	try {
		//Login check
		loggedUserId = Integer.parseInt(session.getAttribute("UserId").toString());
		try {
			int screeningId = Integer.parseInt(request.getParameter("ScreeningId"));
			int numOfTicket = Integer.parseInt(request.getParameter("NumOfTicket"));
			int[] ticketsIds = new int[numOfTicket];
			ArrayList<TicketToDisplay> tickets = new ArrayList<TicketToDisplay>();

			for (int i = 0; i < ticketsIds.length; i++) {
		ticketsIds[i] = Integer.parseInt(request.getParameter("Ticket" + (i + 1)));

			}
			ScreeningToDisplay screening = ScreeningDao.getScreeningDisplayById(screeningId);
	%>


	<div class="main-content">
    <div class="booking-details">
		<div class="payment-right">
               <ul class="place">
			<h3>Order details:</h3>
                        
				<h4>
					<span>Movie-Name:</span><%=screening.getMovieName()%>
				</h4>
				<p>
					<span>Theatre:</span><%=screening.getTheatreName()%>
				</p>
				<p>
					<span>Hall No:</span><%=screening.getHallNum()%>
				</p>
				<p>
					<span>Date:</span>
					<%=screening.getDate()%>
				</p>
				<p>
					<span>Time:</span>
					<%=screening.getTime()%>
				</p>

				<p>
					<span>Num Of Tickets:</span>
					<%=numOfTicket%>
				</p>

				<h4>
					<span>Total :</span>
					<%=ticketsIds.length * 40%>
					ILS
				</h4>
			
			
		<div class="clearfix"></div>
			<br/><br/><br/>
                               
                                        </ul>
                                        
<h3>Ticket details:</h3>	
				
				
				<%
				for (int i = 0; i < ticketsIds.length; i++) {
					TicketToDisplay t = TicketDao.getTicketsDisById(ticketsIds[i]);
				%>
				<p>
                                <ul class="place">
                                Row: <%=t.getSeatRow()%> , Seat: <%=t.getSeatNum()%>
                                 </ul>
				</p>
				<%
				}
				%>
      <input type="button" class="btn btn-primary" value="PrintTicket" onclick="print1()"/>
     
</body>
<script type="text/javascript">
     
            function print1()
            {
               
                window.print();
            }
     </script>
                </div>
      <br/><br/><br/>
			<h1>Thank you!</h1>
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
