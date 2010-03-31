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
import org.hibernate.Query;

import com.socialsite.dao.UniversityDao;
import com.socialsite.persistence.Student;
import com.socialsite.persistence.University;

/**
 * 
 * @author Ananth
 * 
 */
public class UniversityDaoImpl extends AbstractImageDaoImpl<University> implements UniversityDao
{
	/**
	 * constructor
	 */
	public UniversityDaoImpl()
	{
		super(University.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.dao.UniversityDao#countAll(java.lang.String)
	 */
	public int countAll(final String filter)
	{
		return count(filter, University.class, "name");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.dao.UniversityDao#findAll(java.lang.String, int, int,
	 * org.apache.wicket.extensions.markup.html.repeater.util.SortParam)
	 */
	@SuppressWarnings("unchecked")
	public List<University> findAll(final String filter, final int first, final int count,
			final SortParam sortParam)
	{
		return find(filter, first, count, sortParam, University.class, "name");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.dao.UniversityDao#getStudents(long)
	 */
	@SuppressWarnings("unchecked")
	public List<Student> getStudents(long id)
	{
		Query query = getSession().createQuery(
				"select distinct s " + " from University u  " + " inner join u.courses as c "
						+ " inner join c.students as s where u.id = :id").setParameter("id", id);
		return query.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.dao.UniversityDao#getStudents(long, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<Student> getStudents(long id, int first, int count)
	{
		Query query = getSession().createQuery(
				"select distinct s " + " from University u  " + " inner join u.courses as c "
						+ " inner join c.students as s where u.id = :id").setParameter("id", id)
				.setFirstResult(first).setMaxResults(count);
		return query.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.dao.UniversityDao#getStudentsCount(long)
	 */
	public int getStudentsCount(long id)
	{
		Query query = getSession().createQuery(
				"select  count(distinct s) " + " from University u  "
						+ " inner join u.courses as c "
						+ " inner join c.students as s where u.id = :id").setParameter("id", id);
		return ((Long)query.uniqueResult()).intValue();
	}

}
