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

import org.apache.wicket.authorization.strategies.role.Roles;

/**
 * holds the list of roles
 * 
 * @author Ananth
 * 
 */
public class SocialSiteRoles
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String USER = "USER";
	public static final String FRIEND = "FRIEND";
	public static final String OWNER = "OWNER";

	public static final Roles userRole = new Roles(USER);
	public static final Roles friendRole = new Roles(FRIEND);
	public static final Roles ownerRole = new Roles(OWNER);

}
