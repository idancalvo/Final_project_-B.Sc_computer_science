package com.MG_IC.dao.Queries;

public final class QueriesCleanAndCreateTable {

	private static final String CREATE_TABLE_THEATERS = ""
			+ "CREATE TABLE IF NOT EXISTS Theaters(								" + "\n"
			+ "Theater_Id      	SERIAL	 		NOT NULL,						" + "\n"
			+ "Theater_Name 	Varchar(50) 	NOT NULL	UNIQUE,				" + "\n"
			+ "City 			Varchar(30) 	NOT NULL,						" + "\n"
			+ "Street 			Varchar(40),									" + "\n"
			+ "House_Number 	Integer,										" + "\n"

			+ "PRIMARY KEY(Theater_Id),											" + "\n"
			
			+ "CONSTRAINT House_Number_ch 										" + "\n"
			+ " 	check (House_Number>=0 and House_Number<=999)				" + "\n"
			+ ");																";

	private static final String CREATE_TABLE_HALLS = ""
			+ "CREATE TABLE IF NOT EXISTS  Halls(								" + "\n"
			+ "Hall_Id      	SERIAL	 		NOT NULL 	UNIQUE,				" + "\n"
			+ "Theater_Id 		Integer 		NOT NULL,						" + "\n"
			+ "Hall_Num			Integer	 		NOT NULL,						" + "\n"

			+ "PRIMARY KEY(Theater_Id, Hall_Num),								" + "\n"

			+ "CONSTRAINT FK_Theater_Id FOREIGN KEY(Theater_Id)					" + "\n"
			+ "REFERENCES Theaters(Theater_Id)									" + "\n"
			+ "ON DELETE CASCADE ON UPDATE CASCADE,								" + "\n"

			+ "CONSTRAINT Hall_Num_ch CHECK(Hall_Num>0)							" + "\n"
			+ ");																";

	private static final String CREATE_TABLE_SEATS = ""
			+ "CREATE TABLE IF NOT EXISTS Seats(								" + "\n"
			+ "Seat_Id         	SERIAL	 		NOT NULL 	UNIQUE,				" + "\n"
			+ "Hall_Id 			Integer 		NOT NULL,						" + "\n"
			+ "Seat_Row			Integer 		NOT NULL,						" + "\n"
			+ "Seat_Num        	Integer 		NOT NULL,						" + "\n"

			+ "PRIMARY KEY	(Hall_Id, Seat_Row, Seat_Num),						" + "\n"

			+ "CONSTRAINT FK_Hall_Id FOREIGN KEY(Hall_Id)						" + "\n"
			+ "	REFERENCES Halls(Hall_Id)										" + "\n"
			+ "	ON DELETE CASCADE ON UPDATE CASCADE,							" + "\n"

			+ "CONSTRAINT Seat_Row_ch check (Seat_Row>0 and Seat_Row<=50),		" + "\n"
			+ "CONSTRAINT Seat_Num_ch check (Seat_Num>0 and Seat_Num<=50)		" + "\n"
			+ ");																";

	private static final String CREATE_TABLE_IMAGES = ""
			+ "CREATE TABLE IF NOT EXISTS Images(								" + "\n"
			+ "Image_Id			SERIAL	 		NOT NULL	UNIQUE,				" + "\n"
			+ "Image_Path		varchar(500) 	NOT NULL	UNIQUE,				" + "\n"

			+ "PRIMARY KEY(Image_Path)											" + "\n"
			+ ");																";

	private static final String CREATE_TABLE_MOVIES = ""
			+ "CREATE TABLE IF NOT EXISTS Movies(								" + "\n"
			+ "Movie_Id			SERIAL	 		NOT NULL	UNIQUE,				" + "\n"
			+ "Movie_Name		Varchar(50) 	NOT NULL,						" + "\n"
			+ "Year				Integer			NOT NULL,						" + "\n"
			+ "Genre           	Varchar(30),									" + "\n"
			+ "Summary          Varchar(500),									" + "\n"
			+ "Director       	Varchar(50),									" + "\n"
			+ "Length          	Integer 		NOT NULL,						" + "\n"
			+ "Image_Id        	Integer 		DEFAULT 0,						" + "\n"
			+ "Trailer        	varchar(500),									" + "\n"

			+ "PRIMARY KEY(Movie_Name,Year),									" + "\n"

			+ "CONSTRAINT FK_Image_Id FOREIGN KEY(Image_Id) 					" + "\n"
			+ "	REFERENCES Images(Image_Id)										" + "\n"
			+ " ON UPDATE CASCADE ON DELETE SET DEFAULT,						" + "\n"

			+ "CONSTRAINT Year_ch check (Year>=0 and Year<=5000),				" + "\n"
			+ "CONSTRAINT Length_ch check (Length>=0 and Length<500)			" + "\n"
			+ ");																";

	private static final String CREATE_TABLE_USERS = ""
			+ "CREATE TABLE IF NOT EXISTS Users(								" + "\n"
			+ "User_Id     		SERIAL	 		NOT NULL,						" + "\n"
			+ "First_Name		Varchar(30),									" + "\n"
			+ "Last_Name		Varchar(30),									" + "\n"
			+ "Email          	Varchar(100) 	NOT NULL 	UNIQUE,				" + "\n"
			+ "Password        	Varchar(50) 	NOT NULL,						" + "\n"
			+ "Phone           	Varchar(10) 	NOT NULL 	UNIQUE, 			" + "\n"
			+ "Image_Id        	Integer			DEFAULT 0,						" + "\n"
			+ "Admin        	Boolean 		NOT NULL,						" + "\n"

			+ "PRIMARY KEY(User_Id),											" + "\n"

			+ "CONSTRAINT FK_Image_Id FOREIGN KEY(Image_Id) 					" + "\n"
			+ "	REFERENCES Images(Image_Id)										" + "\n"
			+ "    ON UPDATE CASCADE ON DELETE SET DEFAULT,						" + "\n"

			+ "CONSTRAINT Email_ch check (Email LIKE '%_@_%._%')				" + "\n"
			+ ");																";

	private static final String CREATE_TABLE_SCREENINGS = ""
			+ "CREATE TABLE IF NOT EXISTS  Screenings(							" + "\n"
			+ "Screening_Id    	SERIAL	 		NOT NULL 	UNIQUE,				" + "\n"
			+ "Movie_Id 		Integer 		NOT NULL,						" + "\n"
			+ "Hall_Id		    Integer 		NOT NULL,						" + "\n"
			+ "Date_And_TIME   	Timestamp		NOT NULL,						" + "\n"
			+ "Open_For_Sale   	Boolean  		NOT NULL,						" + "\n"

			+ "PRIMARY KEY(Hall_Id,Date_And_TIME),								" + "\n"

			+ "CONSTRAINT FK_Movie_Id FOREIGN KEY(Movie_Id) 					" + "\n"
			+ "	REFERENCES Movies(Movie_Id)										" + "\n"
			+ "	ON DELETE CASCADE ON UPDATE CASCADE,							" + "\n"

			+ "CONSTRAINT FK_Hall_Id FOREIGN KEY(Hall_Id) 						" + "\n"
			+ "	REFERENCES Halls(Hall_Id)										" + "\n"
			+ "	ON DELETE CASCADE ON UPDATE CASCADE								" + "\n"

			+ ");																";

	private static final String CREATE_TABLE_TICKETS = ""
			+ "CREATE TABLE IF NOT EXISTS Tickets(								" + "\n"
			+ "Ticket_Id    	SERIAL	 		NOT NULL 	UNIQUE,				" + "\n"
			+ "Screening_Id    	Integer 		NOT NULL,						" + "\n"
			+ "Seat_Id 			Integer 		NOT NULL,						" + "\n"
			+ "Status		    Integer 		NOT NULL,						" + "\n"
			+ "User_Id         	Integer			DEFAULT 0,						" + "\n"

			+ "PRIMARY KEY(Screening_Id, Seat_Id),								" + "\n"

			+ "CONSTRAINT status_ch check (status<4 and status>=0), 			" + "\n"
			+ "/*0 AVAILABLE,1 SOLD,2 SAVE,3 CANCELED*/							" + "\n"

			+ "CONSTRAINT FK_Screening_Id FOREIGN KEY(Screening_Id) 			" + "\n"
			+ "	REFERENCES Screenings(Screening_Id)								" + "\n"
			+ "	ON DELETE CASCADE ON UPDATE CASCADE,							" + "\n"

			+ "CONSTRAINT FK_Seat_Id FOREIGN KEY(Seat_Id) 						" + "\n"
			+ "	REFERENCES Seats(Seat_Id)										" + "\n"
			+ "	ON DELETE CASCADE ON UPDATE CASCADE,							" + "\n"

			+ "CONSTRAINT FK_User_Id FOREIGN KEY(User_Id) 						" + "\n"
			+ "	REFERENCES Users(User_Id)										" + "\n"
			+ "	ON UPDATE CASCADE ON DELETE SET DEFAULT							" + "\n"
			+ ");																";

	private static final String CREATE_TABLE_MESSAGES = ""
			+ "CREATE TABLE IF NOT EXISTS Messages(								" + "\n"
			+ "Message_Id		SERIAL	 		NOT NULL	UNIQUE,				" + "\n"
			+ "User_Id         	Integer			NOT NULL,						" + "\n"
			+ "Message          Varchar(1000),									" + "\n"

			+ "PRIMARY KEY(Message_Id),											" + "\n"
			
			+ "CONSTRAINT FK_User_Id FOREIGN KEY(User_Id) 						" + "\n"
			+ "	REFERENCES Users(User_Id)										" + "\n"
			+ "	ON DELETE CASCADE ON UPDATE CASCADE								" + "\n"
			+ ");																";	

	
	
	public static final String CREATE_TICKETS_TRIGGER = ""
			+ "CREATE FUNCTION CreateTickets() 											" + "\n"
			+ " 	RETURNS trigger AS $emp_stamp$										" + "\n"

			+ " 	DECLARE																" + "\n"
			+ " 		r1   RECORD ;													" + "\n"
			+ " 																		" + "\n"
			+ " 	BEGIN																" + "\n"
			+ " 		FOR r1 IN (														" + "\n"
			+ " 			SELECT Seats.Seat_Id										" + "\n"
			+ " 			FROM Seats													" + "\n"
			+ " 			WHERE Seats.Hall_Id = NEW.Hall_Id)							" + "\n"
			+ " 		LOOP															" + "\n"
			
			+ " 			INSERT INTO Tickets	(Screening_Id,Seat_Id,Status,User_Id)	" + "\n"
			+ " 				 VALUES	(NEW.Screening_Id,r1.Seat_Id,0,0);				" + "\n"
			
			+ " 		END LOOP;														" + "\n"
			
			+ " 		RETURN NEW;														" + "\n"
			+ " 	 END;																" + "\n"
			
			+ " 	$emp_stamp$ LANGUAGE plpgsql;										" + "\n"
			
			+ " 	CREATE TRIGGER emp_stamp 											" + "\n"
			+ " 		AFTER INSERT ON Screenings										" + "\n"
			+ " 		FOR EACH ROW EXECUTE FUNCTION CreateTickets();					" + "\n"
			;
	
	public static final String CHECK_TICKET_TRIGGER = ""
			+ "CREATE FUNCTION CheckTicket()											" + "\n"
			+ " 	RETURNS trigger AS $ins_Tickets$									" + "\n"
			
			+ " 	DECLARE																" + "\n"
			+ " 		x integer;														" + "\n"
			+ " 		y integer;														" + "\n"
			
			+ " 	BEGIN																" + "\n"
			
			+ " 		SELECT Screenings.Hall_Id									" + "\n"
			+ " 		INTO x															" + "\n"
			+ " 		FROM Screenings													" + "\n"
			+ " 		WHERE Screenings.Screening_Id = NEW.Screening_Id;				" + "\n"

			+ " 		SELECT Seats.Hall_Id										" + "\n"
			+ " 		INTO y															" + "\n"
			+ " 		FROM Seats														" + "\n"
			+ " 		WHERE Seats.Seat_Id = NEW.Screening_Id;							" + "\n"
			
			+ " 		IF x=y THEN														" + "\n"
			+ " 			RETURN NEW;													" + "\n"
			+ " 		END IF;															" + "\n"
			
			+ " 		RETURN ERROR;													" + "\n"
			
			+ " 	END;																" + "\n"
			
			+ " 	$ins_Tickets$ LANGUAGE plpgsql;										" + "\n"
			
			+ " 	CREATE TRIGGER ins_Ticket  											" + "\n"
			+ " 		BEFORE INSERT ON Tickets										" + "\n"
			+ " 		FOR EACH ROW EXECUTE FUNCTION CheckTicket();					" + "\n"
			;
			
	
	public static final String CREATE_TABLES = "" + CREATE_TABLE_THEATERS + CREATE_TABLE_HALLS + CREATE_TABLE_SEATS
			+ CREATE_TABLE_IMAGES + CREATE_TABLE_MOVIES + CREATE_TABLE_USERS + CREATE_TABLE_SCREENINGS
			+ CREATE_TABLE_TICKETS + CREATE_TABLE_MESSAGES;

	public static final String QUERYֹ_CLEANֹ_ֹDBֹ = "DROP SCHEMA public CASCADE; CREATE SCHEMA public;";
}
