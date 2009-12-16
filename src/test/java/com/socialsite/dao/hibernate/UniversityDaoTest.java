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

import javax.annotation.Resource;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.socialsite.dao.AbstractDaoTest;
import com.socialsite.dao.UniversityDao;
import com.socialsite.persistence.University;

/**
 * 
 * 
 * 
 * @author Ananth
 * 
 */
public class UniversityDaoTest extends AbstractDaoTest
{

	@Resource(name = "universityDao")
	private UniversityDao universityDao;

	@Test
	@Transactional
	public void countTest()
	{
		final University u = new University("test");
		universityDao.save(u);
		assertEquals(1, universityDao.countAll(""));
	}

	@Test
	@Transactional
	public void findAllTest()
	{
		// TODO check some more conditions
		final University u = new University("test");
		universityDao.save(u);
		assertEquals(1, universityDao.findAll("te", 0, 5, new SortParam("name", true)).size());
	}

}
