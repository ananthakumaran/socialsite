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

import java.util.List;

import org.hibernate.Query;

import com.socialsite.dao.MessageDao;
import com.socialsite.persistence.Message;
import com.socialsite.persistence.User;

public class MessageDaoImpl<T extends Message> extends AbstractDaoImpl<T> implements MessageDao<T>
{
	public MessageDaoImpl(final Class<T> clazz)
	{
		super(clazz);
	}

	/**
	 * @see com.socialsite.dao.MessageDao#getMessage(User, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<T> getMessage(final User user, final int first, final int count)
	{
		final Query query = getSession().createQuery(
				" select m from Message m , User u "
						+ " where u = :user and u member of m.users order by m.time desc");
		query.setParameter("user", user);
		query.setFirstResult(first);
		query.setMaxResults(count);
		return query.list();
	}

	/**
	 * @see com.socialsite.dao.MessageDao#getMessageCount(User)
	 */
	public int getMessageCount(final User user)
	{
		final Query query = getSession().createQuery(
				" select count(m) from Message m , User u "
						+ " where u = :user and u member of m.users ");
		query.setParameter("user", user);

		final Long result = (Long)query.uniqueResult();

		return result.intValue();
	}

}
