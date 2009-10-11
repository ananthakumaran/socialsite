package com.socialsite.authentication;

import org.apache.wicket.Component;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener;

/**
 * unathorized components will be handled here
 * 
 * @author Ananth
 * 
 */
public class SocialSiteUnauthorizedComponentInstantiationListener implements
		IUnauthorizedComponentInstantiationListener {
	/**
	 * Redirects to the LogingPage if the user is not authorized
	 * 
	 * @param component
	 *            component which failed the authorization check
	 */
	public void onUnauthorizedInstantiation(Component component) {
		throw new RestartResponseAtInterceptPageException(LoginPage.class);

	}

}
