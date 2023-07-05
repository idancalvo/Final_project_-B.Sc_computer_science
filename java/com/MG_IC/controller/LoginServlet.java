package com.MG_IC.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.MG_IC.dao.UserDao;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setContentType("text/html");

			String email = request.getParameter("Email");
			String password = request.getParameter("Password");
			int userId = UserDao.PasswordCheck(email, password);

			if (userId != -1) {
				boolean isAdmin = (UserDao.isAdmin(userId));
				HttpSession session = request.getSession();

				session.setAttribute("Email", request.getParameter("Email"));
				session.setAttribute("UserId", userId);

				if (isAdmin) {
					response.sendRedirect("AdminPages/AdminHomePage.jsp");
					session.setAttribute("Admin", "yes");
				} else {
					response.sendRedirect("UserPages/UserHomePage.jsp?UserId=" + userId);
				}

			} else {
				response.sendRedirect("index.jsp?msg=login fails");
			}

		} catch (Exception e) {
			Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("index.jsp");
	}

}
