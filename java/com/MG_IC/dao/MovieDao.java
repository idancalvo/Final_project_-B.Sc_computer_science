package com.MG_IC.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

import javax.sql.rowset.JdbcRowSet;
import com.MG_IC.dao.Queries.QueriesSelect;
import com.MG_IC.model.Movie;

public class MovieDao {

//----constants-----------------------------------------------------------------------------//

	// The name of the table in the database
	private static final String TABLE_NAME = "Movies";

	// Field names in the database
	public static final int MOVIE_ID = 0, MOVIE_NAME = 1, YEAR = 2, GENRE = 3, SUMMARY = 4, DIRECTOR = 5, LENGTH = 6,
			IMAGE_ID = 7, TRAILER = 8;
	public static final String[] FILDS_NAME = { "Movie_id", "Movie_Name", "Year", "Genre", "Summary", "Director",
			"Length", "Image_Id", "Trailer" };

	// Name the ID field in the database
	private static final String ID_NAME = FILDS_NAME[MOVIE_ID];

//----Methods:-----------------------------------------------------------------------------//

	/**
	 * Add a Movies (Objects) to the database
	 * 
	 * @param movies
	 * @return Number of Movies added
	 */
	public static int insertMovies(Movie[] movies) {
		int count = 0;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetAllTable(TABLE_NAME));
			rowSet.execute();
			rowSet.moveToInsertRow();
			for (int i = 0; i < movies.length; i++) {
				try {
					rowSet.updateString(FILDS_NAME[MOVIE_NAME], movies[i].getName());
					rowSet.updateInt(FILDS_NAME[YEAR], movies[i].getYear());
					rowSet.updateString(FILDS_NAME[GENRE], movies[i].getGenre());
					rowSet.updateString(FILDS_NAME[SUMMARY], movies[i].getSummary());
					rowSet.updateString(FILDS_NAME[DIRECTOR], movies[i].getDirector());
					rowSet.updateInt(FILDS_NAME[LENGTH], movies[i].getLength());
					rowSet.updateInt(FILDS_NAME[IMAGE_ID], movies[i].getImageId());
					rowSet.updateString(FILDS_NAME[TRAILER], movies[i].getTrailer());
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
	 * Add a Movie (Object) to the database
	 * 
	 * @param movie
	 * @return True = success
	 */
	public static boolean insertMovie(Movie movie) {
		Movie[] movies = { movie };
		return (insertMovies(movies) > 0);
	}

	/**
	 * Receiving Movie from the database according to Movie_ID
	 * 
	 * @param movieId
	 * @return Movie or null if failed
	 */
	public static Movie getMovieById(int movieId) {
		Movie movie = null;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetObjById(TABLE_NAME, ID_NAME));
			rowSet.setInt(1, movieId);
			rowSet.execute();

			if (rowSet.next()) {
				movie = dbToMovie(rowSet);
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return movie;
	}

	/**
	 * Receiving all Movies from the database
	 * 
	 * @return ArrayList of Movies
	 */
	public static ArrayList<Movie> getAllMovie() {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetAllTable(TABLE_NAME));
			rowSet.execute();
			while (rowSet.next()) {
				try {
					movies.add(dbToMovie(rowSet));
				} catch (Exception e) {
					System.out.println(e);
					continue;
				}
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return movies;
	}

	/**
	 * Movie update in the database<br>
	 * <br>
	 * Correct work:<br>
	 * obj = getMovieById()<br>
	 * --> changes to the object<br>
	 * --> updateMovie(obj)<br>
	 * 
	 * @param movie
	 * @return True = success
	 */
	public static boolean updateMovie(Movie movie) {
		boolean success = false;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetObjById(TABLE_NAME, ID_NAME));
			rowSet.setInt(1, movie.getMovieId());
			rowSet.execute();
			if (rowSet.next()) {
				rowSet.updateInt(ID_NAME, movie.getMovieId());
				rowSet.updateString(FILDS_NAME[MOVIE_NAME], movie.getName());
				rowSet.updateInt(FILDS_NAME[YEAR], movie.getYear());
				rowSet.updateString(FILDS_NAME[GENRE], movie.getGenre());
				rowSet.updateString(FILDS_NAME[SUMMARY], movie.getSummary());
				rowSet.updateString(FILDS_NAME[DIRECTOR], movie.getDirector());
				rowSet.updateInt(FILDS_NAME[LENGTH], movie.getLength());
				rowSet.updateInt(FILDS_NAME[IMAGE_ID], movie.getImageId());
				rowSet.updateString(FILDS_NAME[TRAILER], movie.getTrailer());
				rowSet.updateRow();
				success = true;
			} else {
				success = insertMovie(movie);
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return success;
	}

	/**
	 * Deleting a movie from the database
	 * 
	 * @param movieId
	 * @return True = success
	 */
	public static boolean deleteMovieById(int movieId) {
		boolean success = false;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetObjById(TABLE_NAME, ID_NAME));
			rowSet.setInt(1, movieId);
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
	 * Receiving all Movies That they have a screening from the database
	 * 
	 * @return ArrayList of Movies
	 */
	public static ArrayList<Movie> getAllScreenedMovie() {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GET_ALL_SCREENED_MOVIE);
			rowSet.execute();
			while (rowSet.next()) {
				try {
					movies.add(dbToMovie(rowSet));
				} catch (Exception e) {
					System.out.println(e);
					continue;
				}
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return movies;
	}

	/**
	 * Receiving all Movies according to 'Theater' and 'Date' 
	 * @param theaterId
	 * @param date
	 * @return ArrayList of Movies
	 */
	public static ArrayList<Movie> getMovieByTheaterDate(int theaterId, MyTimestamp date) {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GAT_MOVIE_BY_THEATER_DATE);

			MyTimestamp startDate = MyTimestamp.StringConvert(date.dateToString(), "00:00");
			MyTimestamp endDate = MyTimestamp.StringConvert(date.dateToString(), "00:00");
			endDate.addDay(1);

			rowSet.setInt(1, theaterId);
			rowSet.setTimestamp(2, startDate);
			rowSet.setTimestamp(3, endDate);

			rowSet.execute();
			while (rowSet.next()) {
				try {
					movies.add(dbToMovie(rowSet));
				} catch (Exception e) {
					System.out.println(e);
					continue;
				}
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return movies;
	}

	private static Movie dbToMovie(JdbcRowSet rowSet) throws SQLException, DataFormatException {
		int movieId = rowSet.getInt(FILDS_NAME[MOVIE_ID]);
		String name = rowSet.getString(FILDS_NAME[MOVIE_NAME]);
		int year = rowSet.getInt(FILDS_NAME[YEAR]);
		String genre = rowSet.getString(FILDS_NAME[GENRE]);
		String summary = rowSet.getString(FILDS_NAME[SUMMARY]);
		String director = rowSet.getString(FILDS_NAME[DIRECTOR]);
		int length = rowSet.getInt(FILDS_NAME[LENGTH]);
		int imageId = rowSet.getInt(FILDS_NAME[IMAGE_ID]);
		String trailer = rowSet.getString(FILDS_NAME[TRAILER]);
		return new Movie(movieId, name, year, genre, summary, director, length, imageId, trailer);
	}

}
