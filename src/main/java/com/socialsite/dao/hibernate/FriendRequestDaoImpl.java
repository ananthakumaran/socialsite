package com.socialsite.dao.hibernate;

import com.socialsite.dao.FriendRequestDao;
import com.socialsite.persistence.FriendRequest;

/**
 * Dao implmentation for friendRequest
 * 
 * @author Ananth
 * 
 */
public class FriendRequestDaoImpl extends AbstractDaoImpl<FriendRequest>
		implements FriendRequestDao
{
	/**
	 * constructor
	 */
	public FriendRequestDaoImpl()
	{
		super(FriendRequest.class);
	}

	/**
	 * @see com.socialsite.dao.FriendRequestDao#hasFriendRequest(long, long)
	 */
	public boolean hasFriendRequest(final long userId, final long friendId)
	{
		final FriendRequest friendRequest = (FriendRequest) getSession()
			.createQuery(
				" from FriendRequest f where (f.user.id = :userId and f.friend.id = :friendId)"
						+ " or (f.user.id = :friendId and f.friend.id = :userId) ")
			.setParameter("userId", userId).setParameter("friendId", friendId)
			.uniqueResult();
		return friendRequest != null;
	}
}
