package com.MG_IC.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

import javax.sql.rowset.JdbcRowSet;
import com.MG_IC.dao.Queries.QueriesSelect;
import com.MG_IC.model.Seat;

public class SeatDao {

//----constants-----------------------------------------------------------------------------//

	// The name of the table in the database
	private static final String TABLE_NAME = "Seats";

	// Field names in the database
	public static final int SEAT_ID = 0, HALL_ID = 1, SEAT_ROW = 2, SEAT_NUM = 3;
	public static final String[] FILDS_NAME = { "Seat_Id", "Hall_Id", "Seat_Row", "Seat_Num" };

	// Name the ID field in the database
	private static final String ID_NAME = "Seat_Id";

//----Methods:-----------------------------------------------------------------------------//	

	/**
	 * Add a Seats (Objects) to the database
	 * 
	 * @param seats
	 * @return Number of Seats added
	 */
	public static int insertSeats(Seat[] seats) {
		int count = 0;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetAllTable(TABLE_NAME));
			rowSet.execute();
			rowSet.moveToInsertRow();
			for (int i = 0; i < seats.length; i++) {
				try {
					rowSet.updateInt(FILDS_NAME[HALL_ID], seats[i].getHallId());
					rowSet.updateInt(FILDS_NAME[SEAT_ROW], seats[i].getSeatRow());
					rowSet.updateInt(FILDS_NAME[SEAT_NUM], seats[i].getSeatNum());
					rowSet.insertRow();
					count++;
				} catch (Exception e) {
					System.out.println(e);
					continue;
				}
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return count;
	}

	/**
	 * Add a Seat (Object) to the database
	 * 
	 * @param seat
	 * @return True = success
	 */
	public static boolean insertSeat(Seat seat) {
		Seat[] seats = { seat };
		return (insertSeats(seats) > 0);
	}

	/**
	 * Add a Seats (Objects) to the database
	 * 
	 * @param hallId
	 * @param NumRows  = Number of rows in the hall
	 * @param Numseats = Number of seats in a row
	 * @return True = success
	 */
	public static boolean insertSeats(int hallId, int NumRows, int Numseats) {
		Seat[] seats = CreateSeats(hallId, NumRows, Numseats);
		return (insertSeats(seats) > 0);
	}

	/**
	 * Receiving Seat from the database according to Seat_ID
	 * 
	 * @param seatId
	 * @return Seat or null if failed
	 */
	public static Seat getSeatById(int seatId) {
		Seat seat = null;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetObjById(TABLE_NAME, ID_NAME));
			rowSet.setInt(1, seatId);
			rowSet.execute();
			if (rowSet.next()) {
				seat = dbToSeat(rowSet);
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return seat;
	}

	/**
	 * Receiving all Seats from the database
	 * 
	 * @return ArrayList of Seats
	 */
	public static ArrayList<Seat> getAllSeat() {
		ArrayList<Seat> seats = new ArrayList<Seat>();
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetAllTable(TABLE_NAME));
			rowSet.execute();
			while (rowSet.next()) {
				try {
					seats.add(dbToSeat(rowSet));
				} catch (Exception e) {
					System.out.println(e);
					continue;
				}
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return seats;
	}

	/**
	 * Seat update in the database<br>
	 * <br>
	 * Correct work:<br>
	 * obj = getSeatById()<br>
	 * --> changes to the object<br>
	 * --> updateSeat (obj)<br>
	 * 
	 * @param seat
	 * @return True = success
	 */
	public static boolean updateSeat(Seat seat) {
		boolean success = false;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetObjById(TABLE_NAME, ID_NAME));
			rowSet.setInt(1, seat.getSeatId());
			rowSet.execute();
			if (rowSet.next()) {
				rowSet.updateInt(ID_NAME, seat.getSeatId());
				rowSet.updateInt(FILDS_NAME[HALL_ID], seat.getHallId());
				rowSet.updateInt(FILDS_NAME[SEAT_ROW], seat.getSeatRow());
				rowSet.updateInt(FILDS_NAME[SEAT_NUM], seat.getSeatNum());
				rowSet.updateRow();
				success = true;
			} else {
				success = insertSeat(seat);
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return success;
	}

	/**
	 * Deleting a Seat from the database
	 * 
	 * @param seatId
	 * @return True = success
	 */
	public static boolean deleteSeatById(int seatId) {
		boolean success = false;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetObjById(TABLE_NAME, ID_NAME));
			rowSet.setInt(1, seatId);
			rowSet.execute();
			if (rowSet.next()) {
				rowSet.deleteRow();
				success = true;
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return success;
	}

	/**
	 * Receiving all Seats at a specific Hall according to Hall_Id from the database
	 * 
	 * @param hallId
	 * @return ArrayList of Seats
	 */
	public static ArrayList<Seat> getSeatByHallId(int hallId) {
		ArrayList<Seat> seats = new ArrayList<Seat>();
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetObjById(TABLE_NAME, FILDS_NAME[HALL_ID]));
			rowSet.setInt(1, hallId);
			rowSet.execute();
			while (rowSet.next()) {
				try {
					seats.add(dbToSeat(rowSet));
				} catch (Exception e) {
					System.out.println(e);
					continue;
				}
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return seats;
	}

	private static Seat dbToSeat(JdbcRowSet rowSet) throws SQLException, DataFormatException {
		int seatId = rowSet.getInt(FILDS_NAME[SEAT_ID]);
		int hallId = rowSet.getInt(FILDS_NAME[HALL_ID]);
		int seatRow = rowSet.getInt(FILDS_NAME[SEAT_ROW]);
		int seatNum = rowSet.getInt(FILDS_NAME[SEAT_NUM]);
		return new Seat(seatId, hallId, seatRow, seatNum);
	}

	/**
	 * Creates an array of seats according to a hall plan
	 * 
	 * @param hallId
	 * @param NumRows  = Number of rows in the hall
	 * @param Numseats = Number of seats in a row
	 * @return array of seats
	 */
	public static Seat[] CreateSeats(int hallId, int NumRows, int Numseats) {
		Seat[] seats = new Seat[NumRows * Numseats];
		for (int i = 0; i < NumRows; i++) {
			for (int j = 0; j < Numseats; j++) {
				try {
					seats[i * Numseats + j] = new Seat(hallId, i + 1, j + 1);
				} catch (Exception e) {
					System.out.println(e);
					continue;
				}
			}
		}
		return seats;
	}

	/**
	 * What is Number of rows in the hall?
	 * 
	 * @param hallId
	 * @return Number of rows in the hall or -1 if failed
	 */
	public static int getNumRow(int hallId) {
		int numRows = 0;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.getSeatInHall(FILDS_NAME[SEAT_ROW]));
			rowSet.setInt(1, hallId);
			rowSet.execute();
			numRows = 0;
			int seatNum = -1;

			while (rowSet.next()) {
				numRows++;
				if (seatNum == -1) {
					seatNum = rowSet.getInt(1);
				} else if (seatNum != rowSet.getInt(1)) {
					throw new Exception("Uneven number of rows, Hall Id:" + hallId + " Row:" + numRows);
				}
			}
			rowSet.close();
		} catch (Exception e) {
			numRows = -1;
			System.out.println(e);

		}
		return numRows;
	}

	/**
	 * What is Number of seats in a row?
	 * 
	 * @param hallId
	 * @return Number of seats in a row or -1 if failed
	 */
	public static int getSeatNum(int hallId) {
		int seatNum = 0;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.getSeatInHall(FILDS_NAME[SEAT_NUM]));
			rowSet.setInt(1, hallId);
			rowSet.execute();
			int numRows = -1;

			while (rowSet.next()) {
				seatNum++;
				if (numRows == -1) {
					numRows = rowSet.getInt(1);
				} else if (numRows != rowSet.getInt(1)) {
					throw new Exception("Uneven number of rows, Hall Id:" + hallId + " Seat:" + seatNum);
				}
			}
			rowSet.close();
		} catch (Exception e) {
			seatNum = -1;
			System.out.println(e);
		}

		return seatNum;
	}

}
