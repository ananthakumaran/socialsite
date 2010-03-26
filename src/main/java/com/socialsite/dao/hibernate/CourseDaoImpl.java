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

import java.util.ArrayList;
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
	 * @see com.socialsite.dao.CourseDao#findAll(java.lang.String, int, int,
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
	 * @see com.socialsite.dao.CourseDao#getCourses(int, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<Course> getCourses(final long id, final int first, final int count)
	{
		final List<Course> course = new ArrayList<Course>();

		final Query universityQuery = getSession().createQuery(
				"select u.courses from University  u  where u.id = :id ").setParameter("id", id)
				.setFirstResult(first).setMaxResults(count);
		course.addAll(universityQuery.list());

		final Query studentQuery = getStudentQuery(id).setFirstResult(first).setMaxResults(count);
		course.addAll(studentQuery.list());
		return course;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.dao.CourseDao#getCoursesCount(long)
	 */
	public int getCoursesCount(final long id)
	{

		int count = 0;

		// TODO don't know why count(u.courses) is not working
		final Query universityQuery = getSession().createQuery(
				"select u.courses from University as u  where u.id = :id ").setParameter("id", id);

		count += universityQuery.list().size();

		count += getStudentQuery(id).list().size();

		return count;
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


	/**
	 * helper
	 * 
	 * @param id
	 *            student id
	 * @return
	 */
	private Query getStudentQuery(long id)
	{
		return getSession().createQuery("select  s.courses from Student as  s  where s.id = :id ")
				.setParameter("id", id);
	}
}
