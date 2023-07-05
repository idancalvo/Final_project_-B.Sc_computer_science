package com.MG_IC.dao.Queries;

public final class QueriesInsert {

	public static final String GET_FREE_HALL_NUM = "" 
			+ " WITH TempHalls(Hall_Num) AS (							" + "\n"
			+ "		SELECT												" + "\n"
			+ "			Halls.Hall_Num									" + "\n"
			+ "		FROM 									 			" + "\n"
			+ "			Theaters NATURAL JOIN Halls						" + "\n"
			+ " 	WHERE 												" + "\n"
			+ "			Theaters.theater_id = ?							" + "\n"
			+ "		)													" + "\n"

			+ "	SELECT													" + "\n"
			+ "		MIN(next_Num)										" + "\n"
			+ "	FROM (									 				" + "\n"
			+ "			SELECT											" + "\n"
			+ " 			COUNT(*) as next_Num						" + "\n"
			+ "			FROM 								 			" + "\n"
			+ "				TempHalls as a	join TempHalls as b			" + "\n"
			+ "				on a.Hall_Num >= b.Hall_Num					" + "\n"
			+ "			GROUP BY 					 					" + "\n"
			+ "				a.Hall_Num 									" + "\n"
			+ "			having 								 			" + "\n"
			+ "				a.Hall_Num > count(*) 						" + "\n"
			+ "			 								 				" + "\n"
			+ "			union 								 			" + "\n"
			+ "				 								 			" + "\n"
			+ "			SELECT											" + "\n"
			+ " 			max(Hall_Num)+1 							" + "\n"
			+ "			FROM 								 			" + "\n"
			+ "				TempHalls									" + "\n"
			+ "		)ids												" + "\n"
			+ "	;														";

	public static final String GET_DOUBLE_SCREENING = "" 
			+ " WITH TempIds (Theater_Id) AS (							" + "\n" 
			+ "		SELECT												" + "\n"
			+ "			Halls.Theater_Id								" + "\n"
			+ "		FROM 									 			" + "\n"
			+ "			Halls											" + "\n"
			+ " 	WHERE 												" + "\n"
			+ "			Halls.Hall_Id = ?		 						" + "\n"
			+ "		)													" + "\n"

			+ "	SELECT													" + "\n"
			+ "		DISTINCT Screenings.Screening_id					" + "\n"

			+ "	FROM 									 				" + "\n"
			+ " 	(TempIds NATURAL JOIN Halls)						" + "\n"
			+ " 	NATURAL JOIN Screenings								" + "\n"

			+ " WHERE 													" + "\n"
			+ "		Screenings.Movie_Id = ?								" + "\n"
			+ "		AND													" + "\n"
			+ "		Screenings.Date_And_TIME = ?						" + "\n"
			+ "	;														";

	// --------------------------------------------------------------------------//

}
