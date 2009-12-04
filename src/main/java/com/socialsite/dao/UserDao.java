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
	public List<T> findAll(String filter, int first, int count,
			SortParam sortParam);

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
	 * get the friend list of the given user
	 * 
	 * XXX Don't use the getter in the user object (use this instead)
	 * 
	 * @param user
	 *            user object
	 *@param first
	 *            first item no of the user List
	 * @param count
	 *            no of items needed in the user List
	 * @return List containig all the friends
	 */
	public List<T> getFriends(User user, int first, int count);

	/**
	 * find the count of friends
	 * 
	 * @param T
	 *            user object
	 * 
	 * @return count of friends
	 */
	public int getFriendsCount(T user);

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
