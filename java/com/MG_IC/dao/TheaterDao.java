package com.MG_IC.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

import javax.sql.rowset.JdbcRowSet;
import com.MG_IC.dao.Queries.QueriesSelect;
import com.MG_IC.model.Theater;

public class TheaterDao {

//----constants-----------------------------------------------------------------------------//

	// The name of the table in the database
	public static final String TABLE_NAME = "Theaters";

	// Field names in the database
	public static final int THEATER_ID = 0, THEATER_NAME = 1, CITY = 2, STREET = 3, HOUSE_NUMBER = 4;
	public static final String[] FILDS_NAME = { "Theater_id", "Theater_Name", "City", "Street", "House_Number" };

	// Name the ID field in the database
	public static final String ID_NAME = FILDS_NAME[THEATER_ID];

//----Methods:-----------------------------------------------------------------------------//	

	/**
	 * Add a Theaters (Objects) to the database
	 * 
	 * @param theaters
	 * @return Number of Theaters added
	 */
	public static int insertTheaters(Theater[] theaters) {
		int count = 0;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetAllTable(TABLE_NAME));
			rowSet.execute();
			rowSet.moveToInsertRow();
			for (Theater theater : theaters) {
				try {
					rowSet.updateString(FILDS_NAME[THEATER_NAME], theater.getName());
					rowSet.updateString(FILDS_NAME[CITY], theater.getCity());
					rowSet.updateString(FILDS_NAME[STREET], theater.getStreet());
					rowSet.updateInt(FILDS_NAME[HOUSE_NUMBER], theater.getHouseNumber());
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
	 * Add a Theater (Object) to the database
	 * 
	 * @param theater
	 * @return theater Id or -1 if failed
	 */
	public static int insertTheater(Theater theater) {
		int theaterId = -1;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetAllTable(TABLE_NAME));
			rowSet.execute();
			rowSet.moveToInsertRow();

			rowSet.updateString(FILDS_NAME[THEATER_NAME], theater.getName());
			rowSet.updateString(FILDS_NAME[CITY], theater.getCity());
			rowSet.updateString(FILDS_NAME[STREET], theater.getStreet());
			rowSet.updateInt(FILDS_NAME[HOUSE_NUMBER], theater.getHouseNumber());
			rowSet.insertRow();
			theaterId = rowSet.getInt(ID_NAME);

			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return theaterId;
	}

	/**
	 * Receiving Theater from the database according to Theater_ID
	 * 
	 * @param theaterId
	 * @return Theater or null if failed
	 */
	public static Theater getTheaterById(int theaterId) {
		Theater theater = null;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetObjById(TABLE_NAME, ID_NAME));
			rowSet.setInt(1, theaterId);
			rowSet.execute();
			if (rowSet.next()) {
				theater = dbToTheater(rowSet);
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return theater;
	}

	/**
	 * Receiving all Theaters from the database
	 * 
	 * @return ArrayList of Theaters
	 */
	public static ArrayList<Theater> getAllTheater() {
		ArrayList<Theater> theaters = new ArrayList<Theater>();
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetAllTable(TABLE_NAME));
			rowSet.execute();
			while (rowSet.next()) {
				try {
					theaters.add(dbToTheater(rowSet));
				} catch (Exception e) {
					System.out.println(e);
					continue;
				}
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return theaters;
	}

	/**
	 * Theater update in the database<br>
	 * <br>
	 * Correct work:<br>
	 * obj = getTheaterById()<br>
	 * --> changes to the object<br>
	 * --> updateTheater(obj)<br>
	 * 
	 * @param theater
	 * @return True = success
	 */
	public static boolean updateTheater(Theater theater) {
		boolean success = false;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetObjById(TABLE_NAME, ID_NAME));
			rowSet.setInt(1, theater.getTheaterId());
			rowSet.execute();

			if (rowSet.next()) {
				rowSet.updateInt(ID_NAME, theater.getTheaterId());
				rowSet.updateString(FILDS_NAME[THEATER_NAME], theater.getName());
				rowSet.updateString(FILDS_NAME[CITY], theater.getCity());
				rowSet.updateString(FILDS_NAME[STREET], theater.getStreet());
				rowSet.updateInt(FILDS_NAME[HOUSE_NUMBER], theater.getHouseNumber());
				rowSet.updateRow();
				success = true;
			} else {
				success = (insertTheater(theater) != -1);
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return success;
	}

	/**
	 * Deleting a Theater from the database
	 * 
	 * @param theaterId
	 * @return True = success
	 */
	public static boolean deleteTheaterById(int theaterId) {
		boolean success = false;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetObjById(TABLE_NAME, ID_NAME));
			rowSet.setInt(1, theaterId);
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

	private static Theater dbToTheater(JdbcRowSet rowSet) throws SQLException, DataFormatException {
		int theaterId = rowSet.getInt(FILDS_NAME[THEATER_ID]);
		String name = rowSet.getString(FILDS_NAME[THEATER_NAME]);
		String city = rowSet.getString(FILDS_NAME[CITY]);
		String street = rowSet.getString(FILDS_NAME[STREET]);
		int houseNumber = rowSet.getInt(FILDS_NAME[HOUSE_NUMBER]);
		return new Theater(theaterId, name, city, street, houseNumber);
	}
}
