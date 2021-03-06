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

import com.socialsite.dao.AbstractImageDao;

/**
 * Abstract DAO implementation for all the Domain class that have images
 * 
 * @author Ananth
 */
public class AbstractImageDaoImpl<T> extends AbstractDaoImpl<T> implements AbstractImageDao<T>
{

	/**
	 * constructor
	 * 
	 * @param domainClass
	 */
	public AbstractImageDaoImpl(final Class<T> domainClass)
	{
		super(domainClass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.dao.AbstractImageDao#getImage(long)
	 */
	public byte[] getImage(final long id)
	{
		final Criteria criteria = getSession().createCriteria(domainClass);
		criteria.add(Restrictions.idEq(id));
		criteria.setProjection(Projections.property("image"));
		return (byte[])criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.dao.AbstractImageDao#getLastModifiedTime(long)
	 */
	public Date getLastModifiedTime(final long id)
	{
		final Criteria criteria = getSession().createCriteria(domainClass);
		criteria.add(Restrictions.idEq(id));
		criteria.setProjection(Projections.property("lastModified"));
		return (Date)criteria.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.dao.AbstractImageDao#getThumb(long)
	 */
	public byte[] getThumb(final long id)
	{
		final Criteria criteria = getSession().createCriteria(domainClass);
		criteria.add(Restrictions.idEq(id));
		criteria.setProjection(Projections.property("thumb"));
		return (byte[])criteria.uniqueResult();
	}
}
