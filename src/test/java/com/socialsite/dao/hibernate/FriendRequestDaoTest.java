package com.socialsite.dao.hibernate;

import static junit.framework.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.transaction.annotation.Transactional;

import com.socialsite.dao.AbstractDaoTest;
import com.socialsite.dao.FriendRequestDao;
import com.socialsite.dao.UserDao;
import com.socialsite.persistence.FriendRequest;
import com.socialsite.persistence.User;

/**
 * 
 * @author Ananth
 * 
 */
public class FriendRequestDaoTest extends AbstractDaoTest
{

	@Resource(name = "userDao")
	private UserDao				userDao;

	@Resource(name = "friendRequestDao")
	private FriendRequestDao	friendRequestDao;

	@Test
	@Transactional
	public void testCreate()
	{
		User ananth = new User("ananth", "pass");
		userDao.save(ananth);

		User anantha = new User("anantha", "pass");
		userDao.save(anantha);

		// ananth is sending friend request to anantha

		FriendRequest friendRequest = new FriendRequest();
		friendRequest.setFriend(anantha);
		friendRequest.setUser(ananth);

		friendRequestDao.save(friendRequest);
		// flush the session so we can get the record using JDBC template
		SessionFactoryUtils.getSession(sessionFactory, false).flush();

		int count = simpleJdbcTemplate
			.queryForInt("select count(*) from friendrequest");

		assertEquals("new row should be inserted in the friendrequest table",
			1, count);

	}

	@Test
	@Transactional
	public void testHasFriendRequest()
	{
		User ananth = new User("ananth", "pass");
		userDao.save(ananth);

		User anantha = new User("anantha", "pass");
		userDao.save(anantha);

		User unknown = new User ("unknown" , "pass");
		userDao.save(unknown);
		
		// ananth is sending friend request to anantha

		FriendRequest friendRequest = new FriendRequest();
		friendRequest.setFriend(anantha);
		friendRequest.setUser(ananth);

		friendRequestDao.save(friendRequest);
		// flush the session so we can get the record using JDBC template
		SessionFactoryUtils.getSession(sessionFactory, false).flush();
		
		
		assertEquals("ananth  sent a friend request to anantha",true, friendRequestDao.hasFriendRequest(ananth.getId(), anantha.getId()));
		assertEquals("no friend request",false, friendRequestDao.hasFriendRequest(anantha.getId(), ananth.getId()));
		assertEquals("no friend request",false, friendRequestDao.hasFriendRequest(unknown.getId(), ananth.getId()));

	}

}
