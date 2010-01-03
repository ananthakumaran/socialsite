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

/**
 * 
 * @author Ananth
 * 
 */
public class Answer implements AbstractDomain
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	/** The user who answered the question */
	private User user;
	private Question question;
	private Set<Comment> comments = new HashSet<Comment>();
	private Date time;
	private String text;

	public Answer()
	{
	}

	public Answer(final Question question, final User user)
	{
		setQuestion(question);
		setUser(user);
		setTime(new Date());
	}

	public Set<Comment> getComments()
	{
		return comments;
	}

	public long getId()
	{
		return id;
	}

	public Question getQuestion()
	{
		return question;
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

	public void setComments(final Set<Comment> comments)
	{
		this.comments = comments;
	}

	public void setId(final long id)
	{
		this.id = id;
	}

	public void setQuestion(final Question question)
	{
		this.question = question;
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
