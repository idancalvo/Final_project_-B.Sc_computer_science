package com.MG_IC.model;

import com.MG_IC.dao.MyTimestamp;

public class TicketToDisplay {

	private int ticketId;
	private int status;
	// theater
	private String theaterName;
	private String theaterCity;
	private String theaterStreet;
	private int theaterHouseNumber;
	// hall
	private int hallNum;
	// seat
	private int seatRow;
	private int seatNum;
	// movie
	private String movieName;
	private int movieLength;
	// screening
	private String dateAndTime;
	// user
	private int userId;
	private String userFirstName;
	private String userLastName;

	public TicketToDisplay(int ticketId, int status, String theaterName, String theaterCity, String theaterStreet,
			int theaterHouseNumber, int hallNum, int seatRow, int seatNum, String movieName, int movieLength,
			MyTimestamp dateAndTime, int userId, String userFirstName, String userLastName) {
		super();
		setTicketId(ticketId);
		setStatus(status);

		setTheaterName(theaterName);
		setTheaterCity(theaterCity);
		setTheaterStreet(theaterStreet);
		setTheaterHouseNumber(theaterHouseNumber);

		setHallNum(hallNum);

		setSeatRow(seatRow);
		setSeatNum(seatNum);

		setMovieName(movieName);
		setMovieLength(movieLength);

		setDateAndTime(dateAndTime);

		setUserId(userId);
		setUserFirstName(userFirstName);
		setUserLastName(userLastName);
	}

	@Override
	public String toString() {
		return "TicketToDisplay [ticketId=" + ticketId + ", theaterName=" + theaterName + ", theaterCity=" + theaterCity
				+ ", theaterStreet=" + theaterStreet + ", theaterHouseNumber=" + theaterHouseNumber + ", hallNum="
				+ hallNum + ", seatRow=" + seatRow + ", seatNum=" + seatNum + ", movieName=" + movieName
				+ ", movieLength=" + movieLength + ",  MyTimestamp dateAndTime=" + dateAndTime + ", userFirstName="
				+ userFirstName + ", userLastName=" + userLastName + "]";
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public String getTheaterCity() {
		return theaterCity;
	}

	public void setTheaterCity(String theaterCity) {
		this.theaterCity = theaterCity;
	}

	public String getTheaterStreet() {
		return theaterStreet;
	}

	public void setTheaterStreet(String theaterStreet) {
		this.theaterStreet = theaterStreet;
	}

	public int getTheaterHouseNumber() {
		return theaterHouseNumber;
	}

	public void setTheaterHouseNumber(int theaterHouseNumber) {
		this.theaterHouseNumber = theaterHouseNumber;
	}

	public int getHallNum() {
		return hallNum;
	}

	public void setHallNum(int hallNum) {
		this.hallNum = hallNum;
	}

	public int getSeatRow() {
		return seatRow;
	}

	public void setSeatRow(int seatRow) {
		this.seatRow = seatRow;
	}

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public int getMovieLength() {
		return movieLength;
	}

	public void setMovieLength(int movieLength) {
		this.movieLength = movieLength;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(MyTimestamp dateAndTime) {
		this.dateAndTime = dateAndTime.dateToString() + " " + dateAndTime.timeToString();
		;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getTheaterAddress() {
		return getTheaterStreet() + " " + getTheaterHouseNumber() + ", " + getTheaterCity();
	}

}
