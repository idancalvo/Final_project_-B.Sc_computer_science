package com.MG_IC.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.MG_IC.dao.MessageDao;
import com.MG_IC.model.Message;

@WebServlet("/AddMessage")
public class AddMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean success = false;
		int userId = 0;
		String returnAddress = "index.jsp";
		try {
			String strUserId = request.getParameter("UserId");
			if (strUserId != null) {
				userId = Integer.parseInt(strUserId);
				returnAddress = "UserPages/UserHomePage.jsp?UserId=" + userId;
			}
			String message = request.getParameter("Message");

			success = MessageDao.insertMessage(new Message(userId, message));

		} catch (Exception e) {
			System.out.println(e);
		}

		if (success) {
			response.sendRedirect(returnAddress);
		} else {
			response.sendRedirect(returnAddress);
		}
	}
}
