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

public class StaffRequestMsg extends Message
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The sender who sends the staff request message */
	private Staff sender;
	private University university;

	public StaffRequestMsg()
	{
	}

	public StaffRequestMsg(Staff sender)
	{
		this.sender = sender;
	}

	public Staff getSender()
	{
		return sender;
	}

	public void setSender(Staff sender)
	{
		this.sender = sender;
	}

	public University getUniversity()
	{
		return university;
	}

	public void setUniversity(University university)
	{
		this.university = university;
	}

}
