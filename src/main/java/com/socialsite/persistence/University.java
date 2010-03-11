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

import java.util.Date;
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
	private Date lastModified;
	private Admin admin;

	private Set<Staff> staffs = new HashSet<Staff>();

	private Set<Course> courses = new HashSet<Course>();

	public University()
	{
	}

	public University(final String name)
	{
		this(name, null);
	}

	public University(final String name, final Admin admin)
	{
		setName(name);
		setAdmin(admin);
		setLastModified(new Date());
	}

	public void addStaff(final Staff staff)
	{
		staffs.add(staff);
		staff.setUniversity(this);
	}

	/**
	 * changes the profile image
	 * 
	 * @param image
	 *            image data in byte[]
	 */
	public void changeImage(final byte[] image)
	{
		this.image = image;
		setLastModified(new Date());
	}

	/**
	 * changes the thumb
	 * 
	 * @param thumb
	 *            thumb data in byte[]
	 */
	public void changeThumb(final byte[] thumb)
	{
		this.thumb = thumb;
		setLastModified(new Date());
	}

	public Admin getAdmin()
	{
		return admin;
	}

	public Set<Course> getCourses()
	{
		return courses;
	}

	public long getId()
	{
		return id;
	}

	public byte[] getImage()
	{
		return image;
	}

	public Date getLastModified()
	{
		return lastModified;
	}

	public String getName()
	{
		return name;
	}

	public Set<Staff> getStaffs()
	{
		return staffs;
	}

	public byte[] getThumb()
	{
		return thumb;
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

	public void setImage(final byte[] image)
	{
		this.image = image;
	}

	public void setLastModified(final Date lastModified)
	{
		this.lastModified = lastModified;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	public void setStaffs(final Set<Staff> staffs)
	{
		this.staffs = staffs;
	}

	public void setThumb(final byte[] thumb)
	{
		this.thumb = thumb;
	}
}
