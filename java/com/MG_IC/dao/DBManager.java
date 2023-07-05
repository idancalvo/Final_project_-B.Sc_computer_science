package com.MG_IC.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

import com.MG_IC.dao.Queries.QueriesCleanAndCreateTable;

public class DBManager {

	private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/LiveInMovieDB";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "admin";

//----Methods:------------------------------------------------------------------//	

	/**
	 * Delete all values from DB
	 * 
	 * @throws SQLException
	 */
	public static void clearAllDB() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new SQLException();
		}
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		Statement statement = connection.createStatement();
		statement.executeUpdate(QueriesCleanAndCreateTable.QUERYֹ_CLEANֹ_ֹDBֹ);
		statement.close();
		connection.close();
	}

	/**
	 * Create new tables and triggers in DB Create an administrator user and a
	 * default image
	 * 
	 * @return True = success
	 * @throws SQLException
	 */
	public static boolean createTables() throws SQLException {
		boolean success = true;
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new SQLException();
		}

		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		Statement statement = connection.createStatement();
		statement.executeUpdate(QueriesCleanAndCreateTable.CREATE_TABLES);

		try {
			statement.executeUpdate(QueriesCleanAndCreateTable.CREATE_TICKETS_TRIGGER);
		} catch (Exception e) {
			success = false;
		}

		statement.close();
		connection.close();

		success &= ImageDao.insertDefaultiImage();
		success &= UserDao.insertAdmin();

		return success;
	}

	/**
	 * Get a ready-to-use JdbcRowSet object
	 * @param command = Query
	 * @return JdbcRowSet 
	 * @throws SQLException
	 */
	public static JdbcRowSet getRowSet(String command) throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new SQLException();
		}

		JdbcRowSet rowSet = RowSetProvider.newFactory().createJdbcRowSet();
		rowSet.setUrl(DATABASE_URL);
		rowSet.setUsername(USERNAME);
		rowSet.setPassword(PASSWORD);
		rowSet.setCommand(command);
		return rowSet;
	}

}
