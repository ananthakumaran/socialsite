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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.socialsite.dao.AbstractDaoTestHelper;
import com.socialsite.persistence.FriendRequestMsg;
import com.socialsite.persistence.Student;
import com.socialsite.persistence.User;

/**
 * 
 * @author Ananth
 * 
 */
public class FriendRequestMsgDaoTest extends AbstractDaoTestHelper
{


	@Test
	@Transactional
	public void testDeleteFriendRequest()
	{
		final User ananth = new Student("ananth", "pass");
		final User anantha = new Student("anantha", "pass");

		saveUsers(ananth, anantha);

		final FriendRequestMsg m = new FriendRequestMsg();
		m.setMessage("hello");
		m.setSender(ananth);
		m.getUsers().add(anantha);
		m.setTime(new Date());
		messageDao.save(m);

		assertEquals(1, messageDao.countAll());
		assertEquals(2, userDao.countAll());
		// delete the message
		messageDao.delete(m);
		assertEquals(0, messageDao.countAll());
		assertEquals(2, userDao.countAll());


	}

	@Test
	@Transactional
	public void testFriendRequestMessage()
	{
		final User ananth = new Student("ananth", "pass");
		final User anantha = new Student("anantha", "pass");

		saveUsers(ananth, anantha);

		final FriendRequestMsg m = new FriendRequestMsg();
		m.setMessage("hello");
		m.setSender(ananth);
		m.getUsers().add(anantha);
		m.setTime(new Date());

		// friendRequestMsgDao.save(m);
		messageDao.save(m);

		assertEquals(0, messageDao.getMessageCount(ananth));
		assertEquals(1, messageDao.getMessageCount(anantha));

	}

	@Test
	@Transactional
	public void testHasFriendRequest()
	{
		final User ananth = new Student("ananth", "pass");
		final User anantha = new Student("anantha", "pass");
		final User user1 = new Student("user1", "password");
		saveUsers(ananth, anantha, user1);

		final FriendRequestMsg m = new FriendRequestMsg();
		m.setMessage("hello");
		m.setSender(ananth);
		m.getUsers().add(anantha);
		m.setTime(new Date());

		// friendRequestMsgDao.save(m);
		messageDao.save(m);

		assertTrue(friendRequestMsgDao.hasFriendRequest(ananth.getId(), anantha.getId()));
		assertTrue(friendRequestMsgDao.hasFriendRequest(anantha.getId(), ananth.getId()));
		assertFalse(friendRequestMsgDao.hasFriendRequest(ananth.getId(), user1.getId()));
		assertFalse(friendRequestMsgDao.hasFriendRequest(user1.getId(), anantha.getId()));

	}
}
