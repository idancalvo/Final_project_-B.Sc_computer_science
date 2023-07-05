package com.MG_IC.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.MG_IC.dao.ScreeningDao;
import com.MG_IC.dao.SeatDao;
import com.MG_IC.dao.TicketDao;
import com.MG_IC.model.Screening;
import com.MG_IC.model.Seat;

@WebServlet("/SeatSelection")
public class SeatSelectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean success = false;
		String Parameters = "";
		ArrayList<Integer> TicketsIds = new ArrayList<Integer>(1);

		try {
			int screeningId = Integer.parseInt(request.getParameter("ScreeningId"));
			int userId = Integer.parseInt(request.getParameter("UserId"));
			Screening screening = ScreeningDao.getScreeningById(screeningId);

			int rows = SeatDao.getNumRow(screening.getHallId());
			int seats = SeatDao.getSeatNum(screening.getHallId());

			ArrayList<Seat> seats1 = new ArrayList<Seat>();

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < seats; j++) {
					if (request.getParameter("Seat" + i + "_" + j).equals("1")) {
						try {
							seats1.add(new Seat(0, i + 1, j + 1));
						} catch (DataFormatException e) {
						}
					}
				}
			}
			success = TicketDao.buyTickets(userId, screeningId, seats1);
			TicketsIds = TicketDao.getTicketsIdsBySeatsAndScreeningId(screeningId, seats1);

			String ScreeningId = "ScreeningId=" + screeningId;
			String NumOfTicket = "&NumOfTicket=" + TicketsIds.size();
			String Tickets = "";
			for (int i = 0; i < TicketsIds.size(); i++) {
				Tickets += "&Ticket" + (i + 1) + "=" + TicketsIds.get(i);
			}
			Parameters = ScreeningId + NumOfTicket + Tickets;

		} catch (Exception e) {
			System.out.println(e);
		}

		if (success) {
			response.sendRedirect("UserPages/OrderSummary.jsp?msg=success" + "&" + Parameters);
		} else {
			response.sendRedirect("UserPages/SeatSelection.jsp?msg=fails");
		}

	}

}
