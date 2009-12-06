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

package com.socialsite.dao.hibernate;

import org.hibernate.Query;

import com.socialsite.dao.FriendRequestMsgDao;
import com.socialsite.persistence.FriendRequestMsg;

public class FriendRequestMsgDaoImpl extends MessageDaoImpl<FriendRequestMsg>
		implements FriendRequestMsgDao
{

	public FriendRequestMsgDaoImpl()
	{
		super(FriendRequestMsg.class);
	}

	public boolean hasFriendRequest(long userid, long guestid)
	{
		Query q = getSession().createQuery(
			" select count(*) from FriendRequestMsg m where (m.sender.id = :guestid "
					+ " and m.user.id = :userid) or (m.sender.id = :userid and m.user.id = :guestid) ");
		q.setParameter("userid", userid);
		q.setParameter("guestid", guestid);

		long result = (Long) q.uniqueResult();

		return result == 0 ? false : true;

	}
}
