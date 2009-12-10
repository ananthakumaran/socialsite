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
import static org.junit.Assert.assertTrue;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.socialsite.dao.AbstractDaoTest;
import com.socialsite.dao.FriendRequestMsgDao;
import com.socialsite.dao.MessageDao;
import com.socialsite.dao.UserDao;
import com.socialsite.persistence.FriendRequestMsg;
import com.socialsite.persistence.Message;
import com.socialsite.persistence.Student;
import com.socialsite.persistence.User;

public class FriendRequestMsgDaoTest extends AbstractDaoTest
{

	@Resource(name = "messageDao")
	MessageDao<Message>		messageDao;

	@Resource(name = "userDao")
	private UserDao<User>	userDao;

	@Resource(name = "friendRequestMsgDao")
	FriendRequestMsgDao		friendRequestMsgDao;

	@Test
	@Transactional
	public void friendRequestMessageTest()
	{
		final User ananth = new Student("ananth", "pass");
		userDao.save(ananth);

		final User anantha = new Student("anantha", "pass");
		userDao.save(anantha);

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
	public void hasFriendRequestTest()
	{
		final User ananth = new Student("ananth", "pass");
		userDao.save(ananth);

		final User anantha = new Student("anantha", "pass");
		userDao.save(anantha);

		final FriendRequestMsg m = new FriendRequestMsg();
		m.setMessage("hello");
		m.setSender(ananth);
		m.getUsers().add(anantha);
		m.setTime(new Date());

		// friendRequestMsgDao.save(m);
		messageDao.save(m);

		assertTrue(friendRequestMsgDao.hasFriendRequest(ananth.getId(), anantha
			.getId()));
		assertTrue(friendRequestMsgDao.hasFriendRequest(anantha.getId(), ananth
			.getId()));

	}
}
