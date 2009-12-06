/**
 *     Copyright SocialSite (C) 2009
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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

	/**
	 * static getter for {@link SocialSiteSession}
	 * 
	 * @return current Session
	 */
	public static SocialSiteSession get()
	{
		return (SocialSiteSession) Session.get();
	}

	/** logged in user */
	private SessionUser	sessionUser;

	/**
	 * id of the user that the logged in user is visiting
	 */
	private long		userId;

	/**
	 * constructor
	 * 
	 * @param request
	 *            request object
	 */
	protected SocialSiteSession(final Request request)
	{
		super(request);
	}

	public SessionUser getSessionUser()
	{
		return sessionUser;
	}

	public long getUserId()
	{
		return userId;
	}

	public void setSessionUser(final SessionUser user)
	{
		sessionUser = user;
	}

	public void setUserId(final long userId)
	{
		this.userId = userId;
	}

}
