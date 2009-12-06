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
 * Scrap Details .This is the business object that we persist to the Database
 * 
 * @author Ananth
 * 
 */
public class Scrap implements AbstractDomain
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private long				id;
	private User				author;
	private User				receiver;
	private Date				time;
	private String				message;

	public User getAuthor()
	{
		return author;
	}

	public long getId()
	{
		return id;
	}

	public String getMessage()
	{
		return message;
	}

	public User getReceiver()
	{
		return receiver;
	}

	public Date getTime()
	{
		return time;
	}

	public void setAuthor(final User author)
	{
		this.author = author;
	}

	public void setId(final long id)
	{
		this.id = id;
	}

	public void setMessage(final String message)
	{
		this.message = message;
	}

	public void setReceiver(final User receiver)
	{
		this.receiver = receiver;
	}

	public void setTime(final Date time)
	{
		this.time = time;
	}

}
