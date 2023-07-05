package MainPackege;


import java.util.ArrayList;
import java.util.Random;
import java.util.zip.DataFormatException;
import com.MG_IC.dao.*;
import com.MG_IC.model.*;

public class ExampleValues {

	private final static int MIN_NUM_OF_HALL = 3;
	private final static int MAX_NUM_OF_HALL = 5;

	private final static int MIN_SEATS_IN_ROW = 5;
	private final static int MAX_SEATS_IN_ROW = 8;
	private final static int MIN_ROWS = 5;
	private final static int MAX_ROWS = 8;

	private final static int MIN_HOUR = 10;
	private final static int MAX_HOUR = 16;

	private final static int MIN_DAY = 10;
	private final static int MAX_DAY = 15;
	private final static int MONTH = 11;
	private final static int YEAR = 2021;

	public static void start() {
		try {
			newTheaters();
			newHalls();
			newUsers();
			newmovie();
			newScreening();
		} catch (Exception e) {
			System.out.println(e);
		}


	}

	private static int getRand(int minRows, int maxSeatsInRow) {
		Random rand = new Random();
		if (minRows < 0) {
			minRows = 0;
		}
		if (maxSeatsInRow < 0) {
			maxSeatsInRow = 0;
		}
		if (maxSeatsInRow <= minRows) {
			maxSeatsInRow = minRows + 1;
		}
		return minRows + rand.nextInt(maxSeatsInRow - minRows);
	}

	private static void newTheaters() throws DataFormatException {
		Theater[] theaters = new Theater[4];
		theaters[0] = new Theater("LiveInMovie Rishon-Lezion", "Rishon-Lezion", "Hamea Veesrim Street", 25);
		theaters[1] = new Theater("LiveInMovie Jerusalem", "Jerusalem", "Hanashi Hashishi blvd", 57);
		theaters[2] = new Theater("LiveInMovie Eilat", "Eilat", "Hatmarim Avenue", 32);
		theaters[3] = new Theater("LiveInMovie Beer-Sheva", "Beer-Sheva", "Hamelacha Street", 41);
		TheaterDao.insertTheaters(theaters);
	}

	private static void newHalls() throws DataFormatException {
		ArrayList<Theater> theaters = TheaterDao.getAllTheater();
		ArrayList<Hall> halls = new ArrayList<Hall>();

		for (Theater theater : theaters) {
			int numOfHall = getRand(MIN_NUM_OF_HALL, MAX_NUM_OF_HALL);
			while (numOfHall > 0) {
				halls.add(new Hall(theater.getTheaterId()));
				numOfHall--;
			}
		}

		Hall[] newHalls = new Hall[halls.size()];
		int[] NumRowsArr = new int[halls.size()];
		int[] NumseatsArr = new int[halls.size()];
		int i = 0;
		for (Hall hall : halls) {
			newHalls[i] = hall;
			NumRowsArr[i] = getRand(MIN_ROWS, MAX_ROWS);
			NumseatsArr[i] = getRand(MIN_SEATS_IN_ROW, MAX_SEATS_IN_ROW);
			i++;
		}
		HallDao.insertHalls(newHalls, NumRowsArr, NumseatsArr);
	}

	private static void newUsers() throws DataFormatException {
		User[] users = new User[3];
		users[0] = new User("moti", "dagan", "moti.dagan@gmail.com", "moti123", "0521234567");
		users[1] = new User("sarit", "sason", "sarit.sason@gmail.com", "sason49", "0507894562");
		users[2] = new User("dror", "atias", "dror1994@gmail.com", "Dror94", "0524564562");
		UserDao.insertUsers(users);
	}

	private static void newmovie() throws DataFormatException {
		String SummaryJungleCruise = "Based on Disneyland's theme park ride where a small riverboat takes a group of travelers through a jungle filled with dangerous animals and reptiles but with a supernatural element.";
		String SummarySpaceJam = "A rogue artificial intelligence kidnaps the son of famed basketball player LeBron James, who then has to work with Bugs Bunny to win a basketball game.";
		String SummaryBlackWidow = "Natasha Romanoff confronts the darker parts of her ledger when a dangerous conspiracy with ties to her past arises.";
		String SummaryLuca = "On the Italian Riviera, an unlikely but strong friendship grows between a human being and a sea monster disguised as a human.";
		String SummaryTheShawshankRedemption ="Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.";
		String SummarytheGodfather ="The aging patriarch of an organized crime dynasty in postwar New York City transfers control of his clandestine empire to his reluctant youngest son.";
		String SummaryTheDarkKnight ="When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.";
		String SummaryTheMatrix ="When a beautiful stranger leads computer hacker Neo to a forbidding underworld, he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence.";
		String SummaryGladiator ="A former Roman General sets out to exact vengeance against the corrupt emperor who murdered his family and sent him into slavery.";
		String SummaryWALLE ="In the distant future, a small waste-collecting robot inadvertently embarks on a space journey that will ultimately decide the fate of mankind.";
		String SummaryShangChi="Shang-Chi, the master of weaponry-based Kung Fu, is forced to confront his past after being drawn into the Ten Rings organization.";
		Movie[] movies = new Movie[11];

		int imageId = ImageDao.insertImage(new Image("\\" +"Jungle Cruise (2021).jpg"));
		String trailer0 = "<iframe width=\"876\" height=\"364\" src=\"https://www.youtube.com/embed/f_HvoipFcA8\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
		movies[0] = new Movie("Jungle Cruise", 2021, "Action", SummaryJungleCruise, "Jaume Collet-Serra", 127, imageId,trailer0);

		imageId = ImageDao.insertImage(new Image("\\" + "Space Jam A New Legacy (2021).jpg"));
		String trailer1 = "<iframe width=\"727\" height=\"409\" src=\"https://www.youtube.com/embed/olXYZOsXw_o\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
		movies[1] = new Movie("Space Jam: A New Legacy", 2021, "animation", SummarySpaceJam, "Malcolm D. Lee", 115,
				imageId,trailer1);

		imageId = ImageDao.insertImage(new Image("\\" +"Black Widow (2021).jpg"));
		String trailer2 = "<iframe width=\"727\" height=\"409\" src=\"https://www.youtube.com/embed/Fp9pNPdNwjI\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
		movies[2] = new Movie("Black Widow", 2021, "Action", SummaryBlackWidow, "Cate Shortland", 133, imageId,trailer2);

		imageId = ImageDao.insertImage(new Image("\\" +"luca (2021).jpg"));
		String trailer3 = "<iframe width=\"1366\" height=\"625\" src=\"https://www.youtube.com/embed/mYfJxlgR2jw\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
		movies[3] = new Movie("Luca", 2021, "Action", SummaryLuca, "Enrico Casarosa", 95, imageId,trailer3);

		
		imageId = ImageDao.insertImage(new Image("\\" +"The Shawshank Redemption (1994).jpg"));
		String trailer4 = "<iframe width=\"790\" height=\"444\" src=\"https://www.youtube.com/embed/6hB3S9bIaco\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
		movies[4] = new Movie("The Shawshank Redemption", 1994, "Drama", SummaryTheShawshankRedemption, "Frank Darabont", 142, imageId,trailer4);

		imageId = ImageDao.insertImage(new Image("\\" +"The Godfather (1972).jpg"));
		String trailer5 = "<iframe width=\"640\" height=\"360\" src=\"https://www.youtube.com/embed/sY1S34973zA\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
		movies[5] = new Movie("The Godfather", 1994, "crime", SummarytheGodfather, "Francis Ford Coppola", 175, imageId,trailer5);


		imageId = ImageDao.insertImage(new Image("\\" +"The Dark Knight (2008).jpg"));
		String trailer6 = "<iframe width=\"828\" height=\"352\" src=\"https://www.youtube.com/embed/EXeTwQWrcwY\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
		movies[6] = new Movie("The Dark Knight", 2008, "Action", SummaryTheDarkKnight, "Christopher Nolan", 0, imageId,trailer6);
		

		imageId = ImageDao.insertImage(new Image("\\" +"The Matrix (1999).jpg"));
		String trailer7 = "<iframe width=\"640\" height=\"360\" src=\"https://www.youtube.com/embed/D4eJx-0g3Do\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
		movies[7] = new Movie("The Matrix", 1999, "Action", SummaryTheMatrix, "Lana Wachows & kiLilly Wachowski", 136, imageId,trailer7);
		
		
		imageId = ImageDao.insertImage(new Image("\\" +"Gladiator (2000).jpg"));
		String trailer8 = "<iframe width=\"640\" height=\"360\" src=\"https://www.youtube.com/embed/owK1qxDselE\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
		movies[8] = new Movie("Gladiator", 2000, "Action", SummaryGladiator,"Ridley Scott", 155, imageId,trailer8);
		
		
		
		imageId = ImageDao.insertImage(new Image("\\" +"WALL-E (2008).jpg"));
		String trailer9 = "<iframe width=\"640\" height=\"360\" src=\"https://www.youtube.com/embed/CZ1CATNbXg0\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
		movies[9] = new Movie("WALL-E", 2008, "animation", SummaryWALLE, "Andrew Stanton", 158, imageId,trailer9);
		
		imageId = ImageDao.insertImage(new Image("\\" +"Shang Chi Va'Agadat Esser Ha'Taba'ot (2021).jpg"));
		String trailer10 = "<iframe width=\"727\" height=\"409\" src=\"https://www.youtube.com/embed/8YjFbMbfXaQ\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
		movies[10] = new Movie("Shang Chi Va'Agadat Esser Ha'Taba'ot", 2021, "Action", SummaryShangChi, "Destin Daniel Cretton", 132, imageId,trailer10);
		
		
		
		MovieDao.insertMovies(movies);
	}

	private static void newScreening() throws DataFormatException {

		ArrayList<Movie> movies = MovieDao.getAllMovie();
		ArrayList<Hall> halls = HallDao.getAllHall();
		ArrayList<MyTimestamp> datesAndTimes = new ArrayList<MyTimestamp>();

		for (int i = 0; i < MAX_DAY - MIN_DAY; i++) {
			for (int j = 0; j < MAX_HOUR - MIN_HOUR; j++) {
				datesAndTimes.add(new MyTimestamp(YEAR, MONTH, i + MIN_DAY, j*3 + MIN_HOUR, 0, 0, 0));
			}
		}

		for (MyTimestamp dateAndTime : datesAndTimes) {
			int x = getRand(0, movies.size());
			int y = getRand(0, halls.size());
			ScreeningDao
					.insertScreening(new Screening(movies.get(x).getMovieId(), halls.get(y).getHallId(), dateAndTime));
		}
	}
}
