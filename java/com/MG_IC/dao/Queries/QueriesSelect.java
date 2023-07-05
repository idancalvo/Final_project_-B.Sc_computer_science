package com.MG_IC.dao.Queries;

import java.util.ArrayList;

import com.MG_IC.model.Seat;

public class QueriesSelect {

	public static String GetAllTable(String tableName) {
		String query = "" 
				+ "		SELECT												" + "\n"
				+ "				*											" + "\n"
				+ "		FROM 									 			" + "\n" 
				+ "			" + tableName + "								" + "\n"
				+ "	;														";
		return query;
	}

	public static String GetObjById(String tableName, String idName) {
		String query = "" 
				+ "		SELECT												" + "\n"
				+ "				*											" + "\n"
				+ "		FROM 									 			" + "\n" 
				+ "			" + tableName + "								" + "\n" 
				+ " 	WHERE 												" + "\n" 
				+ "			" + idName + " = ?		 						" + "\n"
				+ "	;														";
		return query;
	}
	
	
	

	public static final String GAT_MOVIE_BY_THEATER_DATE = ""
			+ "		SELECT												" + "\n"
			+ "				DISTINCT									" + "\n"
			+ "				Movies.movie_id, Movies.movie_name, 		" + "\n"
			+ "				Movies.year,Movies.genre,  					" + "\n"
			+ "				Movies.summary, Movies.director,			" + "\n"
			+ "				Movies.length, Movies.image_id, 			" + "\n"
			+ "				Movies.trailer 								" + "\n"
			
			+ "		FROM 									 			" + "\n"
			+ "			(Movies NATURAL JOIN Screenings) 	 			" + "\n"
			+ "			 NATURAL JOIN Halls								" + "\n"

			+ " 	WHERE 												" + "\n"
			+ "			Halls.theater_id = ?				 		 	" + "\n"
			+ "			AND									 		 	" + "\n"
			+ "			Screenings.Date_And_TIME BETWEEN ? AND ?	 	" + "\n"
			+ "	;														";
	
	
	public static String GetScreeningDisplayById(String idName) {
		String query = "" 
				+ "		SELECT												" + "\n"
				+ "				DISTINCT									" + "\n"
				+ "				Theaters.Theater_Name,						" + "\n"
				+ "				Screenings.Screening_Id,					" + "\n"
				+ "				Halls.Hall_Num,								" + "\n"
				+ "				Movies.Movie_Name,							" + "\n"
				+ "				Screenings.Date_And_TIME,					" + "\n"
				+ "				Screenings.Open_For_Sale					" + "\n"

				+ "		FROM 									 			" + "\n"
				+ "			((Movies NATURAL JOIN Screenings) 	 			" + "\n"
				+ "			 NATURAL JOIN Halls)							" + "\n"
				+ "			 NATURAL JOIN Theaters							" + "\n"

				+ " 	WHERE 												" + "\n"
				+ "			" + idName + " = ?				 		 		" + "\n"
				+ "	;														";
				return query;
				}
	
	
	public static final String GAT_SCREENING_DISPLAY_BY_THEATER_DATE_MOVIE = ""
			+ "		SELECT												" + "\n"
			+ "				DISTINCT									" + "\n"
			+ "				Theaters.Theater_Name,						" + "\n"
			+ "				Screenings.Screening_Id,					" + "\n"
			+ "				Halls.Hall_Num,								" + "\n"
			+ "				Movies.Movie_Name,							" + "\n"
			+ "				Screenings.Date_And_TIME,					" + "\n"
			+ "				Screenings.Open_For_Sale					" + "\n"
			
			+ "		FROM 									 			" + "\n"
			+ "			((Movies NATURAL JOIN Screenings) 	 			" + "\n"
			+ "			 NATURAL JOIN Halls)							" + "\n"
			+ "			 NATURAL JOIN Theaters							" + "\n"

			+ " 	WHERE 												" + "\n"
			+ "			Movies.movie_id	= ? 							" + "\n"
			+ "			AND	 											" + "\n"
			+ "			Halls.theater_id = ?				 		 	" + "\n"
			+ "			AND									 		 	" + "\n"
			+ "			Screenings.Date_And_TIME BETWEEN ? AND ?	 	" + "\n"
			+ "	;														";
	

	public static String getSeatInHall(String groupBy) {
		String query = "" 
				+ "		SELECT												" + "\n"
				+ "			COUNT (*)										" + "\n"
				+ "		FROM 									 			" + "\n"
				+ "			Seats											" + "\n"
				+ " 	WHERE 												" + "\n"
				+ "			Hall_id = ?				 		 				" + "\n"
				+ " 	group by											" + "\n" 
				+ "			" + groupBy + "					 		 		" + "\n"
				+ "	;														";
		return query;
	}
	
	
	public static final String PASSWORDֹֹ_ֹCHECKER =""
			+ "	SELECT													"	+"\n"
			+ "		Users.User_Id										"	+"\n"
			+ "	FROM 											 		"	+"\n"
			+ "		Users												"	+"\n"
			+ "	WHERE													"	+"\n"
			+ "		Users.Email = ?										"	+"\n"
			+ "		AND													"	+"\n"
			+ "		Users.Password = ?									"	+"\n"
			+ "	;														";
	

	public static String  getTicketsDisplay (String idName) {
		String query = "" 
			+ "	SELECT													"	+"\n"
			+ "		Tickets.Ticket_Id, 		Tickets.Status,				"	+"\n"
			+ "		Theaters.Theater_Name,								"	+"\n"
			+ "		Theaters.City, 			Theaters.Street,			"	+"\n"
			+ "		Theaters.House_Number, 	Halls.Hall_Num,				"	+"\n"
			+ "		Seats.Seat_Row, 		Seats.Seat_Num,				"	+"\n"
			+ "		Movies.Movie_Name,		Movies.Length,				"	+"\n"
			+ "		Screenings.Date_And_TIME,							"	+"\n"
			+ "		Users.User_Id,										"	+"\n"
			+ "		Users.First_Name, 		Users.Last_Name				"	+"\n"

			+ "	FROM 													"	+"\n"
			+ "	 	 Users,												"	+"\n"
			+ "	 	(Theaters NATURAL JOIN Halls),						"	+"\n"
			+ "	 	(((Screenings 										"	+"\n"
			+ "	 		NATURAL JOIN Tickets)							"	+"\n"
			+ "	 			NATURAL JOIN Seats)							"	+"\n"
			+ "	 				NATURAL JOIN Movies)					"	+"\n"

				
			+ "	WHERE 													"	+"\n"
			+ "		Users.user_id = Tickets.user_id						"	+"\n"
			+ "		AND													"	+"\n"
			+ "		Halls.Hall_Id = Seats.Hall_Id						"	+"\n"
			+ "		AND													"	+"\n"
			+ "		"+idName+" = ?										"	+"\n"
			+ "	;														";
		return query;
	}
	
	
	public static final String IS_HALL_AVAILABLE =""
			+ "	WITH TAB1																		"	+"\n"
			+ "		(screening_id,hall_id,Movie_length,Start_Time,End_Time) AS (				"	+"\n"
			+ "			SELECT																	"	+"\n"
			+ "				Screenings.screening_id,Screenings.hall_id,							"	+"\n"
			+ "				Movies.length,														"	+"\n"
			+ "				Screenings.date_and_time as Start_Time,								"	+"\n"
			+ "				Screenings.date_and_time + 											"	+"\n"
			+ "					((Movies.length + ?) * interval '1 minute') as End_Time			"	+"\n"
			+ "			FROM																	"	+"\n"
			+ "				Movies NATURAL JOIN Screenings										"	+"\n"
			+ "			WHERE																	"	+"\n"
			+ "				Screenings.hall_id = ?												"	+"\n"
			+ "		)																			"	+"\n"
			
			+ "		SELECT																		"	+"\n"
			+ "				*																	"	+"\n"
			+ "		FROM																		"	+"\n"
			+ "				TAB1																"	+"\n"
			+ "		WHERE																		"	+"\n"
			+ "			( ?  BETWEEN Start_Time  AND  End_Time)									"	+"\n"
			+ "		OR																			"	+"\n"
			+ "			( ? BETWEEN Start_Time  AND  End_Time)									"	+"\n"
			+ "		OR																			"	+"\n"
			+ "			( ? < Start_Time  AND  ? > End_Time)									"	+"\n"
			;
	
	
	
	public static final String GET_ALL_SCREENED_MOVIE =""
				+ "		SELECT												" + "\n"
				+ "			DISTINCT										" + "\n"
				+ "			movie_id, movie_name, year, genre,				" + "\n"
				+ "			summary, director, length, Image_Id,			" + "\n"
				+ "			Trailer											" + "\n"
				
				+ "		FROM 									 			" + "\n" 
				+ "			movies NATURAL join screenings					" + "\n"
				
				+ "		WHERE 									 			" + "\n" 
				+ "			screenings.date_and_time >= now();				" + "\n"
				+ "	;														";


	public static String  getTicketsBySeatsAndScreeningId (ArrayList <Seat> seats) {
		String query1 = "" 
			+ "	SELECT													"	+"\n"
			+ "		tickets.ticket_id, tickets.screening_id, 			"	+"\n"
			+ "		tickets.seat_id, tickets.status, 					"	+"\n"
			+ "		tickets.user_id										"	+"\n"
			
			+ "	FROM 													"	+"\n"
			+ "	 	 tickets  NATURAL JOIN Seats						"	+"\n"
	
			+ "	WHERE 													"	+"\n"
			+ "		tickets.screening_id = ?							"	+"\n"
			+ "		AND	(												"	+"\n"	
			;

		for (int i = 0; i < seats.size(); i++) {
			if (i!=0) {
				query1 += " OR ";
			}
			query1 += "(Seats.seat_row =" +seats.get(i).getSeatRow()+ " AND Seats.seat_num =" +seats.get(i).getSeatNum()+ ")";
		}
		return query1 + ")";
	}
	

}
