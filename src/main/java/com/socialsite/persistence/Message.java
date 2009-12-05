package com.socialsite.persistence;

import java.util.Date;

public abstract class Message implements AbstractDomain
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private long				id;

	private Date				time;

	private User				user;

	public Message()
	{
	}

	public long getId()
	{
		return id;
	}

	public Date getTime()
	{
		return time;
	}

	public User getUser()
	{
		return user;
	}

	public void setId(final long id)
	{
		this.id = id;
	}

	public void setTime(final Date time)
	{
		this.time = time;
	}

	public void setUser(final User user)
	{
		this.user = user;
	}

}
