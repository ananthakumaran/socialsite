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

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.socialsite.dao.CommentDao;
import com.socialsite.persistence.Comment;

/**
 * 
 * @author Ananth
 * 
 */
public class CommentDaoImpl extends AbstractDaoImpl<Comment> implements CommentDao
{

	public CommentDaoImpl()
	{
		super(Comment.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.dao.CommentDao#getComments(long, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<Comment> getComments(final long answerId, final int first, final int count)
	{
		final Criteria criteria = getSession().createCriteria(domainClass);
		criteria.add(Restrictions.eq("answer.id", answerId));
		criteria.setFirstResult(first);
		criteria.setMaxResults(count);

		// order by date
		criteria.addOrder(Order.desc("time"));
		return criteria.list();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.dao.CommentDao#getCommentsCount(long)
	 */
	public int getCommentsCount(final long answerId)
	{
		final Criteria criteria = getSession().createCriteria(domainClass);
		criteria.add(Restrictions.eq("answer.id", answerId));
		criteria.setProjection(Projections.rowCount());
		return (Integer)criteria.uniqueResult();
	}

}
