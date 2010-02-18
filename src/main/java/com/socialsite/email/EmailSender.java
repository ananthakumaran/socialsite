package com.socialsite.email;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * send the email via the google appspot server
 * 
 * @author Ananth
 * 
 */
public class EmailSender
{
	private final static Logger logger = Logger.getLogger(EmailSender.class.getName());
	private StringBuffer emailURL = new StringBuffer();

	public EmailSender()
	{
		// set up the proxy server
		Properties props = System.getProperties();
		props.put("http.proxyHost", "proxy.karunya.edu");
		props.put("http.proxyPort", "3128");
	}

	/**
	 * I cann't send email through smtp. So here i am sending mail through a
	 * proxy server. To send email through smtp server change this
	 * implementation
	 * 
	 * @param email
	 */
	public void send(Email email)
	{
		// TODO this should run asynchronously
		emailURL.append("http://email-relay.appspot.com/email_service?");
		// handle multiple receivers
		addParam("receiver", email.getReceivers().get(0));
		// someone is going to send email using this thing
		addParam("secret", "dontMESSwithme");
		addParam("msg", email.getMessage());
		addParam("subject", email.getSubject());


		try
		{
			URL url = new URL(emailURL.toString());
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			StringBuffer reply = new StringBuffer();
			while ((line = reader.readLine()) != null)
			{
				reply.append(line);
			}
			reader.close();

			if (!reply.toString().equals("message send successfully"))
			{
				// message sending failed
				logger.severe("Could not send Email");
				logger.severe(reply.toString());
			}

		}
		catch (MalformedURLException e)
		{
			logger.log(Level.SEVERE, "", e);
		}
		catch (IOException e)
		{
			logger.log(Level.SEVERE, "", e);
		}
	}

	public void addParam(String key, String value)
	{
		emailURL.append(key).append("=").append(value).append("&");
	}
}
