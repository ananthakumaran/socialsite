package com.socialsite.dao.hibernate;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

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
	public void friendRequestMessageTest()
	{
		final User ananth = new Student("ananth", "pass");
		userDao.save(ananth);

		final User anantha = new Student("anantha", "pass");
		userDao.save(anantha);

		final FriendRequestMsg m = new FriendRequestMsg();
		m.setMessage("hello");
		m.setSender(ananth);
		m.setUser(anantha);
		m.setTime(new Date());

		// friendRequestMsgDao.save(m);
		messageDao.save(m);

		assertEquals(0, messageDao.getMessageCount(ananth));
		assertEquals(1, messageDao.getMessageCount(anantha));

	}
}
