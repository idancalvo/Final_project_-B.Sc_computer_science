package com.MG_IC.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.MG_IC.dao.TheaterDao;
import com.MG_IC.dao.UserDao;
import com.MG_IC.model.Theater;

@WebServlet("/UpdateTheater")
public class UpdateTheaterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean success = false;
		int theaterId = -1;
		try {
			response.setContentType("text/html");

			theaterId = Integer.parseInt(request.getParameter("TheaterId"));
			Theater theater = TheaterDao.getTheaterById(theaterId);

			theater.setName(request.getParameter("TheaterName"));
			theater.setCity(request.getParameter("TheaterCity"));
			theater.setStreet(request.getParameter("TheaterStreet"));
			theater.setHouseNumber(Integer.parseInt(request.getParameter("NoHome")));

			HttpSession session = request.getSession();
			int userId = (int) session.getAttribute("UserId");
			if (UserDao.isAdmin(userId)) {
				success = TheaterDao.updateTheater(theater);
			}
		} catch (Exception e) {
			System.out.println(e);
			success = false;
		}

		if (success) {
			response.sendRedirect("AdminPages/ViewTheaters.jsp?msg=success");
		} else {
			response.sendRedirect("AdminPages/UpdateTheater.jsp?msg=fails&TheaterId=" + theaterId);
		}

	}

}
