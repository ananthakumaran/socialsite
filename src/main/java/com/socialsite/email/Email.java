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

package com.socialsite.email;

import java.util.ArrayList;
import java.util.List;

/**
 * email
 * 
 * @author Ananth
 * 
 */
public class Email
{

	private String sender;
	private List<String> receivers = new ArrayList<String>();
	private String message;
	private String subject;

	public Email()
	{
	}

	public void addReceivers(final String receiver)
	{
		receivers.add(receiver);
	}

	public String getMessage()
	{
		return message;
	}

	public List<String> getReceivers()
	{
		return receivers;
	}


	public String getSender()
	{
		return sender;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setMessage(final String message)
	{
		this.message = message;
	}

	public void setReceivers(final List<String> receivers)
	{
		this.receivers = receivers;
	}

	public void setSender(final String sender)
	{
		this.sender = sender;
	}

	public void setSubject(final String subject)
	{
		this.subject = subject;
	}
}
