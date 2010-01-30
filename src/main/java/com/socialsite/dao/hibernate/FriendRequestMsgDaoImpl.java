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
import com.socialsite.persistence.Student;
import com.socialsite.persistence.User;

/**
 * 
 * @author Ananth
 * 
 */
public class FriendRequestMsgDaoImpl extends MessageDaoImpl<FriendRequestMsg>
		implements
			FriendRequestMsgDao
{

	public FriendRequestMsgDaoImpl()
	{
		super(FriendRequestMsg.class);
	}

	public boolean hasFriendRequest(final long userid, final long guestid)
	{
		final User user = new Student();
		user.setId(userid);

		final User guest = new Student();
		guest.setId(guestid);

		final Query q = getSession()
				.createQuery(
						" select count(*) from FriendRequestMsg m where (m.sender.id = :guestid "
								+ " and :user member of  m.users) or (m.sender.id = :userid and :guest member of m.users) ");
		q.setParameter("userid", userid);
		q.setParameter("guestid", guestid);
		q.setParameter("user", user);
		q.setParameter("guest", guest);

		final long result = (Long)q.uniqueResult();

		return result == 0 ? false : true;

	}
}
