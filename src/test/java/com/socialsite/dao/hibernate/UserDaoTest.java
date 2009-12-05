package com.socialsite.dao.hibernate;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.fail;

import javax.annotation.Resource;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.transaction.annotation.Transactional;

import com.socialsite.authentication.SocialSiteRoles;
import com.socialsite.dao.AbstractDaoTest;
import com.socialsite.dao.UserDao;
import com.socialsite.persistence.Student;
import com.socialsite.persistence.User;

public class UserDaoTest extends AbstractDaoTest
{

	@Resource(name = "userDao")
	private UserDao<User>	userDao;

	@Test
	@Transactional
	public void testCheckUserStatus()
	{
		final User ananth = new Student("ananth", "pass");
		userDao.save(ananth);
		assertNotNull("correct username and password", userDao.checkUserStatus(
			"ananth", "pass"));
		assertNull("wrong username ", userDao.checkUserStatus("asdf", "pass"));
		assertNull("wrong password ", userDao.checkUserStatus("ananth", "ss"));
	}

	@Test
	@Transactional
	public void testCreate()
	{
		final User ananth = new Student("ananth", "pass");
		userDao.save(ananth);

		// flush the session so we can get the record using JDBC template
		SessionFactoryUtils.getSession(sessionFactory, false).flush();

		// check the count of rows in the table
		final int result = simpleJdbcTemplate
			.queryForInt("select count(*) from user");
		assertEquals("The table should contain only on row", result, 1);

	}

	@Test
	@Transactional
	public void testFriendRelation()
	{
		final User ananth = new Student("ananth", "pass");
		userDao.save(ananth);

		final User anantha = new Student("anantha", "pass");
		userDao.save(anantha);

		ananth.addFriend(anantha);

		userDao.save(ananth);

		// flush the session so we can get the record using JDBC template
		SessionFactoryUtils.getSession(sessionFactory, false).flush();

		assertEquals("friend_reference count should be on ", 2,
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
		final User ananth = new Student("ananth", "pass");
		userDao.save(ananth);

		final User anantha = new Student("anantha", "pass");
		userDao.save(anantha);

		ananth.addFriend(anantha);

		userDao.save(ananth);

		final User unknown = new Student("unknown", "pass");
		userDao.save(unknown);

		// flush the session so we can get the record using JDBC template
		SessionFactoryUtils.getSession(sessionFactory, false).flush();

		assertEquals("relationship between ananth and ananth is owner",
			SocialSiteRoles.ownerRole, userDao.getUsersRelation(ananth.getId(),
				ananth.getId()));

		assertEquals("relationship between ananth and anantha is friend",
			SocialSiteRoles.friendRole, userDao.getUsersRelation(
				ananth.getId(), anantha.getId()));
		assertEquals("relationship between ananth and unknown is User",
			SocialSiteRoles.userRole, userDao.getUsersRelation(ananth.getId(),
				unknown.getId()));
	}

	@Test
	@Transactional
	public void testUniqueUserName()
	{
		try
		{
			final User ananth = new Student("ananth", "pass");
			userDao.save(ananth);
			final User anantha = new Student("ananth", "pass");
			userDao.save(anantha);
			fail("should throw a exception");
		} catch (final ConstraintViolationException ex)
		{

		}

	}

}