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
 * 
 * @author Ananth
 * 
 */
public class Comment implements AbstractDomain
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private Answer answer;
	/** The user who comments the answer */
	private User user;
	private Date time;
	private String text;

	public Comment()
	{
		// TODO Auto-generated constructor stub
	}

	public Answer getAnswer()
	{
		return answer;
	}

	public long getId()
	{
		return id;
	}

	public String getText()
	{
		return text;
	}

	public Date getTime()
	{
		return time;
	}

	public User getUser()
	{
		return user;
	}

	public void setAnswer(final Answer answer)
	{
		this.answer = answer;
	}

	public void setId(final long id)
	{
		this.id = id;
	}

	public void setText(final String text)
	{
		this.text = text;
	}

	public void setTime(final Date time)
	{
		this.time = time;
	}

	public void setUser(final User user)
	{
		this.user = user;
	}
}
