package com.socialsite.dao.hibernate;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.socialsite.dao.ScrapDao;
import com.socialsite.persistence.Scrap;
import com.socialsite.persistence.User;

/**
 * DAO implementation for Scrap object
 * 
 * @author Ananth
 * 
 */
public class ScrapDaoImpl extends AbstractDaoImpl<Scrap> implements ScrapDao
{
	/**
	 * constructor
	 */
	public ScrapDaoImpl()
	{
		super(Scrap.class);
	}

	/**
	 * @see com.socialsite.dao.ScrapDao#getScraps(User, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<Scrap> getScraps(User user, int first, int count)
	{

		return (List<Scrap>) getSession().createCriteria(Scrap.class).add(
			Restrictions.eq("receiver", user)).addOrder(Order.desc("time"))
			.setFirstResult(first).setMaxResults(count).list();

	}

	/**
	 * @see com.socialsite.dao.ScrapDao#countAll()
	 */
	public int getScrapsCount(User user)
	{
		return (Integer) getSession().createCriteria(Scrap.class).add(
			Restrictions.eq("receiver", user)).setProjection(
			Projections.rowCount()).uniqueResult();
	}

}
