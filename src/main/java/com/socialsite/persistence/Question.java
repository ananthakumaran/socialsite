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
public class Question implements AbstractDomain
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private String heading;
	private String text;
	/** The course where the question is asked */
	private Course course;
	/** The user who asks the question */
	private User user;
	private Set<Answer> answers = new HashSet<Answer>();
	private Date time;

	public Question()
	{
	}

	public Question(final String heading, final String text)
	{
		setHeading(heading);
		setText(text);
		setTime(new Date());
	}

	public Question(final String heading, final Course course, final User user)
	{
		setHeading(heading);
		setCourse(course);
		setUser(user);
		setTime(new Date());
	}

	public void addAnswer(Answer answer)
	{
		getAnswers().add(answer);
		answer.setQuestion(this);
	}

	public Set<Answer> getAnswers()
	{
		return answers;
	}

	public Course getCourse()
	{
		return course;
	}

	public String getHeading()
	{
		return heading;
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

	public void setAnswers(final Set<Answer> answers)
	{
		this.answers = answers;
	}

	public void setCourse(final Course course)
	{
		this.course = course;
	}

	public void setHeading(final String heading)
	{
		this.heading = heading;
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
