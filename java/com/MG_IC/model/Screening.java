package com.MG_IC.model;

import java.util.zip.DataFormatException;

import com.MG_IC.dao.MyTimestamp;

public class Screening {

	private int screeningId;
	private int movieId;
	private int hallId;
	private MyTimestamp dateAndTime;
	private boolean openForSale;

	public Screening(int screeningId, int movieId, int hallId, MyTimestamp dateAndTime, boolean openForSale)
			throws DataFormatException {
		super();
		setScreeningId(screeningId);
		setMovieId(movieId);
		setHallId(hallId);
		setDateAndTime(dateAndTime);
		setOpenForSale(openForSale);
	}

	public Screening(int movieId, int hallId, MyTimestamp dateAndTime, boolean openForSale) throws DataFormatException {
		this(0, movieId, hallId, dateAndTime, openForSale);
	}

	public Screening(int movieId, int hallId, MyTimestamp dateAndTime)
			throws DataFormatException {
		this(movieId, hallId, dateAndTime, true);
	}

	@Override
	public String toString() {
		return "Screening [screeningId=" + screeningId + ", movieId=" + movieId + ", hallId=" + hallId + ", dateAndTime="
				+ dateAndTime + ", openForSale=" + openForSale + "]";
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Screening) {
			Screening otherScreening = (Screening) other;
			if (screeningId == otherScreening.getScreeningId()) {
				if (movieId == otherScreening.getMovieId()) {
					if (hallId == otherScreening.getHallId()) {
						if (dateAndTime.equals(otherScreening.getDateAndTime())) {
							if (openForSale == otherScreening.isOpenForSale()) {
								return true;
							}
						}
					}

				}
			}
		}
		return false;
	}

	public int getScreeningId() {
		return screeningId;
	}

	public void setScreeningId(int screeningId) throws DataFormatException {
		if (screeningId < 0 || screeningId > Integer.MAX_VALUE) {
			throw new DataFormatException("Invalid 'screeningId' value [Screening]");
		}
		this.screeningId = screeningId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) throws DataFormatException {
		if (movieId < 0 || movieId > Integer.MAX_VALUE) {
			throw new DataFormatException("Invalid 'movieId' value [Screening]");
		}
		this.movieId = movieId;
	}

	public int getHallId() {
		return hallId;
	}

	public void setHallId(int hallId) throws DataFormatException {
		if (hallId < 0 || hallId > Integer.MAX_VALUE) {
			throw new DataFormatException("Invalid 'hallId' value [Screening]");
		}
		this.hallId = hallId;
	}

	public MyTimestamp getDateAndTime() {
		return this.dateAndTime;
	}

	public void setDateAndTime(MyTimestamp dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public boolean isOpenForSale() {
		return openForSale;
	}

	public void setOpenForSale(boolean openForSale) {
		this.openForSale = openForSale;
	}

}
