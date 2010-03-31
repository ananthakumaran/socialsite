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

import com.socialsite.dao.CourseDao;
import com.socialsite.persistence.Course;
import com.socialsite.persistence.University;
import com.socialsite.persistence.User;

/**
 * 
 * @author Ananth
 * 
 */
public class CourseDaoImpl extends AbstractImageDaoImpl<Course> implements CourseDao
{

	/**
	 * constructor
	 */
	public CourseDaoImpl()
	{
		super(Course.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.dao.CourseDao#countAll(java.lang.String)
	 */
	public int countAll(final String filter)
	{
		return count(filter, Course.class, "name");
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.dao.CourseDao#findAll(java.lang.String, int, int, p
	 *      org.apache.wicket.extensions.markup.html.repeater.util.SortParam)
	 */
	@SuppressWarnings("unchecked")
	public List<University> findAll(final String filter, final int first, final int count,
			final SortParam sortParam)
	{
		return find(filter, first, count, sortParam, Course.class, "name");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.dao.CourseDao#getUserCourses(long, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<Course> getUserCourses(final long id, final int first, final int count)
	{
		return getUserQuery(id).setFirstResult(first).setMaxResults(count).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.dao.CourseDao#getUserCoursesCount(long)
	 */
	public int getUserCoursesCount(final long id)
	{
		return getUserQuery(id).list().size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.dao.CourseDao#getStudents(long, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<User> getStudents(long id, int first, int count)
	{
		final Query studentQuery = getSession().createQuery(
				"select c.students from Course c where c.id = :id ").setParameter("id", id)
				.setFirstResult(first).setMaxResults(count);
		return studentQuery.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.dao.CourseDao#getStudentsCount(long)
	 */
	public int getStudentsCount(long id)
	{
		final Query studentQuery = getSession().createQuery(
				"select c.students from Course c where c.id = :id ").setParameter("id", id);
		return studentQuery.list().size();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.dao.CourseDao#getUniversityCourses(long, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<Course> getUniversityCourses(long id, int first, int count)
	{
		return getUniversityQuery(id).setFirstResult(first).setMaxResults(count).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.dao.CourseDao#getUniversityCoursesCount(long)
	 */
	public int getUniversityCoursesCount(long id)
	{
		return getUniversityQuery(id).list().size();

	}

	/**
	 * helper
	 * 
	 * @param id
	 *            student id
	 * @return
	 */
	private Query getUserQuery(long id)
	{
		return getSession().createQuery("select  s.courses from Student as  s  where s.id = :id ")
				.setParameter("id", id);
	}

	/**
	 * helper
	 * 
	 * @param id
	 *            university id
	 * @return
	 */
	private Query getUniversityQuery(long id)
	{
		return getSession().createQuery("select u.courses from University as u where u.id = :id ")
				.setParameter("id", id);
	}
}
