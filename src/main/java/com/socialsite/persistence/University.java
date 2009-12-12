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

public class University implements AbstractDomain
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private byte[] image;
	private byte[] thumb;

	private Admin admin;

	private Set<Staff> staffs = new HashSet<Staff>();

	private Set<Course> courses = new HashSet<Course>();

	public University()
	{
	}

	public Admin getAdmin()
	{
		return admin;
	}

	public Set<Course> getCourses()
	{
		return courses;
	}

	public byte[] getImage()
	{
		return image;
	}

	public void setImage(byte[] image)
	{
		this.image = image;
	}

	public byte[] getThumb()
	{
		return thumb;
	}

	public void setThumb(byte[] thumb)
	{
		this.thumb = thumb;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public long getId()
	{
		return id;
	}

	public Set<Staff> getStaffs()
	{
		return staffs;
	}

	public void setAdmin(final Admin admin)
	{
		this.admin = admin;
	}

	public void setCourses(final Set<Course> courses)
	{
		this.courses = courses;
	}

	public void setId(final long id)
	{
		this.id = id;
	}

	public void setStaffs(final Set<Staff> staffs)
	{
		this.staffs = staffs;
	}
}
