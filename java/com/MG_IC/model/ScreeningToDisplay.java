package com.MG_IC.model;

public class ScreeningToDisplay {

	private int screeningId;
	private String theatreName;
	private String hallNum;
	private String movieName;
	private String date;
	private String time;
	private String openForSale;

	public ScreeningToDisplay(int screeningId,String theatreName, String hallNum, String movieName, String date, String time,
			String openForSale) {
		super();
		this.screeningId = screeningId;
		this.theatreName = theatreName;
		this.hallNum = hallNum;
		this.movieName = movieName;
		this.date = date;
		this.time = time;
		this.openForSale = openForSale;
	}

	@Override
	public String toString() {
		return "Screening [screeningId=" + screeningId + ", hallNum=" + hallNum + ", movieName=" + movieName + ", date="
				+ date + ",time=" + time + ", openForSale=" + openForSale + theatreName + "]";
	}

	
	
	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}
	
	public int getScreeningId() {
		return screeningId;
	}

	public void setScreeningId(int screeningId) {
		this.screeningId = screeningId;
	}

	public String getHallNum() {
		return hallNum;
	}

	public void setHallNum(String hallNum) {
		this.hallNum = hallNum;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getOpenForSale() {
		return openForSale;
	}

	public void setOpenForSale(String openForSale) {
		this.openForSale = openForSale;
	}

}
