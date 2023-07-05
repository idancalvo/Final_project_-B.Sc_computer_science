package com.MG_IC.model;

import java.util.zip.DataFormatException;

public class Seat {

	private static final int MAX_SEAT_ROW = 50;
	private static final int MAX_SEAT_NUM = 50;

	private int seatId;
	private int hallId;
	private int seatRow;
	private int seatNum;

	public Seat(int seatId, int hallId, int seatRow, int seatNum) throws DataFormatException {
		super();
		setSeatId(seatId);
		setHallId(hallId);
		setSeatRow(seatRow);
		setSeatNum(seatNum);
	}

	public Seat(int hallId, int seatRow, int seatNum) throws DataFormatException {
		this(0, hallId, seatRow, seatNum);
	}

	@Override
	public String toString() {
		return "Seat [seatId=" + seatId + ", hallId=" + hallId + ", seatRow=" + seatRow + ", seatNum=" + seatNum + "]";
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Seat) {
			Seat otherSeat = (Seat) other;
			if (seatId == otherSeat.getSeatId()) {
				if (hallId == otherSeat.getHallId()) {
					if (seatRow == otherSeat.getSeatRow()) {
						if (seatNum == otherSeat.getSeatNum()) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) throws DataFormatException {
		if (seatId < 0 || seatId > Integer.MAX_VALUE) {
			throw new DataFormatException("Invalid 'seatId' value [Seat]");
		}
		this.seatId = seatId;
	}

	public int getHallId() {
		return hallId;
	}

	public void setHallId(int hallId) throws DataFormatException {
		if (hallId < 0 || hallId > Integer.MAX_VALUE) {
			throw new DataFormatException("Invalid 'hallId' value [Seat]");
		}
		this.hallId = hallId;
	}

	public int getSeatRow() {
		return seatRow;
	}

	public void setSeatRow(int seatRow) throws DataFormatException {
		if (seatRow < 0 || seatRow > MAX_SEAT_ROW) {
			throw new DataFormatException("Invalid 'seatRow' value [Seat]");
		}
		this.seatRow = seatRow;
	}

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) throws DataFormatException {
		if (seatNum < 0 || seatNum > MAX_SEAT_NUM) {
			throw new DataFormatException("Invalid 'seatNum' value [Seat]");
		}
		this.seatNum = seatNum;
	}

}
