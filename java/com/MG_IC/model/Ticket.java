package com.MG_IC.model;

import java.util.zip.DataFormatException;

public class Ticket {

	public static final int AVAILABLE = 0;
	public static final int SOLD = 1;
	public static final int SAVE = 2;
	public static final int CANCELED = 3;

	private int ticketId;
	private int screeningId;
	private int seatId;
	private int status;
	private int userId;

	public Ticket(int ticketId, int screeningId, int seatId, int status, int userId) throws DataFormatException {
		super();
		setTicketId(ticketId);
		setScreeningId(screeningId);
		setSeatId(seatId);
		setStatus(status);
		setUserId(userId);
	}

	public Ticket(int screeningId, int seatId, int status, int userId) throws DataFormatException {
		this(0, screeningId, seatId, status, userId);
	}

	public Ticket(int screeningId, int seatId, int userId) throws DataFormatException {
		this(screeningId, seatId, AVAILABLE, userId);
	}

	public Ticket(int screeningId, int seatId) throws DataFormatException {
		this(screeningId, seatId, AVAILABLE, 0);
	}

	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", screeningId=" + screeningId + ", seatId=" + seatId + ", status="
				+ status + ", userId=" + userId + "]";
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Ticket) {
			Ticket otherTicket = (Ticket) other;
			if (ticketId == otherTicket.getTicketId()) {
				if (screeningId == otherTicket.getScreeningId()) {
					if (seatId == otherTicket.getSeatId()) {
						if (status == otherTicket.getStatus()) {
							if (userId == otherTicket.getUserId()) {
								return true;
							}
						}

					}
				}
			}
		}
		return false;
	}

	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) throws DataFormatException {
		if (ticketId < 0 || ticketId > Integer.MAX_VALUE) {
			throw new DataFormatException("Invalid 'ticketId' value [Ticket]");
		}
		this.ticketId = ticketId;
	}

	public int getScreeningId() {
		return screeningId;
	}

	public void setScreeningId(int screeningId) throws DataFormatException {
		if (screeningId < 0 || screeningId > Integer.MAX_VALUE) {
			throw new DataFormatException("Invalid 'screeningId' value [Ticket]");
		}
		this.screeningId = screeningId;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) throws DataFormatException {
		if (seatId < 0 || seatId > Integer.MAX_VALUE) {
			throw new DataFormatException("Invalid 'seatId' value [Ticket]");
		}
		this.seatId = seatId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) throws DataFormatException {
		if (status != AVAILABLE && status != SOLD && status != SAVE && status != CANCELED) {
			throw new DataFormatException("Invalid 'status' value [Ticket]");
		}
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) throws DataFormatException {
		if (userId < 0 || userId > Integer.MAX_VALUE) {
			throw new DataFormatException("Invalid 'userId' value [Ticket]");
		}
		this.userId = userId;
	}

}
