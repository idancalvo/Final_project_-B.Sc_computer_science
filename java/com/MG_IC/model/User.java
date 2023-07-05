package com.MG_IC.model;

import java.io.Serializable;
import java.util.zip.DataFormatException;

public class User implements Serializable {
	
	private static final long serialVersionUID = -8875985761804943839L;
	
	private static final int MAX_F_NAME_LENGTH = 30;
	private static final int MAX_L_NAME_LENGTH = 30;
	private static final int MAX_EMAIL_LENGTH = 100;
	private static final int MAX_PASSWORD_LENGTH = 50;
	private static final int MAX_PHONE_LENGTH = 10;

	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phone;
	private int imageId;
	private boolean admin;

	public User() throws DataFormatException {
		this("", "", "a@a.com", "", "", 0);

	}

	public User(int userId, String firstName, String lastName, String email, String password, String phone, int imageId,
			boolean admin) throws DataFormatException {
		super();
		setUserId(userId);
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPassword(password);
		setPhone(phone);
		setImageId(imageId);
		setAdmin(admin);
	}

	public User(String firstName, String lastName, String email, String password, String phone, int imageId,
			boolean admin) throws DataFormatException {
		this(0, firstName, lastName, email, password, phone, imageId, admin);
	}

	public User(String firstName, String lastName, String email, String password, String phone, int imageId)
			throws DataFormatException {
		this(firstName, lastName, email, password, phone, imageId, false);

	}

	public User(String firstName, String lastName, String email, String password, String phone)
			throws DataFormatException {
		this(firstName, lastName, email, password, phone, 0);

	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", phone=" + phone + ", imageId=" + imageId + ", admin=" + admin + "]";
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof User) {
			User otherUser = (User) other;
			if (userId == otherUser.getUserId()) {
				if (firstName.equals(otherUser.getFirstName())) {
					if (lastName.equals(otherUser.getLastName())) {
						if (email.equals(otherUser.getEmail())) {
							if (password.equals(otherUser.getPassword())) {
								if (phone.equals(otherUser.getPhone())) {
									if (imageId == otherUser.getImageId()) {
										if (admin == otherUser.isAdmin()) {
											return true;
										}
									}
								}
							}

						}
					}

				}
			}
		}
		return false;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) throws DataFormatException {
		if (userId < 0 || userId > Integer.MAX_VALUE) {
			throw new DataFormatException("Invalid 'userId' value [User]");
		}
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) throws DataFormatException {
		if (firstName.length() > MAX_F_NAME_LENGTH) {
			throw new DataFormatException("Invalid 'firstName' value [User]");
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) throws DataFormatException {
		if (lastName.length() > MAX_L_NAME_LENGTH) {
			throw new DataFormatException("Invalid 'lastName' value [User]");
		}
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws DataFormatException {
		if (email.length() > MAX_EMAIL_LENGTH) {
			throw new DataFormatException("Invalid 'email' value [User]");
		}
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws DataFormatException {
		if (password.length() > MAX_PASSWORD_LENGTH) {
			throw new DataFormatException("Invalid 'password' value [User]");
		}
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) throws DataFormatException {
		if (phone.length() > MAX_PHONE_LENGTH) {
			throw new DataFormatException("Invalid 'phone' value [User]");
		}
		this.phone = phone;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) throws DataFormatException {
		if (imageId < 0 || imageId > Integer.MAX_VALUE) {
			throw new DataFormatException("Invalid 'imageId' value [User]");
		}
		this.imageId = imageId;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

}
