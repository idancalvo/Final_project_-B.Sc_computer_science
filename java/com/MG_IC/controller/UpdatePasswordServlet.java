package com.MG_IC.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.MG_IC.dao.UserDao;

@WebServlet("/UpdatePassword")
@MultipartConfig
public class UpdatePasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean success = false;
		int userId = -1;
		try {
			response.setContentType("text/html");

			userId = Integer.parseInt(request.getParameter("UserId"));

			String oldPassword = request.getParameter("OldPassword");
			String newPassword1 = request.getParameter("NewPassword1");
			String newPassword2 = request.getParameter("NewPassword2");

			if (newPassword1.equals(newPassword2)) {
				HttpSession session = request.getSession();
				int userId2 = (int) session.getAttribute("UserId");
				if (userId2 == userId) {
					success = UserDao.updatePassword(userId, oldPassword, newPassword1);
				}
			}

		} catch (

		Exception e) {
			System.out.println(e);
		}

		if (success) {
			response.sendRedirect("UserPages/UserProfile.jsp?msg=success&UserId=" + userId);
		} else {
			response.sendRedirect("UserPages/UpdatePassword.jsp?msg=fails&UserId=" + userId);
		}
	}
}
