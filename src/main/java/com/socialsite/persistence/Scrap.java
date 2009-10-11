package com.socialsite.persistence;

import java.util.Date;


/**
 * Scrap Details .This is the business object that we persist to the Database
 * 
 * @author Ananth
 * 
 */
public class Scrap implements AbstractDomain
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private long				id;
	private User				author;
	private User				receiver;
	private Date				time;
	private String				message;

	public long getId()
	{
		return id;
	}

	public User getAuthor()
	{
		return author;
	}

	public void setAuthor(User author)
	{
		this.author = author;
	}

	public User getReceiver()
	{
		return receiver;
	}

	public void setReceiver(User receiver)
	{
		this.receiver = receiver;
	}

	public Date getTime()
	{
		return time;
	}

	public void setTime(Date time)
	{
		this.time = time;
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
