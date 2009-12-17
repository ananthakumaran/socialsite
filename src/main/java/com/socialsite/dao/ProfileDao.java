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

package com.socialsite.dao;

import java.util.Date;

import com.socialsite.persistence.Profile;

/**
 * DAO for the User object
 * 
 * @author Ananth
 */
public interface ProfileDao extends AbstractDao<Profile>
{

	/**
	 * get the userImage of the user
	 * 
	 * @param userId
	 *            user id
	 * @return byte[] contains the userImage
	 */
	public byte[] getUserImage(long userId);

	/**
	 * get the thumbnail userImage of the user
	 * 
	 * @param userId
	 *            user id
	 * @return byte[] thumbnail contains the userImage
	 */
	public byte[] getUserThumb(long userId);

	/**
	 * returns the last modified date of the 
	 * user image
	 * @param id
	 * @return
	 */
	public Date getLastModifiedTime(final long id);
}
