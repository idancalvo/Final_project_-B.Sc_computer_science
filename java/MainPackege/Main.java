package MainPackege;

import com.MG_IC.dao.DBManager;

public class Main {

	public static void main(String[] args) {
		try {
			
			DBManager.clearAllDB();
			DBManager.createTables();
			ExampleValues.start();	
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
