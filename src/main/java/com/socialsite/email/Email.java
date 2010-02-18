package com.socialsite.email;

import java.util.ArrayList;
import java.util.List;

/**
 * email
 * 
 * @author Ananth
 * 
 */
public class Email
{

	private String sender;
	private List<String> receivers = new ArrayList<String>();
	private String message;
	private String subject;

	public Email()
	{
	}

	public void addReceivers(String receiver)
	{
		receivers.add(receiver);
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}


	public String getMessage()
	{
		return message;
	}

	public List<String> getReceivers()
	{
		return receivers;
	}

	public String getSender()
	{
		return sender;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public void setReceivers(List<String> receivers)
	{
		this.receivers = receivers;
	}

	public void setSender(String sender)
	{
		this.sender = sender;
	}
}
