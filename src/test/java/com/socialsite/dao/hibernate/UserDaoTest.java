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

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.junit.Test;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.transaction.annotation.Transactional;

import com.socialsite.authentication.SocialSiteRoles;
import com.socialsite.dao.AbstractDaoTestHelper;
import com.socialsite.persistence.Student;
import com.socialsite.persistence.User;

/**
 * 
 * @author Ananth
 * 
 */
public class UserDaoTest extends AbstractDaoTestHelper
{


	@Test
	@Transactional
	public void testCheckUserStatus()
	{
		final User ananth = new Student("ananth", "pass");
		userDao.save(ananth);
		assertNotNull("correct username and password", userDao.checkUserStatus("ananth", "pass"));
		assertNull("wrong username ", userDao.checkUserStatus("asdf", "pass"));
		assertNull("wrong password ", userDao.checkUserStatus("ananth", "ss"));
	}

	@Test
	@Transactional
	public void testCountAll()
	{
		final User ananth = new Student("ananth", "pass");
		final User anantha = new Student("anantha", "pass");
		saveUsers(ananth, anantha);
		assertEquals(2, userDao.countAll("an"));
		assertEquals(1, userDao.countAll("anantha"));
		assertEquals(0, userDao.countAll("ufasdf"));
		assertEquals(2, userDao.countAll());

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
		final int result = simpleJdbcTemplate.queryForInt("select count(*) from user");
		assertEquals("The table should contain only on row", result, 1);

	}

	@Test
	@Transactional
	public void testFindAll()
	{
		final User ananth = new Student("ananth", "pass");
		final User anantha = new Student("anantha", "pass");
		saveUsers(ananth, anantha);
		assertEquals(2, userDao.findAll(null, 0, 2, new SortParam("userName", true)).size());
		assertEquals(2, userDao.findAll("an", 0, 2, new SortParam("userName", false)).size());
	}

	@Test
	@Transactional
	public void testFriendRelation()
	{
		final User ananth = new Student("ananth", "pass");
		final User anantha = new Student("anantha", "pass");
		saveUsers(ananth, anantha);
		ananth.addFriend(anantha);
		saveUsers(ananth);

		// flush the session so we can get the record using JDBC template
		SessionFactoryUtils.getSession(sessionFactory, false).flush();

		assertEquals("friend_reference count should be on ", 2, simpleJdbcTemplate
				.queryForInt("select count(*) from friend_reference"));

		assertEquals("size of the friend list of ananth should be one ", 1, userDao.getFriends(
				ananth).size());

		assertEquals("size of the friend list of anantha should be one ", 1, userDao.getFriends(
				anantha).size());
		assertEquals(" ananth should have a friend with name anantha ", "anantha", userDao
				.getFriends(ananth).get(0).getUserName());
		assertEquals("anantha should have a friend with name ananth ", "ananth", userDao
				.getFriends(anantha).get(0).getUserName());

	}

	@Test
	@Transactional
	public void testGetFriends()
	{
		final User ananth = new Student("ananth", "pass");
		final User anantha = new Student("anantha", "pass");
		saveUsers(ananth, anantha);
		ananth.addFriend(anantha);
		saveUsers(anantha);
		// flush the session so we can get the record using JDBC template
		SessionFactoryUtils.getSession(sessionFactory, false).flush();
		assertEquals(1, userDao.getFriends(ananth).size());
		assertEquals(1, userDao.getFriends(anantha.getId(), 0, 1).size());

		final User friend = userDao.getFriends(ananth.getId(), 0, 1).get(0);

		assertEquals(anantha, friend);

	}

	@Test
	@Transactional
	public void testGetFriendsCount()
	{
		final User ananth = new Student("ananth", "pass");
		final User anantha = new Student("anantha", "pass");
		saveUsers(ananth, anantha);
		ananth.addFriend(anantha);
		saveUsers(anantha);
		// flush the session so we can get the record using JDBC template
		SessionFactoryUtils.getSession(sessionFactory, false).flush();
		assertEquals(1, userDao.getFriendsCount(anantha.getId()));
		assertEquals(1, userDao.getFriendsCount(ananth.getId()));

	}

	@Test
	@Transactional
	public void testGetUsersRelation()
	{
		final User ananth = new Student("ananth", "pass");
		final User anantha = new Student("anantha", "pass");
		saveUsers(ananth, anantha);
		ananth.addFriend(anantha);
		final User unknown = new Student("unknown", "pass");
		saveUsers(anantha, unknown);
		// flush the session so we can get the record using JDBC template
		SessionFactoryUtils.getSession(sessionFactory, false).flush();

		assertEquals("relationship between ananth and ananth is owner", SocialSiteRoles.ownerRole,
				userDao.getUsersRelation(ananth.getId(), ananth.getId()));

		assertEquals("relationship between ananth and anantha is friend",
				SocialSiteRoles.friendRole, userDao.getUsersRelation(ananth.getId(), anantha
						.getId()));
		assertEquals("relationship between ananth and unknown is User", SocialSiteRoles.userRole,
				userDao.getUsersRelation(ananth.getId(), unknown.getId()));
	}

}