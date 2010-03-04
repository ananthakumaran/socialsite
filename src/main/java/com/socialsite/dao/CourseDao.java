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

import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;

import com.socialsite.persistence.Course;
import com.socialsite.persistence.University;

/**
 * 
 * @author Ananth
 * 
 */
public interface CourseDao extends AbstractImageDao<Course>
{
	/**
	 * find the number of unique rows in the Course (table) that matched the
	 * search text (filter)
	 * 
	 * @param filter
	 *            search text
	 * @return no of unique rows in the Course (table) that matched the search
	 *         text (filter)
	 */
	public int countAll(String filter);

	/**
	 * find the list of all Course according to the search text(filter) and
	 * orders the list according to the sortParam
	 * 
	 * @param filter
	 *            filter for course name
	 *            <p>
	 *            if filter is null all the users will be added to the list
	 * @param first
	 *            first item no of the course List
	 * @param count
	 *            no of items needed in the course List
	 * @param sortParam
	 *            used to sort the course List
	 * @return List containing the {@link University} matched the search
	 *         criteria
	 */
	public List<University> findAll(String filter, int first, int count, SortParam sortParam);


	/**
	 * get the courses
	 * 
	 * @param id
	 * @param first
	 * @param count
	 * @return
	 */
	public List<Course> getCourses(long id, int first, int count);


	/**
	 * count of courses
	 * 
	 * @param id
	 * @return
	 */
	public int getCoursesCount(long id);

}
