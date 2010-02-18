package com.socialsite.email;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Don't run this test every time. This will send a email
 * 
 * @author Ananth
 * 
 */
public class EmailSenderTest
{
	@Test
	@Ignore
	public void testSend()
	{
		Email email = new Email();
		// am i sending message to myself :)
		email.addReceivers("ananthakumaran@gmail.com");
		email.setSubject("Unit Testing");
		email.setMessage("Test Message");
		new EmailSender().send(email);
	}
}
