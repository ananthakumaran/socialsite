package com.socialsite.dao.hibernate;

import java.math.BigInteger;
import java.util.List;

import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.socialsite.authentication.SocialSiteRoles;
import com.socialsite.dao.UserDao;
import com.socialsite.persistence.User;

/**
 * DAO implementation for User object
 * 
 * @author Ananth
 * 
 */
public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao
{
	/**
	 * constructor
	 */
	public UserDaoImpl()
	{
		super(User.class);
	}

	/**
	 * @see com.socialsite.dao.UserDao#checkUserStatus(String, String)
	 */
	public User checkUserStatus(String userName, String password)
	{
		final Criteria criteria = getSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("userName", userName));
		final User user = (User) criteria.uniqueResult();

		if (user != null && user.matchPassword(password))
		{
			return user;
		}
		return null;
	}

	/**
	 * @see com.socialsite.dao.UserDao#getFriends(User)
	 */
	@SuppressWarnings("unchecked")
	public List<User> getFriends(User user)
	{
		return (List<User>) getSession()
			.createSQLQuery(
				" select {u.*} from user {u} where u.id in  (select user_id from friend_reference "
						+ " where friend_id = :id) or u.id in ( select friend_id from friend_reference where user_id = :id)  ")
			.addEntity("u", User.class).setParameter("id", user.getId()).list();
	}

	/**
	 * @see com.socialsite.dao.UserDao#getFriends(User, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<User> getFriends(User user, int first, int count)
	{

		return (List<User>) getSession()
			.createSQLQuery(
				" select {u.*} from user {u} where u.id in  (select user_id from friend_reference "
						+ " where friend_id = :id) or u.id in ( select friend_id from friend_reference where user_id = :id)  ")
			.addEntity("u", User.class).setParameter("id", user.getId())
			.setFirstResult(first).setMaxResults(count).list();
	}

	/**
	 * @see com.socialsite.dao.UserDao#getFriendsCount()
	 */
	public int getFriendsCount(User user)
	{
		final BigInteger count = (BigInteger) getSession()
			.createSQLQuery(
				" select count(*) from friend_reference where user_id=:id or friend_id= :id ")
			.setParameter("id", user.getId()).uniqueResult();

		return count.intValue();
	}

	/**
	 * @see com.socialsite.dao.UserDao#findAll(String, int, int, SortParam)
	 */
	@SuppressWarnings("unchecked")
	public List<User> findAll(String filter, int first, int count,
			SortParam sortParam)
	{

		filter = filter == null ? "" : filter;

		final StringBuilder query = new StringBuilder();

		// set the sort parameters
		final String sortBy = "u." + sortParam.getProperty();
		final String sort = sortParam.isAscending() ? "asc" : "desc";

		query.append(" from User u where u.userName like :filter order by ")
			.append(sortBy).append(" ").append(sort);

		return getSession().createQuery(query.toString()).setParameter(
			"filter", "%" + filter + "%").setFirstResult(first).setMaxResults(
			count).list();
	}

	/**
	 * @see com.socialsite.dao.UserDao#countAll(String)
	 */
	public int countAll(String filter)
	{
		filter = filter == null ? "" : filter;

		final Long count = (Long) getSession().createQuery(
			"select count(u) from User u where " + " u.userName like :filter ")
			.setParameter("filter", "%" + filter + "%").uniqueResult();

		return count.intValue();
	}

	/**
	 * @see com.socialsite.dao.UserDao#getUsersRelation(long, long)
	 */
	public Roles getUsersRelation(long id1, long id2)
	{
		// owner
		if (id1 == id2)
		{
			return SocialSiteRoles.ownerRole;
		}

		final BigInteger count = (BigInteger) getSession()
			.createSQLQuery(
				"select count(*) from friend_reference where"
						+ " (friend_id = :id1 and user_id = :id2) or (user_id = :id1 and friend_id = :id2)   ")
			.setParameter("id1", id1).setParameter("id2", id2).uniqueResult();
		// friend
		if (count.intValue() == 1)
		{
			return SocialSiteRoles.friendRole;
		} else
		{ // unknown
			return SocialSiteRoles.userRole;
		}
	}

}
