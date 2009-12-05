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

	public String getMessage()
	{
		return message;
	}

	public User getSender()
	{
		return Sender;
	}

	public void setMessage(final String message)
	{
		this.message = message;
	}

	public void setSender(final User user)
	{
		Sender = user;
	}
}
