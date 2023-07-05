package com.MG_IC.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.MG_IC.dao.HallDao;
import com.MG_IC.dao.UserDao;
import com.MG_IC.model.Hall;

@WebServlet("/AddHall")
public class AddHallServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean success = false;
		int theaterId = 0;

		try {
			if (request.getParameter("AddHall") != null) {
				theaterId = Integer.parseInt(request.getParameter("AddHall"));
				int rows = Integer.parseInt(request.getParameter("Rows"));
				int seats = Integer.parseInt(request.getParameter("Seats"));

				HttpSession session = request.getSession();
				int userId = (int) session.getAttribute("UserId");
				if (UserDao.isAdmin(userId)) {
					success = HallDao.insertHall(new Hall(theaterId), rows, seats);
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		if (success) {
			response.sendRedirect("AdminPages/ViewHalls.jsp?msg=success&TheaterId=" + theaterId);
		} else {
			response.sendRedirect("AdminPages/ViewHalls.jsp?msg=fails&TheaterId=" + theaterId);
		}
	}
}
