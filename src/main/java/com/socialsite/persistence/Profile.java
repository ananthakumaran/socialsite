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

/**
 * @author Ananth
 * 
 */
public class Profile implements AbstractDomain
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private User user;
	private String firstName;
	private String lastName;
	private String email;
	private byte[] image;
	private byte[] thumb;
	private Date lastModified;

	/**
	 * TODO add other fields link address , birthday etc;
	 */

	public Profile()
	{
		setLastModified(new Date());
	}

	public String getEmail()
	{
		return email;
	}

	public Date getLastModified()
	{
		return lastModified;
	}

	public void setLastModified(Date lastModified)
	{
		this.lastModified = lastModified;
	}

	public String getFirstName()
	{
		return firstName;
	}

	/***** accessor methods ****/
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.persistence.AbstractDomain#getId()
	 */
	public long getId()
	{
		return id;
	}

	public byte[] getImage()
	{
		return image;
	}

	public String getLastName()
	{
		return lastName;
	}

	public byte[] getThumb()
	{
		return thumb;
	}

	public User getUser()
	{
		return user;
	}

	public void setEmail(final String email)
	{
		this.email = email;
	}

	public void setFirstName(final String firstName)
	{
		this.firstName = firstName;
	}

	public void setId(final long id)
	{
		this.id = id;
	}

	public void setImage(final byte[] image)
	{
		this.image = image;
		setLastModified(new Date());
	}

	public void setLastName(final String lastName)
	{
		this.lastName = lastName;
	}

	public void setThumb(final byte[] thumb)
	{
		this.thumb = thumb;
		setLastModified(new Date());
	}

	public void setUser(final User user)
	{
		this.user = user;
	}
}
