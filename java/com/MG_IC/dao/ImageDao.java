package com.MG_IC.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

import javax.sql.rowset.JdbcRowSet;
import com.MG_IC.dao.Queries.QueriesSelect;
import com.MG_IC.model.Image;

public class ImageDao {

//----constants-----------------------------------------------------------------------------//

	// The name of the table in the database
	private static final String TABLE_NAME = "Images";

	// Field names in the database
	private static final int IMAGE_ID = 0, IMAGE_PATH = 1;
	private static final String[] FILDS_NAME = { "Image_Id", "Image_Path" };

	// Name the ID field in the database
	private static final String ID_NAME = FILDS_NAME[IMAGE_ID];

	// Default image saving path
	public static final String DEFALT_IMAGE_NAME = "Default.png";

//----Methods:-----------------------------------------------------------------------------//


	/**
	 * Add a image (Object) to the database
	 * 
	 * @param image
	 * @return image id or -1 if failed 
	 */
	public static int insertImage(Image image) {
		int imageId = -1;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetAllTable(TABLE_NAME));
			rowSet.execute();
			rowSet.moveToInsertRow();
			rowSet.updateString(FILDS_NAME[IMAGE_PATH],image.getImagePath());
			rowSet.insertRow();
			imageId = rowSet.getInt(ID_NAME);
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return imageId;
	}

	/**
	 * Initializes a default image to the database
	 * @return True = success
	 */
	public static boolean insertDefaultiImage() {
		boolean success = false;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetAllTable(TABLE_NAME));
			rowSet.execute();
			rowSet.moveToInsertRow();
			rowSet.updateInt(FILDS_NAME[IMAGE_ID], 0);
			rowSet.updateString(FILDS_NAME[IMAGE_PATH],"\\" + DEFALT_IMAGE_NAME);
			rowSet.insertRow();
			rowSet.close();
			success = true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return success;
	}

	/**
	 * Receiving Image from the database according to Image_ID
	 * 
	 * @param imageId
	 * @return Image or null if failed
	 */
	public static Image getImageById(int imageId) {
		Image image = null;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetObjById(TABLE_NAME, ID_NAME));
			rowSet.setInt(1, imageId);
			rowSet.execute();

			if (rowSet.next()) {
				image = dbToImage(rowSet);
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return image;
	}

	/**
	 * Receiving all Images from the database
	 * 
	 * @return ArrayList of Images
	 */
	public static ArrayList<Image> getAllImage() {
		ArrayList<Image> images = new ArrayList<Image>();
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetAllTable(TABLE_NAME));
			rowSet.execute();

			while (rowSet.next()) {
				try {
					images.add(dbToImage(rowSet));
				} catch (Exception e) {
					System.out.println(e);
					continue;
				}
			}
			rowSet.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return images;
	}

	/**
	 * Image update in the database<br>
	 * <br>
	 * Correct work:<br>
	 * obj = getImageById()<br>
	 * --> changes to the object<br>
	 * --> updateImage (obj)<br>
	 * 
	 * @param image
	 */
	public static boolean updateImage(Image image) {
		boolean success = false;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetObjById(TABLE_NAME, ID_NAME));
			rowSet.setInt(1, image.getImageId());
			rowSet.execute();

			if (rowSet.next()) {
				rowSet.updateInt(ID_NAME, image.getImageId());
				rowSet.updateString(FILDS_NAME[IMAGE_PATH], image.getImagePath());
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
	 * Deleting a Image from the database
	 * 
	 * @param imageId
	 * @return True = success
	 */
	public static boolean deleteImageById(int imageId) {
		boolean success = false;
		try {
			JdbcRowSet rowSet = DBManager.getRowSet(QueriesSelect.GetObjById(TABLE_NAME, ID_NAME));
			rowSet.setInt(1, imageId);
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

	private static Image dbToImage (JdbcRowSet rowSet) throws SQLException, DataFormatException {
		int imageId = rowSet.getInt(FILDS_NAME[IMAGE_ID]);
		String imagePath = rowSet.getString(FILDS_NAME[IMAGE_PATH]);
		return new Image(imageId, imagePath);
	}
}
