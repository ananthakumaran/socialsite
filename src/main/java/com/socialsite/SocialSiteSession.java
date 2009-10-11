package com.socialsite;

import org.apache.wicket.Request;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;

import com.socialsite.authentication.SessionUser;

/**
 * session class of the SocialSite Application contains the information about
 * the logged in User
 * 
 * @author Ananth
 * 
 */
public class SocialSiteSession extends WebSession
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/** logged in user */
	private SessionUser			sessionUser;

	/**
	 * id of the user that the logged in user is visiting
	 */
	private long				userId;

	/**
	 * constructor
	 * 
	 * @param request
	 *            request object
	 */
	protected SocialSiteSession(Request request)
	{
		super(request);
	}

	/**
	 * static getter for {@link SocialSiteSession}
	 * 
	 * @return current Session
	 */
	public static SocialSiteSession get()
	{
		return (SocialSiteSession) Session.get();
	}

	public SessionUser getSessionUser()
	{
		return sessionUser;
	}

	public void setSessionUser(SessionUser user)
	{
		this.sessionUser = user;
	}

	public long getUserId()
	{
		return userId;
	}

	public void setUserId(long userId)
	{
		this.userId = userId;
	}

}
