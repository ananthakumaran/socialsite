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

import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;

import com.socialsite.persistence.User;

/**
 * DAO for the User object
 * 
 * @author Ananth
 * @param <T>
 */
public interface UserDao<T extends User> extends AbstractDao<T>
{
	/**
	 * compare the user details in the database and find the user
	 * 
	 * @param userName
	 *            user name
	 * @param password
	 *            password
	 * @return User object or null if the user with the given details doesn't
	 *         exists
	 */
	public T checkUserStatus(String userName, String password);

	/**
	 * find the number of unique rows in the User (table) that matched the
	 * search text (filter)
	 * 
	 * @param filter
	 *            search text
	 * @return no of unique rows in the User (table) that matched the search
	 *         text (filter)
	 */
	public int countAll(String filter);

	/**
	 * find the list of all user according to the search text(filter) and orders
	 * the list according to the sortParam
	 * 
	 * @param filter
	 *            filter for username
	 *            <p>
	 *            if filter is null all the users will be added to the list
	 * @param first
	 *            first item no of the user List
	 * @param count
	 *            no of items needed in the user List
	 * @param sortParam
	 *            used to sort the user List
	 * @return List containing the {@link User} matched the search criteriaF
	 */
	public List<T> findAll(String filter, int first, int count, SortParam sortParam);

	/**
	 * get the friend list of the given user
	 * 
	 * XXX Don't use the getter in the user object (use this instead)
	 * 
	 * @param userId
	 *            user id
	 *@param first
	 *            first item no of the user List
	 * @param count
	 *            no of items needed in the user List
	 * @return List containig all the friends
	 */
	public List<T> getFriends(long userId, int first, int count);

	/**
	 * get the friend list of the given user
	 * 
	 * XXX Don't use the getter in the user object (use this instead)
	 * 
	 * @param T
	 *            user object
	 * @return List containig all the friends
	 */
	public List<T> getFriends(T user);

	/**
	 * find the count of friends
	 * 
	 * @param userId
	 *            user id
	 * 
	 * @return count of friends
	 */
	public int getFriendsCount(long userId);

	/**
	 * find the relationship between two users
	 * 
	 * @param id1
	 *            id of user one
	 * @param id2
	 *            id of user two
	 * @return relationship between the two user in roles
	 * 
	 * @see com.socialsite.authentication.SocialSiteRoles
	 */
	public Roles getUsersRelation(long id1, long id2);

}
