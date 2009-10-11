package com.socialsite.authentication;

import org.apache.wicket.markup.html.WebPage;

/**
 * logout page for the application
 * 
 * @author Ananth
 */
public final class LogoutPage extends WebPage
{

	/**
	 * constructor
	 */
	public LogoutPage()
	{
		super();

		// invalidates the session
		getSession().invalidate();
		// redirects the user to login page
		setResponsePage(LoginPage.class);
	}
}
