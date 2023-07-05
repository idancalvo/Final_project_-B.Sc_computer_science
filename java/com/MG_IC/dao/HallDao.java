package com.MG_IC.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

import javax.sql.rowset.JdbcRowSet;
import com.MG_IC.dao.Queries.QueriesInsert;
import com.MG_IC.dao.Queries.QueriesSelect;
import com.MG_IC.model.Hall;

public class HallDao {

//----constants-----------------------------------------------------------------------------//

	// The name of the table in the database
	public static final String TABLE_NAME = "Halls";

	// Field names in the database
	public static final int HALL_ID = 0, THEATER_ID = 1, HALL_NUM = 2;
	public static final String[] FILDS_NAME = { "Hall_id", "Theater_id", "Hall_num" };

	// Name the ID field in the database
	private static final String ID_NAME = FILDS_NAME[HALL_ID];

//----Methods:-----------------------------------------------------------------------------//	

	/**
	 * Add a halls (Objects) to the database
	 * 
	 * @param halls         = Array of halls
	 * @param ArrNumRowsArr = (array) Number of rows in the hall
	 * @param ArrNumseats   = (array) Number of seats in a row
	 * @return Number of halls added
	 */
	public static int insertHalls(Hall[] halls, int[] arrNumRows, int[] arrNumseats) {
		int count = 0;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetAllTable(TABLE_NAME));
			rowSet.execute();
			rowSet.moveToInsertRow();

			for (int i = 0; i < halls.length; i++) {
				try {
					rowSet.updateInt(FILDS_NAME[THEATER_ID], halls[i].getTheaterId());
					rowSet.updateInt(FILDS_NAME[HALL_NUM], getFreeHallNum(halls[i].getTheaterId()));
					rowSet.insertRow();
					count++;
					int hallId = rowSet.getInt(ID_NAME);
					SeatDao.insertSeats(hallId, arrNumRows[i], arrNumseats[i]);
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

	/***
	 * Add a hall (Object) to the database
	 * 
	 * @param hall
	 * @param NumRows  = Number of rows in the hall
	 * @param Numseats = Number of seats in a row
	 * @return True = success
	 */
	public static boolean insertHall(Hall hall, int NumRows, int Numseats) {
		Hall[] halls = { hall };
		int[] arrNumRows = { NumRows };
		int[] arrNumseats = { Numseats };
		return (insertHalls(halls, arrNumRows, arrNumseats) > 0);
	}

	/**
	 * Receiving Hall from the database according to Hall_ID
	 * 
	 * @param hallId
	 * @return Hall or null if failed
	 */
	public static Hall getHallById(int hallId) {
		Hall hall = null;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetObjById(TABLE_NAME, ID_NAME));
			rowSet.setInt(1, hallId);
			rowSet.execute();

			if (rowSet.next()) {
				hall = dbToHall(rowSet);
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return hall;
	}

	/**
	 * Receiving all Halls from the database
	 * 
	 * @return ArrayList of Halls
	 */
	public static ArrayList<Hall> getAllHall() {
		ArrayList<Hall> halls = new ArrayList<Hall>();
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetAllTable(TABLE_NAME));
			rowSet.execute();

			while (rowSet.next()) {
				try {
					halls.add(dbToHall(rowSet));
				} catch (Exception e) {
					System.out.println(e);
					continue;
				}
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return halls;
	}

	/**
	 * Deleting a Hall from the database
	 * 
	 * @param hallId
	 * @return True = success
	 */
	public static boolean deleteHallById(int hallId) {
		boolean success = false;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetObjById(TABLE_NAME, ID_NAME));
			rowSet.setInt(1, hallId);
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
	 * Finds a free hall number(minimum), in a theater
	 * 
	 * @param theaterId
	 * @return Hall number or -1 if failed
	 */
	public static int getFreeHallNum(int theaterId) {
		int freeHallNum = -1;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesInsert.GET_FREE_HALL_NUM);
			rowSet.setInt(1, theaterId);
			rowSet.execute();
			if (rowSet.next()) {
				freeHallNum = rowSet.getInt(1);
			}

			if (freeHallNum == 0) {
				freeHallNum++;
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return freeHallNum;
	}

	/**
	 * Receiving all hall at a specific Theater according to Theater_Id from the
	 * database
	 * 
	 * @param theaterId
	 * @return ArrayList of Halls
	 */
	public static ArrayList<Hall> getHallsByTheaterId(int theaterId) {
		ArrayList<Hall> halls = new ArrayList<Hall>();
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetObjById(TABLE_NAME, FILDS_NAME[THEATER_ID]));
			rowSet.setInt(1, theaterId);
			rowSet.execute();

			while (rowSet.next()) {
				try {
					halls.add(dbToHall(rowSet));
				} catch (Exception e) {
					System.out.println(e);
					continue;
				}
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return halls;
	}

	private static Hall dbToHall(JdbcRowSet rowSet) throws SQLException, DataFormatException {
		int theaterId = rowSet.getInt(FILDS_NAME[THEATER_ID]);
		int hallId = rowSet.getInt(FILDS_NAME[HALL_ID]);
		int hallNum = rowSet.getInt(FILDS_NAME[HALL_NUM]);
		return new Hall(hallId, theaterId, hallNum);
	}

}
