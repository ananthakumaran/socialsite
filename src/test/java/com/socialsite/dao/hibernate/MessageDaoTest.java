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

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.socialsite.dao.AbstractDaoTestHelper;
import com.socialsite.persistence.InfoMsg;
import com.socialsite.persistence.Student;
import com.socialsite.persistence.User;

/**
 * 
 * @author Ananth
 * 
 */
public class MessageDaoTest extends AbstractDaoTestHelper
{

	@Test
	@Transactional
	public void testGetMessage()
	{
		// create some students
		final User user1 = new Student("user1", "password");
		final User user2 = new Student("user2", "password");
		saveUsers(user1, user2);
		final InfoMsg infoMsg1 = new InfoMsg();
		infoMsg1.setSender(user1);
		infoMsg1.addUser(user2);
		infoMsg1.setMessage("sample message");
		saveMessage(infoMsg1);

		assertEquals(1, messageDao.countAll());
		assertEquals(1, messageDao.getMessage(user2, 0, 1).size());

	}

	@Test
	@Transactional
	public void testGetMessageCount()
	{
		// create some students
		final User user1 = new Student("user1", "password");
		final User user2 = new Student("user2", "password");
		saveUsers(user1, user2);
		final InfoMsg infoMsg1 = new InfoMsg();
		infoMsg1.setSender(user1);
		infoMsg1.addUser(user2);
		infoMsg1.setMessage("sample message");
		saveMessage(infoMsg1);

		assertEquals(0, messageDao.getMessageCount(user1));
		assertEquals(1, messageDao.getMessageCount(user2));
	}

}
