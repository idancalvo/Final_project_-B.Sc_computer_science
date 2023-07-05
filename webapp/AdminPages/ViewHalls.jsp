<%@page import="com.MG_IC.dao.SeatDao"%>
<%@page import="com.MG_IC.model.Theater"%>
<%@page import="com.MG_IC.dao.TheaterDao"%>
<%@page import="com.MG_IC.model.Hall"%>
<%@page import="com.MG_IC.dao.HallDao"%>
<%@page import="java.util.ArrayList"%>

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

			try {
		int theaterId = Integer.parseInt(request.getParameter("TheaterId"));
		Theater theater = TheaterDao.getTheaterById(theaterId);
	%>

	<div class="container" style="margin: 10px;">
		<div class="row" align="center">



			<h5>
				<font color=black> Theater name: <%=theater.getName()%></font>
			</h5>

			<h5>
				<font color=black> Theater Address: <%=theater.getStreet() + " " + theater.getHouseNumber() + ", " + theater.getCity()%>
				</font>
			</h5>
		</div>

		<h2 align=center>
			<font color=black> Halls list :- ${param.msg}</font>
		</h2>

		<!---- Add Hall button -->
		<div class="form-group">
			<form action="../AddHall" method="post">

				<div class="form-group">
					<button type="submit" name="AddHall" id="AddHall"
						value=<%=theaterId%>>
						Add Hall &nbsp; <i class="fa fa-plus-square-o" aria-hidden="true"></i>
					</button>
					<br />
					<br />
					<div>
						<label class="control-label col-sm-2" for="Rows">Rows:</label>
						<div class="col-md-2">

							<input type="number" id="Rows" class="form-control" name="Rows"
								placeholder="Rows" max=30 min=1 required>
						</div>
					</div>
					<div>
						<label class="control-label col-sm-2" for="Seats">Seats:</label>
						<div class="col-md-2">
							<input type="number" id="Seats" class="form-control" name="Seats"
								placeholder="seats" max=50 min=1 required>
						</div>
					</div>
				</div>
			</form>
		</div>

		<br />
		<br />
		<!---- Halls table -->
		<div class="table-responsive shadow p-3 mb-5 bg-white rounded">
			<table class="table table-striped" align="center" border=1 width=90%>
				<thead>
					<tr>
						<th>#</th>
						<th>Hall No.</th>
						<th>Number of rows</th>
						<th>Seats in a row</th>
						<th>Delete</th>

					</tr>
				</thead>

				<tbody>
					<%
					ArrayList<Hall> halls = HallDao.getHallsByTheaterId(theaterId);
					int i = 0;
					for (Hall hall : halls) {
						i++;
					%>

					<tr>
						<td align=center><%=i%></td>
						<td align=center><%=hall.getHallNum()%></td>
						<td align=center><%=SeatDao.getNumRow(hall.getHallId())%></td>
						<td align=center><%=SeatDao.getSeatNum(hall.getHallId())%></td>

						<td align=center><a
							href="../Delete?Item=Hall&HallId=<%=hall.getHallId()%>&TheaterId=<%=hall.getTheaterId()%>">
								<i class="fa fa-trash-o fa-lg"></i>
						</a></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
	</div>
	
	<%
	} catch (Exception e) {
	System.out.println(e);
	}
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