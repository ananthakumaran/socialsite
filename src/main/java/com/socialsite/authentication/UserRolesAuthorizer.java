package com.socialsite.authentication;

import org.apache.wicket.authorization.strategies.role.IRoleCheckingStrategy;
import org.apache.wicket.authorization.strategies.role.Roles;

import com.socialsite.SocialSiteSession;

/**
 * 
 * authorizer for the socialsite application
 * 
 * @author Ananth
 * 
 */
public class UserRolesAuthorizer implements IRoleCheckingStrategy
{

	/**
	 * constructor
	 */
	public UserRolesAuthorizer()
	{

	}

	/**
	 * @see org.apache.wicket.authorization.strategies.role.IRoleCheckingStrategy#hasAnyRole(SocialSiteRoles)
	 */
	public boolean hasAnyRole(final Roles roles)
	{
		final SessionUser sessionUser = SocialSiteSession.get()
			.getSessionUser();
		if (sessionUser != null)
		{
			// check whether he has the role
			return sessionUser.hasAnyRole(roles);
		}
		// not logged in
		return false;
	}

}
