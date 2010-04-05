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

public class Note implements AbstractDomain
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private byte[] data;
	private String description;
	private Date time;
	private Course course;
	private String fileName;
	private String contentType;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public void setData(byte[] data)
	{
		this.data = data;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public void setTime(Date time)
	{
		this.time = time;
	}

	public byte[] getData()
	{
		return data;
	}

	public String getDescription()
	{
		return description;
	}

	public Date getTime()
	{
		return time;
	}

	public void setCourse(Course course)
	{
		this.course = course;
	}

	public Course getCourse()
	{
		return course;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public void setContentType(String contentType)
	{
		this.contentType = contentType;
	}

	public String getContentType()
	{
		return contentType;
	}

}
