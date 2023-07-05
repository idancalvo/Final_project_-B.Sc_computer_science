package com.MG_IC.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

import javax.sql.rowset.JdbcRowSet;

import com.MG_IC.dao.Queries.QueriesSelect;
import com.MG_IC.model.User;

public class UserDao {

//----constants-----------------------------------------------------------------------------//

	// The name of the table in the database
	public static final String TABLE_NAME = "Users";

	// Field names in the database
	public static final int USER_ID = 0, FIRST_NAME = 1, LAST_NAME = 2, EMAIL = 3, PASSWORD = 4, PHONE = 5,
			IMAGE_ID = 6, ADMIN = 7;
	public static final String[] FILDS_NAME = { "User_Id", "First_Name", "Last_Name", "Email", "Password", "Phone",
			"Image_Id", "Admin" };

	// Name the ID field in the database
	private static final String ID_NAME = FILDS_NAME[USER_ID];

//----Methods:-----------------------------------------------------------------------------//	

	/**
	 * Add a Users (Objects) to the database
	 * 
	 * @param users
	 * @return Number of Users added
	 */
	public static int insertUsers(User[] users) {
		int count = 0;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetAllTable(TABLE_NAME));
			rowSet.execute();
			rowSet.moveToInsertRow();
			for (int i = 0; i < users.length; i++) {
				try {
					rowSet.updateString(FILDS_NAME[FIRST_NAME], users[i].getFirstName());
					rowSet.updateString(FILDS_NAME[LAST_NAME], users[i].getLastName());
					rowSet.updateString(FILDS_NAME[EMAIL], users[i].getEmail());
					rowSet.updateString(FILDS_NAME[PASSWORD], users[i].getPassword());
					rowSet.updateString(FILDS_NAME[PHONE], users[i].getPhone());
					rowSet.updateInt(FILDS_NAME[IMAGE_ID], users[i].getImageId());
					rowSet.updateBoolean(FILDS_NAME[ADMIN], users[i].isAdmin());
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
	 * Add a User (Object) to the database
	 * 
	 * @param user
	 * @return True = success
	 */
	public static boolean insertUser(User user) {
		User[] users = { user };
		return (insertUsers(users) > 0);
	}

	/**
	 * Initializes a default Admin to the database
	 * 
	 * @return True = success
	 */
	public static boolean insertAdmin() {
		boolean success = false;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetAllTable(TABLE_NAME));
			rowSet.execute();
			rowSet.moveToInsertRow();
			rowSet.updateInt(FILDS_NAME[USER_ID], 0);
			rowSet.updateString(FILDS_NAME[FIRST_NAME], "Admin");
			rowSet.updateString(FILDS_NAME[LAST_NAME], "Admin");
			rowSet.updateString(FILDS_NAME[EMAIL], "Admin@Admin.com");
			rowSet.updateString(FILDS_NAME[PASSWORD], "Admin");
			rowSet.updateString(FILDS_NAME[PHONE], "0000000000");
			rowSet.updateInt(FILDS_NAME[IMAGE_ID], 0);
			rowSet.updateBoolean(FILDS_NAME[ADMIN], true);
			rowSet.insertRow();
			rowSet.close();
			success = true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return success;
	}

	/**
	 * Receiving User from the database according to User_ID
	 * 
	 * @param userId
	 * @return User or null if failed
	 */
	public static User getUserById(int userId) {
		User user = null;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetObjById(TABLE_NAME, ID_NAME));
			rowSet.setInt(1, userId);
			rowSet.execute();
			if (rowSet.next()) {
				user = dbToUser(rowSet);
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return user;
	}

	/**
	 * Receiving all Users from the database
	 * 
	 * @return ArrayList of Users
	 */
	public static ArrayList<User> getAllUser() {
		ArrayList<User> users = new ArrayList<User>();
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetAllTable(TABLE_NAME));
			rowSet.execute();
			while (rowSet.next()) {
				try {
					users.add(dbToUser(rowSet));
				} catch (Exception e) {
					System.out.println(e);
					continue;
				}
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return users;
	}

	private static User dbToUser(JdbcRowSet rowSet) throws SQLException, DataFormatException {
		int userId = rowSet.getInt(FILDS_NAME[USER_ID]);
		String firstName = rowSet.getString(FILDS_NAME[FIRST_NAME]);
		String lastName = rowSet.getString(FILDS_NAME[LAST_NAME]);
		String email = rowSet.getString(FILDS_NAME[EMAIL]);
		String password = "";
		String phone = rowSet.getString(FILDS_NAME[PHONE]);
		int imageId = rowSet.getInt(FILDS_NAME[IMAGE_ID]);
		boolean admin = rowSet.getBoolean(FILDS_NAME[ADMIN]);
		return new User(userId, firstName, lastName, email, password, phone, imageId, admin);
	}

	/**
	 * User update in the database<br>
	 * <br>
	 * Correct work:<br>
	 * obj = getUserById()<br>
	 * --> changes to the object<br>
	 * --> updateUser(obj)<br>
	 * *
	 * 
	 * @param user
	 * @return True = success
	 */
	public static boolean updateUser(User user) {
		boolean success = false;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetObjById(TABLE_NAME, ID_NAME));
			rowSet.setInt(1, user.getUserId());
			rowSet.execute();

			if (rowSet.next()) {
				rowSet.updateInt(ID_NAME, user.getUserId());
				rowSet.updateString(FILDS_NAME[FIRST_NAME], user.getFirstName());
				rowSet.updateString(FILDS_NAME[LAST_NAME], user.getLastName());
				rowSet.updateString(FILDS_NAME[EMAIL], user.getEmail());
				rowSet.updateString(FILDS_NAME[PHONE], user.getPhone());
				rowSet.updateInt(FILDS_NAME[IMAGE_ID], user.getImageId());
				rowSet.updateRow();
				success = true;
			} else {
				success = insertUser(user);
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return success;
	}

	/**
	 * Deleting a User from the database
	 * 
	 * @param userId
	 * @return True = success
	 */
	public static boolean deleteUserById(int userId) {
		boolean success = false;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetObjById(TABLE_NAME, ID_NAME));
			rowSet.setInt(1, userId);
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
	 * Checks user name and password
	 * 
	 * @param email
	 * @param password
	 * @return Returns userId if success or -1 if failed
	 */
	public static int PasswordCheck(String email, String password) {
		int userId = -1;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.PASSWORDֹֹ_ֹCHECKER);
			rowSet.setString(1, email);
			rowSet.setString(2, password);
			rowSet.execute();
			if (rowSet.next()) {
				userId = rowSet.getInt("User_Id");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return userId;
	}

	/**
	 * Changes the user to an administrator
	 * 
	 * @param userId
	 * @return True = success
	 */
	public static boolean changeToAdmin(int userId) {
		boolean success = false;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetObjById(TABLE_NAME, ID_NAME));
			rowSet.setInt(1, userId);
			rowSet.execute();

			if (rowSet.next()) {
				rowSet.updateInt(ID_NAME, userId);
				rowSet.updateBoolean(FILDS_NAME[ADMIN], true);
				rowSet.updateRow();
				success = true;
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return success;
	}

	/**
	 * Checks if a user is an administrator
	 * 
	 * @param userId
	 * @return True = the user is an administrator
	 */
	public static boolean isAdmin(int userId) {
		boolean ans = false;
		User user = getUserById(userId);
		if (user != null) {
			ans = user.isAdmin();
		}
		return ans;
	}

	/**
	 * Updating the user's password
	 * 
	 * @param userId
	 * @param oldPassword
	 * @param newPassword1
	 * @return True = success
	 */
	public static boolean updatePassword(int userId, String oldPassword, String newPassword1) {
		boolean success = false;
		try {

			User user = getUserById(userId);
			int userId2 = PasswordCheck(user.getEmail(), oldPassword);
			if (userId == userId2) {
				user.setPassword(newPassword1);

				JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetObjById(TABLE_NAME, ID_NAME));
				rowSet.setInt(1, userId);
				rowSet.execute();

				if (rowSet.next()) {
					rowSet.updateString(FILDS_NAME[PASSWORD], newPassword1);
					rowSet.updateRow();
					success = true;
				}
				rowSet.close();
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return success;
	}

}
