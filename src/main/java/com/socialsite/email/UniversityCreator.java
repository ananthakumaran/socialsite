package com.socialsite.email;

import com.socialsite.persistence.Admin;

/**
 * 
 * @author Ananth
 * 
 */
public class UniversityCreator
{
	private Admin admin;

	public UniversityCreator(Admin admin)
	{
		this.admin = admin;
	}

	public void create()
	{
		Email email = new Email();
		// am i sending message to myself :)
		email.addReceivers("ananthakumaran@gmail.com");
		email.setSubject("University Activation");
		email.setMessage(getMessage());
		new EmailSender().send(email);
	}

	public String getMessage()
	{
		// TODO create a url
		// add a entry to activation table
		return "Hello";
	}
}
