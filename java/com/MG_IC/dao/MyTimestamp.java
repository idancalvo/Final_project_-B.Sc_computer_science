package com.MG_IC.dao;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class MyTimestamp extends Timestamp {

	@SuppressWarnings("deprecation")
	public MyTimestamp(Timestamp timestamp) {
		super(timestamp.getYear(), timestamp.getMonth(), timestamp.getDate(), timestamp.getHours(),
				timestamp.getMinutes(), timestamp.getSeconds(), timestamp.getNanos());
	}

	public MyTimestamp(MyTimestamp timestamp) {
		this((Timestamp) timestamp);
	}

	@SuppressWarnings("deprecation")
	public MyTimestamp(Date date, Time time) {
		this(date.getYear(), date.getMonth(), date.getDate(), time.getHours(), time.getMinutes(), time.getSeconds());
	}

	@SuppressWarnings("deprecation")
	public MyTimestamp(int year, int month, int date, int hour, int minute, int second, int nano) {
		super(year - 1900, month - 1, date, hour, minute, second, nano);
	}

	public MyTimestamp(int year, int month, int date, int hour, int minute, int second) {
		this(year, month, date, hour, minute, second, 0);
	}

	@SuppressWarnings("deprecation")
	public void addMinutes(int minute) {
		setMinutes(this.getMinutes() + minute);
	}
	
	@SuppressWarnings("deprecation")
	public void addDay(int day) {
		setDate(this.getDate() + day);
	}
	

	@SuppressWarnings("deprecation")
	public String timeToString() {
		int hours = getHours();
		int minutes = getMinutes();
		int seconds = getSeconds();

		String ans = "";

		if (hours < 10) {
			ans += "0";
		}
		ans += hours + ":";

		if (minutes < 10) {
			ans += "0";
		}
		ans += minutes + ":";

		if (seconds < 10) {
			ans += "0";
		}
		ans += seconds;

		return ans;
	}

	@SuppressWarnings("deprecation")
	public String dateToString() {
		int year = getYear() + 1900;
		int month = getMonth() + 1;
		int day = getDate();

		String ans = "" + year + "-";
		if (month < 10) {
			ans += "0";
		}
		ans += month + "-";
		if (day < 10) {
			ans += "0";
		}
		ans += day;
		return ans;
	}

	/**
	 * Converter from Strinerg to MyTimestamp
	 * @param date - yyyy-MM-dd 
	 * @param time -HH:mm
	 * @return MyTimestamp
	 * @throws ParseException
	 */
	public static MyTimestamp StringConvert(String date, String time) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		java.util.Date parsedDate = dateFormat.parse(date + " " + time);
		return new MyTimestamp(new Timestamp(parsedDate.getTime()));
	}

}
