package com.socialsite.dao.hibernate;

import javax.annotation.Resource;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.transaction.annotation.Transactional;

import com.socialsite.authentication.SocialSiteRoles;
import com.socialsite.dao.AbstractDaoTest;
import com.socialsite.dao.UserDao;
import com.socialsite.persistence.User;

import static junit.framework.Assert.*;

public class UserDaoTest extends AbstractDaoTest
{

	@Resource(name = "userDao")
	private UserDao	userDao;

	@Test
	@Transactional
	public void testCreate()
	{
		User ananth = new User("ananth", "pass");
		userDao.save(ananth);

		// flush the session so we can get the record using JDBC template
		SessionFactoryUtils.getSession(sessionFactory, false).flush();

		// check the count of rows in the table
		int result = simpleJdbcTemplate
			.queryForInt("select count(*) from user");
		assertEquals("The table should contain only on row", result, 1);

	}

	@Ignore
	@Test
	@Transactional
	@ExpectedException(ConstraintViolationException.class)
	public void testUniqueUserName()
	{
		User ananth = new User("ananth", "pass");
		userDao.save(ananth);
		User anantha = new User("ananth", "pass");
		userDao.save(anantha);

		// flush the session so we can get the record using JDBC template
		SessionFactoryUtils.getSession(sessionFactory, false).flush();

	}

	@Test
	@Transactional
	public void testCheckUserStatus()
	{
		User ananth = new User("ananth", "pass");
		userDao.save(ananth);
		assertNotNull("correct username and password", userDao.checkUserStatus(
			"ananth", "pass"));
		assertNull("wrong username ", userDao.checkUserStatus("asdf", "pass"));
		assertNull("wrong password ", userDao.checkUserStatus("ananth", "ss"));
	}

	@Test
	@Transactional
	public void testFriendRelation()
	{
		User ananth = new User("ananth", "pass");
		userDao.save(ananth);

		User anantha = new User("anantha", "pass");
		userDao.save(anantha);

		ananth.addFriend(anantha);

		userDao.save(ananth);

		// flush the session so we can get the record using JDBC template
		SessionFactoryUtils.getSession(sessionFactory, false).flush();

		assertEquals("friend_reference count should be on ", 1,
			simpleJdbcTemplate
				.queryForInt("select count(*) from friend_reference"));

		assertEquals("size of the friend list of ananth should be one ", 1,
			userDao.getFriends(ananth).size());

		assertEquals("size of the friend list of anantha should be one ", 1,
			userDao.getFriends(anantha).size());
		assertEquals(" ananth should have a friend with name anantha ",
			"anantha", userDao.getFriends(ananth).get(0).getUserName());
		assertEquals("anantha should have a friend with name ananth ",
			"ananth", userDao.getFriends(anantha).get(0).getUserName());

	}

	@Test
	@Transactional
	public void testGetUsersRelation()
	{
		User ananth = new User("ananth", "pass");
		userDao.save(ananth);

		User anantha = new User("anantha", "pass");
		userDao.save(anantha);

		ananth.addFriend(anantha);

		userDao.save(ananth);

		User unknown = new User("unknown", "pass");
		userDao.save(unknown);

		// flush the session so we can get the record using JDBC template
		SessionFactoryUtils.getSession(sessionFactory, false).flush();

		assertEquals("relationship between ananth and ananth is owner",
			SocialSiteRoles.ownerRole, userDao.getUsersRelation(ananth.getId(),
				ananth.getId()));

		assertEquals("relationship between ananth and anantha is friend",
			SocialSiteRoles.friendRole, userDao.getUsersRelation(ananth.getId(),
				anantha.getId()));
		assertEquals("relationship between ananth and unknown is User",
			SocialSiteRoles.userRole, userDao.getUsersRelation(ananth.getId(),
				unknown.getId()));
	}

}