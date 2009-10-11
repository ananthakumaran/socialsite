/**
 * 
 */
package com.socialsite.dao;

import com.socialsite.persistence.FriendRequest;

/**
 * DAO for the FriendRequestObject
 * 
 * @author Ananth
 * 
 */
public interface FriendRequestDao extends AbstractDao<FriendRequest>
{

	/**
	 * check for a friendrequest by the user to the friend
	 * 
	 * @param userId
	 *            user id
	 * @param friendId
	 *            friend id
	 * @return returns true if there is a friendrequest else false
	 */
	public boolean hasFriendRequest(long userId , long friendId);
	
}
