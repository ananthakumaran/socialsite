package com.socialsite.authentication;

import org.apache.wicket.authorization.strategies.role.IRoleCheckingStrategy;
import org.apache.wicket.authorization.strategies.role.RoleAuthorizationStrategy;

/**
 * authorization strategy of the application
 * 
 * @author Ananth
 * 
 */
public final class SocialSiteAuthorizationStrategy extends
		RoleAuthorizationStrategy {

	public SocialSiteAuthorizationStrategy(
			IRoleCheckingStrategy roleCheckingStrategy) {
		super(roleCheckingStrategy);

	}

}
