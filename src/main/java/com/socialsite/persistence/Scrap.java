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

	public User getAuthor()
	{
		return author;
	}

	public long getId()
	{
		return id;
	}

	public String getMessage()
	{
		return message;
	}

	public User getReceiver()
	{
		return receiver;
	}

	public Date getTime()
	{
		return time;
	}

	public void setAuthor(final User author)
	{
		this.author = author;
	}

	public void setId(final long id)
	{
		this.id = id;
	}

	public void setMessage(final String message)
	{
		this.message = message;
	}

	public void setReceiver(final User receiver)
	{
		this.receiver = receiver;
	}

	public void setTime(final Date time)
	{
		this.time = time;
	}

}
