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

import com.socialsite.dao.QuestionDao;
import com.socialsite.persistence.Question;

/**
 * 
 * @author Ananth
 * 
 */
public class QuestionDaoImpl extends AbstractDaoImpl<Question> implements QuestionDao
{

	/**
	 * construtor
	 */
	public QuestionDaoImpl()
	{
		super(Question.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.dao.QuestionDao#getQuestions(long)
	 */
	@SuppressWarnings("unchecked")
	public List<Question> getQuestions(final long courseId, final int first, final int count)
	{
		final Criteria criteria = getSession().createCriteria(domainClass);
		criteria.add(Restrictions.eq("course.id", courseId));
		criteria.setFirstResult(first);
		criteria.setMaxResults(count);

		// order by date
		criteria.addOrder(Order.desc("time"));
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.dao.QuestionDao#getQuestionsCount(long)
	 */
	public int getQuestionsCount(final long courseId)
	{
		final Criteria criteria = getSession().createCriteria(domainClass);
		criteria.add(Restrictions.eq("course.id", courseId));
		criteria.setProjection(Projections.rowCount());
		return (Integer)criteria.uniqueResult();
	}

}
