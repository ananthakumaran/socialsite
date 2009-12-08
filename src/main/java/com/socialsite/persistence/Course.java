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

public class Course implements AbstractDomain
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private long				id;

	private University			university;

	private Staff				staff;

	private Set<Student>		students			= new HashSet<Student>();

	public Course()
	{
	}

	public long getId()
	{
		return id;
	}

	public Staff getStaff()
	{
		return staff;
	}

	public Set<Student> getStudents()
	{
		return students;
	}

	public University getUniversity()
	{
		return university;
	}

	public void setId(final long id)
	{
		this.id = id;
	}

	public void setStaff(final Staff staff)
	{
		this.staff = staff;
	}

	public void setStudents(final Set<Student> students)
	{
		this.students = students;
	}

	public void setUniversity(final University university)
	{
		this.university = university;
	}
}
