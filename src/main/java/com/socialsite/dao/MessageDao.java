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

package com.socialsite.dao;

import java.util.List;

import com.socialsite.persistence.User;

public interface MessageDao<T> extends AbstractDao<T>
{

	/**
	 * get the list of message for the user
	 * 
	 * @param user
	 *            user object
	 * @param first
	 *            first item index of the result
	 * @param count
	 *            no of item
	 * @return List of messages
	 */
	public List<T> getMessage(User user, int first, int count);

	/**
	 * gets the no of messages
	 * 
	 * @param user
	 *            user object
	 * @return no of message for the user
	 */
	public int getMessageCount(User user);

}
