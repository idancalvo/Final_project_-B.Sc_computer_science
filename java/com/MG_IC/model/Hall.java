package com.MG_IC.model;

import java.beans.JavaBean;
import java.util.zip.DataFormatException;

@JavaBean
public class Hall {

	private int hallId;
	private int theaterId;
	private int hallNum;
	
	
	public Hall(int hallId, int theaterId, int hallNum) throws DataFormatException {
		super();
		setHallId(hallId);
		setTheaterId(theaterId);
		setHallNum(hallNum);
	}

	public Hall(int theaterId, int hallNum) throws DataFormatException {
		this(0, theaterId, hallNum);
	}

	public Hall(int theaterId) throws DataFormatException {
		this(theaterId, 0);

	}

	@Override
	public String toString() {
		return "Hall [hallId=" + hallId + ", theaterId=" + theaterId + ", hallNum=" + hallNum + "]";
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Hall) {
			Hall otherHall = (Hall) other;
			if (hallId == otherHall.getHallId()) {
				if (hallNum == otherHall.getHallNum()) {
					if (theaterId == otherHall.getTheaterId()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public int getHallId() {
		return hallId;
	}

	public void setHallId(int hallId) throws DataFormatException {
		if (hallId < 0 || hallId > Integer.MAX_VALUE) {
			throw new DataFormatException("Invalid 'hallId' value [Hall]");
		}
		this.hallId = hallId;
	}

	public int getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(int theaterId) throws DataFormatException {
		if (theaterId < 0 || theaterId > Integer.MAX_VALUE) {
			throw new DataFormatException("Invalid 'theaterId' value [Hall]");
		}
		this.theaterId = theaterId;
	}

	public int getHallNum() {
		return hallNum;
	}

	public void setHallNum(int hallNum) throws DataFormatException {
		if (hallNum < 0 || hallNum > Integer.MAX_VALUE) {
			throw new DataFormatException("Invalid 'hallNum' value [Hall]");
		}
		this.hallNum = hallNum;
	}

}
