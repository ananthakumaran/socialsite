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

import com.socialsite.dao.CourseDao;
import com.socialsite.persistence.Course;
import com.socialsite.persistence.University;

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

}
