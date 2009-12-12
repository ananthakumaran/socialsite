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

package com.socialsite.dao.hibernate;

import com.socialsite.dao.ProfileDao;
import com.socialsite.persistence.Profile;

/**
 * DAO implementation for Profile object
 * 
 * @author Ananth
 * 
 */
public class ProfileDaoImpl extends AbstractDaoImpl<Profile> implements ProfileDao
{
	/**
	 * constructor
	 */
	public ProfileDaoImpl()
	{
		super(Profile.class);
	}

	/**
	 * @see com.socialsite.dao.ProfileDao#getUserImage(Long)
	 */
	public byte[] getUserImage(final Long userId)
	{
		return (byte[])getSession().createQuery(
				"select image from Profile where user.id = :userid ")
				.setParameter("userid", userId).uniqueResult();
	}

	/**
	 * @see com.socialsite.dao.ProfileDao#getUserThumb(Long)
	 */
	public byte[] getUserThumb(final Long userId)
	{
		return (byte[])getSession().createQuery(
				"select thumb from Profile where user.id = :userid ")
				.setParameter("userid", userId).uniqueResult();
	}
}
