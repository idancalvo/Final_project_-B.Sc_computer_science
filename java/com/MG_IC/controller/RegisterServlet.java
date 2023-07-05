package com.MG_IC.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.MG_IC.dao.UserDao;
import com.MG_IC.model.User;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			String firstName = request.getParameter("FirstName");
			String lastName = request.getParameter("LastName");
			String email = request.getParameter("Email");
			String Phone = request.getParameter("Phone");
			String password = request.getParameter("Password");
			User user = new User(firstName, lastName, email, password, Phone);
			boolean success = UserDao.insertUser(user);
			if (success) {
				response.sendRedirect("index.jsp?msg1= Registration success");
			} else {
				response.sendRedirect("index.jsp?msg2=Registration fails");
			}
		} catch (DataFormatException ex) {
			Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
