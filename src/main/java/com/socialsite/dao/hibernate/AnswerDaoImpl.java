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

import com.socialsite.dao.AnswerDao;
import com.socialsite.persistence.Answer;

/**
 * 
 * @author Ananth
 * 
 */
public class AnswerDaoImpl extends AbstractDaoImpl<Answer> implements AnswerDao
{
	/**
	 * contructor
	 */
	public AnswerDaoImpl()
	{
		super(Answer.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.dao.AnswerDao#getAnswers(long, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<Answer> getAnswers(long questionId, int first, int count)
	{
		final Criteria criteria = getSession().createCriteria(domainClass);
		criteria.add(Restrictions.eq("question.id", questionId));
		criteria.setFirstResult(first);
		criteria.setMaxResults(count);

		// order by date
		criteria.addOrder(Order.desc("time"));
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.dao.AnswerDao#getAnswersCount(long)
	 */
	public int getAnswersCount(long questionId)
	{
		final Criteria criteria = getSession().createCriteria(domainClass);
		criteria.add(Restrictions.eq("question.id", questionId));
		criteria.setProjection(Projections.rowCount());
		return (Integer)criteria.uniqueResult();
	}

}
