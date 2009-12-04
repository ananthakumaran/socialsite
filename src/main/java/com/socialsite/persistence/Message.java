package com.socialsite.persistence;

import java.util.Date;

public abstract class Message implements AbstractDomain
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	private long id;
	
	private Date time;
	
	private User user;
	
	public Message()
	{
	}
	
	public long getId()
	{
	 return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public void setUser(User user)
	{
		this.user = user;
	}
	
	public User getUser()
	{
		return user;
	}
	
	public Date getTime()
	{
		return time;
	}
	
	public void setTime(Date time)
	{
		this.time = time;
	}
	
}
