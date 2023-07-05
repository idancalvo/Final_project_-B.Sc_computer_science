package com.MG_IC.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.MG_IC.dao.HallDao;
import com.MG_IC.dao.TheaterDao;
import com.MG_IC.dao.UserDao;
import com.MG_IC.model.Hall;
import com.MG_IC.model.Theater;

@WebServlet("/AddTheater")
public class AddTheaterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean success = false;
		int TheaterId = 0;
		try {
			response.setContentType("text/html");

			String theaterName = request.getParameter("TheaterName");
			String theaterCity = request.getParameter("TheaterCity");
			String theaterStreet = request.getParameter("TheaterStreet");
			int noHome = Integer.parseInt(request.getParameter("NoHome"));

			HttpSession session = request.getSession();
			int userId = (int) session.getAttribute("UserId");
			if (UserDao.isAdmin(userId)) {
				TheaterId = TheaterDao.insertTheater(new Theater(theaterName, theaterCity, theaterStreet, noHome));

			}

			if (TheaterId != 0) {
				success = true;
				int noOfHalls = Integer.parseInt(request.getParameter("NoOfHalls"));
				for (int i = 1; i <= noOfHalls; i++) {
					int Rows = Integer.parseInt(request.getParameter("Rows" + i));
					int Seats = Integer.parseInt(request.getParameter("Seats" + i));

					if (UserDao.isAdmin(userId)) {
						success &= HallDao.insertHall(new Hall(TheaterId), Rows, Seats);
					}
				}

			}

		} catch (Exception e) {
			System.out.println(e);
			success = false;
		}

		if (success) {
			response.sendRedirect("AdminPages/ViewTheaters.jsp?msg=success");
		} else {
			response.sendRedirect("AdminPages/AddTheater.jsp?msg=fails");
		}
	}

}
