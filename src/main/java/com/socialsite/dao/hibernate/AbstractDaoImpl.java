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

import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;

import com.socialsite.dao.AbstractDao;

/**
 * Abstract DAO implementation for all the Domain class
 * 
 * @author Ananth
 */
public abstract class AbstractDaoImpl<T> implements AbstractDao<T>
{

	/** class of the domain */
	protected final Class<T> domainClass;
	/** Hibernate sessionFactory to access the database */
	private SessionFactory sessionFactory;

	/**
	 * constructor
	 * 
	 * @param domainClass
	 *            domainClass
	 */
	public AbstractDaoImpl(final Class<T> domainClass)
	{
		this.domainClass = domainClass;
	}

	/**
	 * helper method to find the count of item that match the filter
	 * 
	 * @param filter
	 *            filter text
	 * @param clazz
	 *            class of the item
	 * @param field
	 *            field that will be matched with the criteria
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected int count(String filter, final Class clazz, final String field)
	{
		// avoid NPE
		filter = filter == null ? "" : filter;

		final Long count = (Long)getSession().createQuery(
				"select count(u) from " + clazz.getName() + " u where " + " u." + field
						+ " like :filter ").setParameter("filter", "%" + filter + "%")
				.uniqueResult();
		return count.intValue();
	}

	/**
	 * find the number of unique rows in the given domainclass (table)
	 * 
	 * @return no of unique rows in the given domainclass (table)
	 */
	public int countAll()
	{
		final Criteria criteria = getSession().createCriteria(domainClass);
		criteria.setProjection(Projections.rowCount());
		return (Integer)criteria.uniqueResult();
	}

	/**
	 * removes the given domainclass object from the Database
	 * 
	 * @param object
	 *            object to be deleted
	 */
	public void delete(final T object)
	{
		getSession().delete(object);
	}

	/**
	 * a helper method to find the list of item matching the filter
	 * 
	 * @param filter
	 *            filter text
	 * @param first
	 *            first index of the resul
	 * @param count
	 *            count
	 * @param sortParam
	 *            sorting is done based on this property
	 * @param clazz
	 *            class of the item
	 * @param field
	 *            field that will be matched with the criteria
	 * @return list of item matching the filter text
	 */
	@SuppressWarnings("unchecked")
	protected List find(String filter, final int first, final int count, final SortParam sortParam,
			final Class clazz, final String field)
	{
		// avoids the NPE
		filter = filter == null ? "" : filter;

		final StringBuilder query = new StringBuilder();
		// set the sort parameters
		final String sortBy = "u." + sortParam.getProperty();
		final String sort = sortParam.isAscending() ? "asc" : "desc";
		// query construction
		query.append(" from ").append(clazz.getName()).append(" u where u.").append(field).append(
				" like :filter order by ").append(sortBy).append(" ").append(sort);

		return getSession().createQuery(query.toString())
				.setParameter("filter", "%" + filter + "%").setFirstResult(first).setMaxResults(
						count).list();

	}

	/**
	 * find all the rows in the table and return it as a list
	 * 
	 * @return List contains all the rows in the given domainclass (table)
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll()
	{
		final Criteria criteria = getSession().createCriteria(domainClass);
		return criteria.list();
	}

	/**
	 * get hibernate Session from SessionFactory
	 * 
	 * @return Hibernate Session
	 */
	public Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	/**
	 * getter
	 * 
	 * @return hibernate SessionFactory
	 */
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	/**
	 * load the domainclass of the given id
	 * 
	 * @param id
	 *            id of the object
	 * @return entity object or null if the id doesn't match
	 */
	@SuppressWarnings("unchecked")
	public T load(final long id)
	{
		return (T)getSession().get(domainClass, id);
	}

	/**
	 * merges the Detached object with the Session
	 * 
	 * @param object
	 *            object to be merged
	 */
	@SuppressWarnings("unchecked")
	public T merge(final T object)
	{
		return (T)getSession().merge(object);
	}

	/**
	 * saves or update the object state in the database
	 * 
	 * @param object
	 *            object to be saved
	 */
	public void save(final T object)
	{
		getSession().saveOrUpdate(object);
	}

	/**
	 * setter
	 * 
	 * @param sessionFactory
	 *            Hibernate sessionFactory
	 */
	public void setSessionFactory(final SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
}
