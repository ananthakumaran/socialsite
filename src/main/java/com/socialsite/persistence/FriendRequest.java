package com.socialsite.persistence;

/**
 * Domain object for the friend requests
 * 
 * @author Ananth
 * 
 */
public class FriendRequest implements AbstractDomain
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private long				id;
	/** The user who sends the friend request */
	private User				user;
	/** The user who received the friend request */
	private User				friend;
	/** message send during friend request */
	private String				message;

	public User getFriend()
	{
		return friend;
	}

	public long getId()
	{
		return id;
	}

	public String getMessage()
	{
		return message;
	}

	public User getUser()
	{
		return user;
	}

	public void setFriend(final User friend)
	{
		this.friend = friend;
	}

	public void setId(final long id)
	{
		this.id = id;
	}

	public void setMessage(final String message)
	{
		this.message = message;
	}

	public void setUser(final User user)
	{
		this.user = user;
	}

}
