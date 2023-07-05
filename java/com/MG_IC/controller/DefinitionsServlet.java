package com.MG_IC.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.MG_IC.dao.DBManager;
import com.MG_IC.dao.UserDao;

import MainPackege.ExampleValues;

@WebServlet("/Definitions")
public class DefinitionsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String msg = "fails";

		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("UserId");
		if (UserDao.isAdmin(userId)) {

			if (request.getParameter("ResetAllDB") != null) {
				try {
					DBManager.clearAllDB();
					DBManager.createTables();
					msg = "success";
				} catch (SQLException e) {
					System.out.println(e);
					msg = "fails";
				}

			} else if (request.getParameter("AddExampleValues") != null) {
				try {
					ExampleValues.start();
					msg = "success";
				} catch (Exception e) {
					System.out.println(e);
					msg = "fails";
				}

			} else if (request.getParameter("AddAdmin") != null) {
				System.out.println("AddAdmin");
				msg = "success";
			}
		}

		response.sendRedirect("AdminPages/Definitions.jsp?msg=" + msg);
	}
}
