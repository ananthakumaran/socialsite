package com.socialsite.authentication;

import org.apache.wicket.authorization.strategies.role.IRoleCheckingStrategy;
import org.apache.wicket.authorization.strategies.role.Roles;

import com.socialsite.SocialSiteSession;

/**
 * 
 * @author Ananth
 * 
 */
public class UserRolesAuthorizer implements IRoleCheckingStrategy {

	/**
	 * constructor
	 */
	public UserRolesAuthorizer() {

	}

	/**
	 * @see org.apache.wicket.authorization.strategies.role.IRoleCheckingStrategy#hasAnyRole(SocialSiteRoles)
	 */
	public boolean hasAnyRole(Roles roles) {
		return SocialSiteSession.get().getSessionUser().hasAnyRole(roles);
	}

}
