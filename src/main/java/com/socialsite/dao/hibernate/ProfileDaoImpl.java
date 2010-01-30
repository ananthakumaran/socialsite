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

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.socialsite.dao.ProfileDao;
import com.socialsite.persistence.Profile;
import com.socialsite.persistence.User;

/**
 * DAO implementation for Profile object
 * 
 * @author Ananth
 * 
 */
public class ProfileDaoImpl extends AbstractImageDaoImpl<Profile> implements ProfileDao
{

	/**
	 * constructor
	 */
	public ProfileDaoImpl()
	{
		super(Profile.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.socialsite.dao.hibernate.AbstractImageDaoImpl#getLastModifiedTime
	 * (long)
	 */
	@Override
	public Date getLastModifiedTime(final long id)
	{
		final Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.idEq(id));
		criteria.setProjection(Projections.property("lastModified"));
		return (Date)criteria.uniqueResult();
	}
}
