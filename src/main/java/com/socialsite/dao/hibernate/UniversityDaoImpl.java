package com.socialsite.dao.hibernate;

import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;

import com.socialsite.dao.UniversityDao;
import com.socialsite.persistence.University;

/**
 * 
 * @author Ananth
 * 
 */
public class UniversityDaoImpl extends AbstractDaoImpl<University> implements UniversityDao
{
	public UniversityDaoImpl()
	{
		super(University.class);
	}

	/**
	 * @see com.socialsite.dao.UniversityDao#countAll(String)
	 */
	public int countAll(String filter)
	{
		filter = filter == null ? "" : filter;

		final Long count = (Long)getSession().createQuery(
				"select count(u) from University u where " + " u.name like :filter ").setParameter(
				"filter", "%" + filter + "%").uniqueResult();

		return count.intValue();
	}

	/**
	 * @see com.socialsite.dao.UniversityDao#findAll(String, int, int,
	 *      SortParam)
	 */
	@SuppressWarnings("unchecked")
	public List<University> findAll(String filter, final int first, final int count,
			final SortParam sortParam)
	{

		filter = filter == null ? "" : filter;

		final StringBuilder query = new StringBuilder();

		// set the sort parameters
		final String sortBy = "u." + sortParam.getProperty();
		final String sort = sortParam.isAscending() ? "asc" : "desc";

		query.append(" from University u where u.name like :filter order by ").append(sortBy)
				.append(" ").append(sort);

		return getSession().createQuery(query.toString())
				.setParameter("filter", "%" + filter + "%").setFirstResult(first).setMaxResults(
						count).list();
	}
}
