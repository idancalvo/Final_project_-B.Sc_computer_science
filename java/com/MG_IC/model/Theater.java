package com.MG_IC.model;

import java.io.Serializable;
import java.util.zip.DataFormatException;

import javax.servlet.annotation.WebListener;


@WebListener
public class Theater  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private static final int MAX_NAME_LENGTH = 50;
	private static final int MAX_CITY_LENGTH = 30;
	private static final int MAX_STREET_LENGTH = 40;
	private static final int MAX_HOUSE_NUMBER = 999;

	private int theaterId;
	private String name;
	private String city;
	private String street;
	private int houseNumber;

	
	public Theater() {
	}

	public Theater(int theaterId, String name, String city, String street, int houseNumber) throws DataFormatException {
		super();
		setTheaterId(theaterId);
		setName(name);
		setCity(city);
		setStreet(street);
		setHouseNumber(houseNumber);

	}

	public Theater(String name, String city, String street, int houseNumber) throws DataFormatException {
		this(0, name, city, street, houseNumber);
	}

	@Override
	public String toString() {
		return "Theater [theaterId=" + theaterId + ", name=" + name + ", city=" + city + ", street=" + street
				+ ", houseNumber=" + houseNumber + "]";
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Theater) {
			Theater otherTheater = (Theater) other;
			if (theaterId == otherTheater.getTheaterId()) {
				if (name.equals(otherTheater.getName())) {
					if (city.equals(otherTheater.getCity())) {
						if (street.equals(otherTheater.getStreet())) {
							if (houseNumber == otherTheater.getHouseNumber()) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	public int getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(int theaterId) throws DataFormatException {
		if (theaterId < 0 || theaterId > Integer.MAX_VALUE) {
			throw new DataFormatException("Invalid 'theaterId' value [Theater]");
		}
		this.theaterId = theaterId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws DataFormatException {
		if (name.length() > MAX_NAME_LENGTH) {
			throw new DataFormatException("Invalid 'name' value [Theater]");
		}
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) throws DataFormatException {
		if (city.length() > MAX_CITY_LENGTH) {
			throw new DataFormatException("Invalid 'city' value [Theater]");
		}
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) throws DataFormatException {
		if (street.length() > MAX_STREET_LENGTH) {
			throw new DataFormatException("Invalid 'street' value [Theater]");
		}
		this.street = street;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(int houseNumber) throws DataFormatException {
		if (houseNumber < 0 || houseNumber > MAX_HOUSE_NUMBER) {
			throw new DataFormatException("Invalid 'street' value [Theater]");
		}
		this.houseNumber = houseNumber;
	}

}
