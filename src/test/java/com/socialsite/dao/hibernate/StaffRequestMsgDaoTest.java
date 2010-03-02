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

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.socialsite.dao.AbstractDaoTestHelper;
import com.socialsite.persistence.Admin;
import com.socialsite.persistence.Staff;
import com.socialsite.persistence.StaffRequestMsg;
import com.socialsite.persistence.University;

/**
 * 
 * @author Ananth
 * 
 */
public class StaffRequestMsgDaoTest extends AbstractDaoTestHelper
{
	@Test
	@Transactional
	public void testHasRequest()
	{
		// create admin for university
		final Admin admin1 = new Admin("admin1", "password");
		saveUsers(admin1);
		// create universities
		final University university1 = new University("TestUniversity", admin1);
		saveUniversities(university1);
		// staffs
		final Staff staff1 = new Staff("staff1", "password");
		final Staff staff2 = new Staff("staff2", "password");
		saveUsers(staff1, staff2);
		StaffRequestMsg msg = new StaffRequestMsg(staff1);
		msg.setTime(new Date());
		msg.setUniversity(university1);
		msg.addUser(university1.getAdmin());
		staffRequestMsgDao.save(msg);

		Assert.assertEquals(true, staffRequestMsgDao.hasRequest(staff1, university1));
		Assert.assertEquals(false, staffRequestMsgDao.hasRequest(staff2, university1));

	}

}
