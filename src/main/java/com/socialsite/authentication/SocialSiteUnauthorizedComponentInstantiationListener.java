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
public class SocialSiteUnauthorizedComponentInstantiationListener
		implements
			IUnauthorizedComponentInstantiationListener
{
	/**
	 * Redirects to the LogingPage if the user is not authorized
	 * 
	 * @param component
	 *            component which failed the authorization check
	 */
	public void onUnauthorizedInstantiation(final Component component)
	{
		throw new RestartResponseAtInterceptPageException(LoginPage.class);

	}

}
