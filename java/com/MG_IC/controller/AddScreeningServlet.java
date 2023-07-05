package com.MG_IC.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.MG_IC.dao.MyTimestamp;
import com.MG_IC.dao.ScreeningDao;
import com.MG_IC.dao.UserDao;
import com.MG_IC.model.Screening;

@WebServlet("/AddScreening")
public class AddScreeningServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean success = false;
		int theaterId = 0;
		try {
			response.setContentType("text/html");

			theaterId = Integer.parseInt(request.getParameter("TheaterId"));
			int movieId = Integer.parseInt(request.getParameter("MovieName"));
			String StrDate = request.getParameter("Date");
			String StrTime = request.getParameter("Time");
			int hallId = Integer.parseInt(request.getParameter("HallNo"));

			MyTimestamp myTimestamp = MyTimestamp.StringConvert(StrDate, StrTime);

			HttpSession session = request.getSession();
			int userId = (int) session.getAttribute("UserId");
			if (UserDao.isAdmin(userId)) {
				success = ScreeningDao.insertScreening(new Screening(movieId, hallId, myTimestamp));
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		if (success) {
			response.sendRedirect("AdminPages/ViewScreenings.jsp?msg=success&TheaterId=" + theaterId);
		} else {
			response.sendRedirect("AdminPages/AddScreening.jsp?msg=fails");
		}
	}

}
