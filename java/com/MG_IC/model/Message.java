package com.MG_IC.model;


import java.util.zip.DataFormatException;

public class Message {
	
	private static final int MAX_MESSAGE_LENGTH = 1000;
	
	int messageId;
	int userId;
	String message;
	
	
	public Message(int messageId, int userId, String message) throws DataFormatException {
		super();
		setMessageId(messageId);
		setUserId(userId);
		setMessage(message);
	}
	
	public Message(int userId, String message) throws DataFormatException {
		this(0,userId,message);
	}
	
	

	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", userId=" + userId + ", message=" + message + "]";
	}
	
	
	public int getMessageId() {
		return messageId;
	}
	
	
	public void setMessageId(int messageId) throws DataFormatException {
		if (messageId < 0 || messageId > Integer.MAX_VALUE) {
			throw new DataFormatException("Invalid 'messageId' value [Message]");
		}
		this.messageId = messageId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) throws DataFormatException {
		if (userId < 0 || userId > Integer.MAX_VALUE) {
			throw new DataFormatException("Invalid 'userId' value [Message]");
		}
		this.userId = userId;
	}
	
	public String getMessage() {
		return message;
	}
	
	
	public void setMessage(String message) throws DataFormatException {
		if (message.length() > MAX_MESSAGE_LENGTH) {
			throw new DataFormatException("Invalid 'message' value [Message]");
		}
		this.message = message;
	}
	
	

}
