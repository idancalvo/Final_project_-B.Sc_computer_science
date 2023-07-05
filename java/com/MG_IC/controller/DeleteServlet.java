package com.MG_IC.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.MG_IC.dao.HallDao;
import com.MG_IC.dao.MessageDao;
import com.MG_IC.dao.MovieDao;
import com.MG_IC.dao.ScreeningDao;
import com.MG_IC.dao.TheaterDao;
import com.MG_IC.dao.UserDao;

@WebServlet("/Delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		int userId = Integer.parseInt(session.getAttribute("UserId").toString());
		if (UserDao.isAdmin(userId)) {

			try {

				switch (request.getParameter("Item")) {

				case "User":
					deleteUser(request, response);
					break;

				case "Movie":
					deleteMovie(request, response);
					break;

				case "Theater":
					deleteTheater(request, response);
					break;

				case "Hall":
					deleteHall(request, response);
					break;

				case "Screening":
					deleteScreening(request, response);
					break;

				case "Message":
					deleteMessage(request, response);
					break;

				}

			} catch (Exception e) {
				System.out.println(e);
				response.sendRedirect("AdminPages/AdminInfo.jsp?msg=fails");
			}

		}
	}

	private void deleteMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean success = false;
		int messageId = 0;

		try {
			messageId = Integer.parseInt(request.getParameter("MessageId"));
			success = MessageDao.deleteMessageById(messageId);
		} catch (Exception e) {
			success = false;
			System.out.println(e);
		}

		if (success) {
			response.sendRedirect("AdminPages/ViewMessages.jsp?msg=item deleted");
		} else {
			response.sendRedirect("AdminPages/ViewMessages.jsp?msg=item deletion fails");
		}

	}

	private void deleteScreening(HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean success = false;
		int screeningId = 0;
		int theaterId = 0;
		try {
			screeningId = Integer.parseInt(request.getParameter("ScreeningId"));
			success = ScreeningDao.deleteScreeningById(screeningId);
			theaterId = Integer.parseInt(request.getParameter("TheaterId"));

		} catch (Exception e) {
			success = false;
			System.out.println(e);
		}

		if (success) {
			response.sendRedirect("AdminPages/ViewScreenings.jsp?msg=item deleted&TheaterId=" + theaterId);
		} else {
			response.sendRedirect("AdminPages/ViewScreenings.jsp?msg=item deletion fails&TheaterId=" + theaterId);
		}
	}

	private void deleteTheater(HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean success = false;
		int theaterId = 0;

		try {
			theaterId = Integer.parseInt(request.getParameter("TheaterId"));
			success = TheaterDao.deleteTheaterById(theaterId);

		} catch (Exception e) {
			success = false;
			System.out.println(e);
		}

		if (success) {
			response.sendRedirect("AdminPages/ViewTheaters.jsp?msg=item deleted");
		} else {
			response.sendRedirect("AdminPages/ViewTheaters.jsp?msg=item deletion fails");
		}

	}

	private void deleteMovie(HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean success = false;
		int movieId = 0;

		try {
			movieId = Integer.parseInt(request.getParameter("MovieId"));
			success = MovieDao.deleteMovieById(movieId);

		} catch (Exception e) {
			success = false;
			System.out.println(e);
		}

		if (success) {
			response.sendRedirect("AdminPages/ViewMovies.jsp?msg=item deleted");
		} else {
			response.sendRedirect("AdminPages/ViewMovies.jsp?msg=item deletion fails");
		}

	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean success = false;
		int userId = 0;

		try {
			userId = Integer.parseInt(request.getParameter("UserId"));
			success = UserDao.deleteUserById(userId);

		} catch (Exception e) {
			success = false;
			System.out.println(e);
		}

		if (success) {
			response.sendRedirect("AdminPages/ViewUsers.jsp?msg=item deleted");
		} else {
			response.sendRedirect("AdminPages/ViewUsers.jsp?msg=item deletion fails");
		}

	}

	private void deleteHall(HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean success = false;
		int theaterId = 0;
		int hallId = 0;
		try {
			hallId = Integer.parseInt(request.getParameter("HallId"));
			theaterId = Integer.parseInt(request.getParameter("TheaterId"));

			success = HallDao.deleteHallById(hallId);

		} catch (Exception e) {
			success = false;
			System.out.println(e);
		}

		if (success) {
			response.sendRedirect("AdminPages/ViewHalls.jsp?msg=item deleted&TheaterId=" + theaterId);
		} else {
			response.sendRedirect("AdminPages/ViewHalls.jsp?msg=item deletion fails&&TheaterId=" + theaterId);
		}

	}

}
