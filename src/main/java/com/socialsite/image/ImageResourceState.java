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

package com.socialsite.image;

import org.apache.wicket.markup.html.DynamicWebResource.ResourceState;
import org.apache.wicket.util.time.Time;

/**
 * TODO supply the lastModified date
 * 
 * @author Ananth
 * 
 */
class ImageResourceState extends ResourceState
{
	// CONSTRUCTORS

	private String contentType;

	// MEMBERS

	private byte[] data;

	private Time lastModified;

	ImageResourceState(final Time lastModified)
	{
		super();
		this.lastModified = lastModified;
	}

	@Override
	public String getContentType()
	{
		return contentType;
	}

	@Override
	public byte[] getData()
	{
		return data;
	}

	@Override
	public int getLength()
	{
		return data.length;
	}

	@Override
	public Time lastModifiedTime()
	{
		return lastModified;
	}

	void setContentType(final String contentType)
	{
		this.contentType = contentType;
	}

	void setData(final byte[] data)
	{
		this.data = data;
	}

	public void setLastModified(final Time lastModified)
	{
		this.lastModified = lastModified;
	}

	// METHODS
}