package com.MG_IC.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

import javax.sql.rowset.JdbcRowSet;
import com.MG_IC.dao.Queries.QueriesSelect;
import com.MG_IC.model.Seat;
import com.MG_IC.model.Ticket;
import com.MG_IC.model.TicketToDisplay;

public class TicketDao {

//----constants-----------------------------------------------------------------------------//

	// The name of the table in the database
	private static final String TABLE_NAME = "Tickets";

	// Field names in the database
	private static final int TICKET_ID = 0, SCREENING_ID = 1, SEAT_ID = 2, STATUS = 3, USER_ID = 4;
	private static final String[] FILDS_NAME = { "Ticket_Id", "Screening_Id", "Seat_Id", "Status", "User_Id" };

	// Name the ID field in the database
	private static final String ID_NAME = FILDS_NAME[TICKET_ID];

//----Methods:-----------------------------------------------------------------------------//	

	/**
	 * Receiving Ticket from the database according to Ticket_ID
	 * 
	 * @param ticketId
	 * @return Ticket or null if failed
	 */
	public synchronized static Ticket getTicketById(int ticketId) {
		Ticket ticket = null;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetObjById(TABLE_NAME, ID_NAME));
			rowSet.setInt(1, ticketId);
			rowSet.execute();
			if (rowSet.next()) {
				ticket = dbToTicket(rowSet);
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return ticket;
	}

	/**
	 * Receiving all Tickets from the database
	 * 
	 * @return ArrayList of Tickets
	 */
	public synchronized static ArrayList<Ticket> getAllTicket() {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetAllTable(TABLE_NAME));
			rowSet.execute();
			while (rowSet.next()) {
				try {
					tickets.add(dbToTicket(rowSet));
				} catch (Exception e) {
					System.out.println(e);
					continue;
				}
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return tickets;
	}

	/**
	 * update the information of the ticket that were bought
	 * 
	 * @param ticketId
	 * @param userId
	 * @return True = success
	 */
	public synchronized static boolean buyTicke(int ticketId, int newUserId) {
		boolean success = false;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetObjById(TABLE_NAME, ID_NAME));
			rowSet.setInt(1, ticketId);
			rowSet.execute();
			if (rowSet.next()) {
				int oldUserId = rowSet.getInt(FILDS_NAME[TICKET_ID]);
				int oldStatus = rowSet.getInt(FILDS_NAME[STATUS]);

				if (oldStatus == Ticket.AVAILABLE && oldUserId == 0) {
					rowSet.updateInt(FILDS_NAME[STATUS], Ticket.SOLD);
					rowSet.updateInt(FILDS_NAME[USER_ID], newUserId);
					rowSet.updateRow();
					success = true;
				}
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return success;
	}

	/**
	 * Receiving all Tickets at a specific User according to User_Id from the
	 * database
	 * 
	 * @param userId
	 * @return ArrayList of Ticket
	 */
	public synchronized static ArrayList<Ticket> getTicketsByUserId(int userId) {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetObjById(TABLE_NAME, FILDS_NAME[USER_ID]));
			rowSet.setInt(1, userId);
			rowSet.execute();
			while (rowSet.next()) {
				try {
					tickets.add(dbToTicket(rowSet));
				} catch (Exception e) {
					System.out.println(e);
					continue;
				}
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return tickets;
	}

	/**
	 * * Receiving all Ticket (Display format) at a specific User according to
	 * User_Id from the database
	 * 
	 * @param userId
	 * @return ArrayList of TicketToDisplay
	 */
	public synchronized static ArrayList<TicketToDisplay> getTicketsDisByUserId(int userId) {
		ArrayList<TicketToDisplay> tickets = new ArrayList<TicketToDisplay>();
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(
					QueriesSelect.getTicketsDisplay(UserDao.TABLE_NAME + "." + UserDao.FILDS_NAME[UserDao.USER_ID]));
			rowSet.setInt(1, userId);
			rowSet.execute();

			while (rowSet.next()) {
				try {
					tickets.add(dbtoTicketToDisplay(rowSet));
				} catch (Exception e) {
					System.out.println(e);
					continue;
				}
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return tickets;
	}

	public synchronized static ArrayList<TicketToDisplay> getTicketsDisByScreeningId(int screeningId) {
		ArrayList<TicketToDisplay> tickets = new ArrayList<TicketToDisplay>();
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.getTicketsDisplay(
					ScreeningDao.TABLE_NAME + "." + ScreeningDao.FILDS_NAME[ScreeningDao.SCREENING_ID]));
			rowSet.setInt(1, screeningId);
			rowSet.execute();

			while (rowSet.next()) {
				try {
					tickets.add(dbtoTicketToDisplay(rowSet));
				} catch (Exception e) {
					System.out.println(e);
					continue;
				}
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return tickets;
	}

	/**
	 * * Receiving Ticket (Display format) according to Ticket_ID from the database
	 * 
	 * @param ticketId
	 * @return TicketToDisplay or null if failed
	 */
	public synchronized static TicketToDisplay getTicketsDisById(int ticketId) {
		TicketToDisplay ticket = null;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.getTicketsDisplay(FILDS_NAME[TICKET_ID]));
			rowSet.setInt(1, ticketId);
			rowSet.execute();
			if (rowSet.next()) {
				ticket = dbtoTicketToDisplay(rowSet);
			}
			rowSet.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return ticket;
	}

	private synchronized static Ticket dbToTicket(JdbcRowSet rowSet) throws SQLException, DataFormatException {
		int ticketId = rowSet.getInt(FILDS_NAME[TICKET_ID]);
		int screeningId = rowSet.getInt(FILDS_NAME[SCREENING_ID]);
		int seatId = rowSet.getInt(FILDS_NAME[SEAT_ID]);
		int status = rowSet.getInt(FILDS_NAME[STATUS]);
		int userId = rowSet.getInt(FILDS_NAME[USER_ID]);
		return new Ticket(ticketId, screeningId, seatId, status, userId);
	}

	private synchronized static TicketToDisplay dbtoTicketToDisplay(JdbcRowSet rowSet) throws SQLException {

		int ticketId = rowSet.getInt(FILDS_NAME[TICKET_ID]);
		int status = rowSet.getInt(FILDS_NAME[STATUS]);

		String theaterName = rowSet.getString(TheaterDao.FILDS_NAME[TheaterDao.THEATER_NAME]);
		String theaterCity = rowSet.getString(TheaterDao.FILDS_NAME[TheaterDao.CITY]);
		String theaterStreet = rowSet.getString(TheaterDao.FILDS_NAME[TheaterDao.STREET]);
		int theaterHouseNumber = rowSet.getInt(TheaterDao.FILDS_NAME[TheaterDao.HOUSE_NUMBER]);

		int hallNum = rowSet.getInt(HallDao.FILDS_NAME[HallDao.HALL_NUM]);

		int seatRow = rowSet.getInt(SeatDao.FILDS_NAME[SeatDao.SEAT_ROW]);
		int seatNum = rowSet.getInt(SeatDao.FILDS_NAME[SeatDao.SEAT_NUM]);

		String movieName = rowSet.getString(MovieDao.FILDS_NAME[MovieDao.MOVIE_NAME]);
		int movieLength = rowSet.getInt(MovieDao.FILDS_NAME[MovieDao.LENGTH]);

		MyTimestamp dateAndTime = new MyTimestamp(
				rowSet.getTimestamp(ScreeningDao.FILDS_NAME[ScreeningDao.DATE_AND_TIME]));

		int userId = rowSet.getInt(UserDao.FILDS_NAME[UserDao.USER_ID]);
		String userFirstName = rowSet.getString(UserDao.FILDS_NAME[UserDao.FIRST_NAME]);
		String userLastName = rowSet.getString(UserDao.FILDS_NAME[UserDao.LAST_NAME]);

		TicketToDisplay tempTicketDis = new TicketToDisplay(ticketId, status, theaterName, theaterCity, theaterStreet,
				theaterHouseNumber, hallNum, seatRow, seatNum, movieName, movieLength, dateAndTime, userId,
				userFirstName, userLastName);
		return tempTicketDis;
	}

	/**
	 * update the information of the tickets that were bought
	 * 
	 * @param newUserId
	 * @param screeningId
	 * @param seats       - ArrayList <Seat> (hall id does not matter)
	 * @return True = success
	 */
	public synchronized static boolean buyTickets(int newUserId, int screeningId, ArrayList<Seat> seats) {
		boolean success = false;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.getTicketsBySeatsAndScreeningId(seats));
			rowSet.setAutoCommit(false);
			rowSet.setInt(1, screeningId);
			rowSet.execute();
			while (rowSet.next()) {
				int oldUserId = rowSet.getInt(FILDS_NAME[USER_ID]);
				int oldStatus = rowSet.getInt(FILDS_NAME[STATUS]);

				if (oldStatus == Ticket.AVAILABLE && oldUserId == 0) {
					rowSet.updateInt(FILDS_NAME[STATUS], Ticket.SOLD);
					rowSet.updateInt(FILDS_NAME[USER_ID], newUserId);
					rowSet.updateRow();
				}
			}
			rowSet.commit();
			rowSet.close();
			success = true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return success;
	}

	/**
	 * Returns a list of id of tickets according to 'seats' and 'screening'
	 * 
	 * @param screeningId
	 * @param seats
	 * @return ArrayList of ticket Id
	 */
	public synchronized static ArrayList<Integer> getTicketsIdsBySeatsAndScreeningId(int screeningId,
			ArrayList<Seat> seats) {
		ArrayList<Integer> TickesIds = new ArrayList<Integer>();
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.getTicketsBySeatsAndScreeningId(seats));
			rowSet.setInt(1, screeningId);
			rowSet.execute();
			while (rowSet.next()) {
				TickesIds.add(rowSet.getInt(FILDS_NAME[TICKET_ID]));
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return TickesIds;
	}

}
