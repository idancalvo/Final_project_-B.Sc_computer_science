package com.MG_IC.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

import javax.sql.rowset.JdbcRowSet;
import com.MG_IC.dao.Queries.QueriesSelect;
import com.MG_IC.model.Message;

public class MessageDao {

	// ----constants-----------------------------------------------------------------------------//

	// The name of the table in the database
	private static final String TABLE_NAME = "Messages";

	// Field names in the database
	private static final int MESSAGE_ID = 0, USER_ID = 1, MESSAGE = 2;
	private static final String[] FILDS_NAME = { "Message_Id", "User_Id", "Message" };

	// Name the ID field in the database
	private static final String ID_NAME = FILDS_NAME[MESSAGE_ID];

	// ----Methods:-----------------------------------------------------------------------------//

	/**
	 * Add a message (Object) to the database
	 * 
	 * @param message
	 * @return True = success
	 */
	public static boolean insertMessage(Message message) {
		boolean success = false;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetAllTable(TABLE_NAME));
			rowSet.execute();
			rowSet.moveToInsertRow();
			rowSet.updateInt(FILDS_NAME[USER_ID], message.getUserId());
			rowSet.updateString(FILDS_NAME[MESSAGE], message.getMessage());

			rowSet.insertRow();
			success = true;
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
			success = false;
		}
		return success;
	}

	/**
	 * Receiving all message from the database
	 * 
	 * @return ArrayList of message
	 */
	public static ArrayList<Message> getAllMessage() {
		ArrayList<Message> messages = new ArrayList<Message>();
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetAllTable(TABLE_NAME));
			rowSet.execute();

			while (rowSet.next()) {
				try {
					messages.add(dbToMessage(rowSet));
				} catch (Exception e) {
					System.out.println(e);
					continue;
				}
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return messages;
	}

	/**
	 * Deleting a message from the database
	 * 
	 * @param messageId
	 * @return True = success
	 */
	public static boolean deleteMessageById(int messageId) {
		boolean success = false;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetObjById(TABLE_NAME, ID_NAME));
			rowSet.setInt(1, messageId);
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

	private static Message dbToMessage(JdbcRowSet rowSet) throws SQLException, DataFormatException {
		int messageId = rowSet.getInt(FILDS_NAME[MESSAGE_ID]);
		int userId = rowSet.getInt(FILDS_NAME[USER_ID]);
		String message = rowSet.getString(FILDS_NAME[MESSAGE]);
		return new Message(messageId, userId, message);
	}
}
