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

package com.socialsite.persistence;

import java.util.HashSet;
import java.util.Set;

public class Student extends User
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Set<Course> courses = new HashSet<Course>();

	public Student()
	{
	}

	public Student(final String userName, final String password)
	{
		super(userName, password);
	}

	public Set<Course> getCourses()
	{
		return courses;
	}

	public void setCourses(final Set<Course> courses)
	{
		this.courses = courses;
	}

}
