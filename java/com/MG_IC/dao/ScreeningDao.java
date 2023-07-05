package com.MG_IC.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

import javax.sql.rowset.JdbcRowSet;
import com.MG_IC.dao.Queries.QueriesInsert;
import com.MG_IC.dao.Queries.QueriesSelect;
import com.MG_IC.model.Screening;
import com.MG_IC.model.ScreeningToDisplay;

public class ScreeningDao {

//----constants-----------------------------------------------------------------------------//

	// The name of the table in the database
	public static final String TABLE_NAME = "Screenings";

	// Field names in the database
	public static final int SCREENING_ID = 0, MOVIE_ID = 1, HALL_ID = 2, DATE_AND_TIME = 3, OPEN_FOR_SALE = 4;
	public static final String[] FILDS_NAME = { "Screening_Id", "Movie_Id", "Hall_Id", "Date_And_TIME",
			"Open_For_Sale" };

	// Name the ID field in the database
	private static final String ID_NAME = FILDS_NAME[SCREENING_ID];

	// Organization time, between screenings
	public static final int TIME_BETWEEN_SCREENING = 15;

//----Methods:-----------------------------------------------------------------------------//		

	/**
	 * Add a Screening (Object) to the database
	 * 
	 * @param screening
	 * @return True = success
	 */
	public static boolean insertScreening(Screening screening) {
		boolean success = false;

		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetAllTable(TABLE_NAME));
			rowSet.execute();
			rowSet.moveToInsertRow();

			if (isHallAvailable(screening)) {
				rowSet.updateInt(FILDS_NAME[MOVIE_ID], screening.getMovieId());
				rowSet.updateInt(FILDS_NAME[HALL_ID], screening.getHallId());
				rowSet.updateTimestamp(FILDS_NAME[DATE_AND_TIME], screening.getDateAndTime());
				boolean OpenForSale = true;
//DoubleScreening(screening.getHallId(), screening.getMovieId(),screening.getDateAndTime());
				rowSet.updateBoolean(FILDS_NAME[OPEN_FOR_SALE], OpenForSale);
				rowSet.insertRow();
				success = true;
			} else {
				throw new Exception("the hall not available");
			}
			rowSet.close();
		} catch (Exception e) {
			success = false;
			System.out.println(e);
		}
		return success;
	}

	/**
	 * Receiving Screening from the database according to screening_ID
	 * 
	 * @param screeningId
	 * @return Screening or null if failed
	 */
	public static Screening getScreeningById(int screeningId) {
		Screening screening = null;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetObjById(TABLE_NAME, ID_NAME));
			rowSet.setInt(1, screeningId);
			rowSet.execute();
			if (rowSet.next()) {
				screening = dbToScreening(rowSet);
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return screening;
	}

	/**
	 * Receiving ScreeningDisplay from the database according to screening_ID
	 * 
	 * @param screeningId
	 * @return ScreeningDisplay or null if failed
	 */
	public static ScreeningToDisplay getScreeningDisplayById(int screeningId) {
		ScreeningToDisplay screening = null;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetScreeningDisplayById(FILDS_NAME[SCREENING_ID]));
			rowSet.setInt(1, screeningId);
			rowSet.execute();
			if (rowSet.next()) {
				screening = (dbToScreeningDis(rowSet));
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return screening;
	}

	/**
	 * Receiving all Screenings from the database
	 * 
	 * @return ArrayList of Screenings
	 */
	public static ArrayList<Screening> getAllScreening() {
		ArrayList<Screening> screenings = new ArrayList<Screening>();

		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetAllTable(TABLE_NAME));
			rowSet.execute();

			while (rowSet.next()) {
				try {
					screenings.add(dbToScreening(rowSet));
				} catch (Exception e) {
					System.out.println(e);
					continue;
				}
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return screenings;
	}

	/**
	 * Receiving all screenings at a specific Theater according to Theater_Id from
	 * the database
	 * 
	 * @param theaterId
	 * @return ArrayList of ScreeningToDisplay
	 */
	public static ArrayList<ScreeningToDisplay> getScreeningDisplayByTheaterId(int theaterId) {
		ArrayList<ScreeningToDisplay> screenings = new ArrayList<ScreeningToDisplay>();
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect
					.GetScreeningDisplayById(HallDao.TABLE_NAME + "." + HallDao.FILDS_NAME[HallDao.THEATER_ID]));
			rowSet.setInt(1, theaterId);
			rowSet.execute();
			while (rowSet.next()) {
				try {
					screenings.add(dbToScreeningDis(rowSet));
				} catch (Exception e) {
					System.out.println(e);
					continue;
				}
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return screenings;
	}

	/**
	 * Receiving all screenings at a specific Theater,movie and date from the
	 * database
	 * 
	 * @param theaterId
	 * @param movieId
	 * @param date
	 * @return ArrayList of ScreeningToDisplay
	 */
	public static ArrayList<ScreeningToDisplay> getScreeningToDisByTheaterMovieDate(int theaterId, int movieId,
			MyTimestamp date) {
		ArrayList<ScreeningToDisplay> screenings = new ArrayList<ScreeningToDisplay>();
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GAT_SCREENING_DISPLAY_BY_THEATER_DATE_MOVIE);

			MyTimestamp startDate = MyTimestamp.StringConvert(date.dateToString(), "00:00");
			MyTimestamp endDate = MyTimestamp.StringConvert(date.dateToString(), "00:00");
			endDate.addDay(1);

			rowSet.setInt(1, movieId);
			rowSet.setInt(2, theaterId);
			rowSet.setTimestamp(3, startDate);
			rowSet.setTimestamp(4, endDate);
			rowSet.execute();
			while (rowSet.next()) {
				try {
					screenings.add(dbToScreeningDis(rowSet));
				} catch (Exception e) {
					System.out.println(e);
					continue;
				}
			}

			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return screenings;
	}

	/**
	 * Screening update in the database<br>
	 * <br>
	 * Correct work:<br>
	 * obj = getScreeningById()<br>
	 * --> changes to the object<br>
	 * --> updateScreening (obj)<br>
	 * 
	 * @param screening
	 * @return True = success
	 */
	public static boolean updateScreening(Screening screening) {
		boolean success = false;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetObjById(TABLE_NAME, ID_NAME));
			rowSet.setInt(1, screening.getScreeningId());
			rowSet.execute();

			if (rowSet.next()) {
				rowSet.updateInt(ID_NAME, screening.getScreeningId());
				rowSet.updateInt(FILDS_NAME[MOVIE_ID], screening.getMovieId());
				rowSet.updateInt(FILDS_NAME[HALL_ID], screening.getHallId());
				rowSet.updateTimestamp(FILDS_NAME[DATE_AND_TIME], screening.getDateAndTime());
				rowSet.updateBoolean(FILDS_NAME[OPEN_FOR_SALE], screening.isOpenForSale());
				rowSet.updateRow();
				success = true;
			} else {
				success = insertScreening(screening);
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return success;
	}

	/**
	 * Deleting a screening from the database
	 * 
	 * @param screeningId
	 * @return True = success
	 */
	public static boolean deleteScreeningById(int screeningId) {
		boolean success = false;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetObjById(TABLE_NAME, ID_NAME));
			rowSet.setInt(1, screeningId);
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
	 * Checking if in a Theater There is a screening of the same movie at the same
	 * time
	 * 
	 * @param hallId
	 * @param movieId
	 * @param myTimestamp
	 * @return True = There is no screening at the same time
	 */
	public static boolean DoubleScreening(int hallId, int movieId, MyTimestamp myTimestamp) {
		boolean ans = false;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesInsert.GET_DOUBLE_SCREENING);
			rowSet.setInt(1, hallId);
			rowSet.setInt(2, movieId);
			rowSet.setTimestamp(3, myTimestamp);

			rowSet.execute();

			if (!rowSet.next()) {
				ans = true;
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
			ans = false;
		}

		return ans;
	}

	/**
	 * Checking if the hall is available and there is no overlap in screening times
	 * 
	 * @param screening = The screening we would like to add
	 * @return True = There is no overlap
	 */
	public static boolean isHallAvailable(Screening screening) {
		boolean ans = false;
		MyTimestamp startTime = screening.getDateAndTime();
		MyTimestamp endTime = new MyTimestamp(startTime);
		int hallId = screening.getHallId();

		try {
			int movieLength = MovieDao.getMovieById(screening.getMovieId()).getLength();
			endTime.addMinutes(movieLength + TIME_BETWEEN_SCREENING);

			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.IS_HALL_AVAILABLE);
			rowSet.setInt(1, TIME_BETWEEN_SCREENING);
			rowSet.setInt(2, hallId);
			rowSet.setTimestamp(3, startTime);
			rowSet.setTimestamp(4, endTime);
			rowSet.setTimestamp(5, startTime);
			rowSet.setTimestamp(6, endTime);

			rowSet.execute();

			if (!rowSet.next()) {
				ans = true;
			}
			rowSet.close();
		} catch (Exception e) {
			ans = false;
			System.out.println(e);
		}
		return ans;
	}

	private static Screening dbToScreening(JdbcRowSet rowSet) throws SQLException, DataFormatException {
		int screeningId = rowSet.getInt(FILDS_NAME[SCREENING_ID]);
		int movieId = rowSet.getInt(FILDS_NAME[MOVIE_ID]);
		int hallId = rowSet.getInt(FILDS_NAME[HALL_ID]);
		Timestamp dateAndTime = rowSet.getTimestamp(FILDS_NAME[DATE_AND_TIME]);
		boolean openForSale = rowSet.getBoolean(FILDS_NAME[OPEN_FOR_SALE]);
		Screening s = new Screening(screeningId, movieId, hallId, new MyTimestamp(dateAndTime), openForSale);
		return s;
	}

	private static ScreeningToDisplay dbToScreeningDis(JdbcRowSet rowSet) throws SQLException {
		int screeningId = rowSet.getInt(FILDS_NAME[SCREENING_ID]);
		String theatreName = rowSet.getString(TheaterDao.FILDS_NAME[TheaterDao.THEATER_NAME]);
		String hallNum = "" + rowSet.getInt(HallDao.FILDS_NAME[HallDao.HALL_NUM]);
		String movieName = rowSet.getString(MovieDao.FILDS_NAME[MovieDao.MOVIE_NAME]);
		MyTimestamp dateAndTime = new MyTimestamp(rowSet.getTimestamp(FILDS_NAME[DATE_AND_TIME]));
		String openForSale = rowSet.getBoolean(FILDS_NAME[OPEN_FOR_SALE]) ? "open" : "close";
		return new ScreeningToDisplay(screeningId, theatreName, hallNum, movieName, dateAndTime.dateToString(),
				dateAndTime.timeToString(), openForSale);
	}
}
