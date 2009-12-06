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

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.transaction.annotation.Transactional;

import com.socialsite.dao.AbstractDaoTest;
import com.socialsite.dao.ScrapDao;
import com.socialsite.dao.UserDao;
import com.socialsite.persistence.Scrap;
import com.socialsite.persistence.Student;
import com.socialsite.persistence.User;

/**
 * @author Ananth
 * 
 */
public class ScrapDaoTest extends AbstractDaoTest
{
	@Resource(name = "userDao")
	private UserDao<User>	userDao;

	@Resource(name = "scrapDao")
	private ScrapDao		scrapDao;

	@Test
	@Transactional
	public void testCreate()
	{
		final User ananth = new Student("ananth", "pass");
		userDao.save(ananth);

		final User anantha = new Student("anantha", "pass");
		userDao.save(anantha);

		final Scrap scrap = new Scrap();
		scrap.setAuthor(anantha);
		scrap.setReceiver(ananth);
		scrap.setMessage(" Hello ");
		scrap.setTime(new Date());
		scrapDao.save(scrap);

		// flush the session so we can get the record using JDBC template
		SessionFactoryUtils.getSession(sessionFactory, false).flush();

		// check the count of rows in the table
		final int result = simpleJdbcTemplate
			.queryForInt("select count(*) from scrap");
		assertEquals("The table should contain only on row", result, 1);

	}

	@Transactional
	@Test
	public void testGetScraps()
	{
		final User ananth = new Student("ananth", "pass");
		userDao.save(ananth);

		final User anantha = new Student("anantha", "pass");
		userDao.save(anantha);

		final Scrap scrap = new Scrap();
		scrap.setAuthor(anantha);
		scrap.setReceiver(ananth);
		scrap.setMessage(" Hello ");
		scrap.setTime(new Date());
		scrapDao.save(scrap);

		final Scrap scrap1 = new Scrap();
		scrap1.setAuthor(anantha);
		scrap1.setReceiver(ananth);
		scrap1.setMessage(" another  ");
		scrap1.setTime(new Date());
		scrapDao.save(scrap1);

		// flush the session so we can get the record using JDBC template
		SessionFactoryUtils.getSession(sessionFactory, false).flush();

		final List<Scrap> scraps = scrapDao.getScraps(ananth, 0, 2);

		assertEquals("it should return one scrap ", 2, scraps.size());

		final int count = scrapDao.getScrapsCount(ananth);

		assertEquals("it should return one scrap ", 2, count);
	}

}
