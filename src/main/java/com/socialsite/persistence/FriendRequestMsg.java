package com.socialsite.persistence;

public class FriendRequestMsg extends Message
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/** The Sender who sends the friend request */
	private User				Sender;
	/** message send during friend request */
	private String				message;
	
	
	public User getSender()
	{
		return Sender;
	}
	public void setSender(User user)
	{
		this.Sender = user;
	}
	public String getMessage()
	{
		return message;
	}
	public void setMessage(String message)
	{
		this.message = message;
	}
}
