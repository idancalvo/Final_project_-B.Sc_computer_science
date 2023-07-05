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

@WebServlet("/UpdateScreening")
public class UpdateScreeningServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean success = false;
		int screeningId = 0;
		String theaterId = "0";
		try {
			response.setContentType("text/html");

			screeningId = Integer.parseInt(request.getParameter("ScreeningId"));
			Screening screening = ScreeningDao.getScreeningById(screeningId);
			theaterId = request.getParameter("TheaterId");

			screening.setMovieId(Integer.parseInt(request.getParameter("MovieName")));
			String StrDate = request.getParameter("Date");
			String StrTime = request.getParameter("Time");
			screening.setDateAndTime(MyTimestamp.StringConvert(StrDate, StrTime));

			HttpSession session = request.getSession();
			int userId = (int) session.getAttribute("UserId");
			if (UserDao.isAdmin(userId)) {
				success = ScreeningDao.updateScreening(screening);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		if (success) {
			response.sendRedirect("AdminPages/ViewScreenings.jsp?msg=success&TheaterId=" + theaterId);
		} else {
			response.sendRedirect("AdminPages/UpdateScreening.jsp?msg=fails&ScreeningId=" + screeningId);
		}

	}

}
