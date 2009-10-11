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

	public long getId()
	{
		return id;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public User getFriend()
	{
		return friend;
	}

	public void setFriend(User friend)
	{
		this.friend = friend;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public void setId(long id)
	{
		this.id = id;
	}

}
