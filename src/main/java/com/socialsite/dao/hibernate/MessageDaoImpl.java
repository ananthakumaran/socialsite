package com.socialsite.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.socialsite.dao.MessageDao;
import com.socialsite.persistence.Message;
import com.socialsite.persistence.User;

public class MessageDaoImpl<T extends Message> extends AbstractDaoImpl<T>
		implements MessageDao<T>
{
	public MessageDaoImpl(final Class<T> clazz)
	{
		super(clazz);
	}

	/**
	 * @see com.socialsite.dao.MessageDao#getMessage(User, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<T> getMessage(User user, int first, int count)
	{
		final Criteria criteria = getSession().createCriteria(domainClass);
		criteria.add(Restrictions.eq("user", user));
		criteria.setFirstResult(first);
		criteria.setMaxResults(count);
		return criteria.list();
	}

	/**
	 * @see com.socialsite.dao.MessageDao#getMessageCount(User)
	 */
	public int getMessageCount(User user)
	{
		final Criteria criteria = getSession().createCriteria(domainClass);
		criteria.add(Restrictions.eq("user", user));
		criteria.setProjection(Projections.rowCount());
		
		return (Integer)criteria.uniqueResult();
	}

}
