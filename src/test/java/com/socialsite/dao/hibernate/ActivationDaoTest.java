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

import org.junit.Test;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.transaction.annotation.Transactional;

import com.socialsite.dao.AbstractDaoTestHelper;
import com.socialsite.persistence.Activation;
import com.socialsite.persistence.Admin;

/**
 * 
 * @author Ananth
 * 
 */
public class ActivationDaoTest extends AbstractDaoTestHelper
{
	@Test
	@Transactional
	public void createActivation()
	{
		Admin admin = new Admin();
		admin.setUserName("admin");
		admin.setPassword("password");
		saveUsers(admin);

		Activation activation = new Activation();
		activation.setAdmin(admin);
		activation.setUniversityName("testUniversity");

		activationDao.save(activation);
		// flush the session so we can get the record using JDBC template
		SessionFactoryUtils.getSession(sessionFactory, false).flush();

		assertEquals("activation count should be one ", 1, simpleJdbcTemplate
				.queryForInt("select count(*) activation"));
	}
}
