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
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.socialsite.authentication.SignUpPage;
import com.socialsite.dao.AbstractDaoTestHelper;
import com.socialsite.persistence.University;
import com.socialsite.util.SpringWicketTester;

/**
 * 
 * 
 * 
 * @author Ananth
 * 
 */
public class UniversityDaoTest extends AbstractDaoTestHelper
{

	@Test
	@Transactional
	public void testCountAll()
	{
		final University u = new University("test");
		universityDao.save(u);
		assertEquals(1, universityDao.countAll(""));
	}

	@Test
	@Transactional
	public void testFinlAll()
	{
		// TODO check some more conditions
		final University u = new University("test");
		universityDao.save(u);
		assertEquals(1, universityDao.findAll("te", 0, 5, new SortParam("name", true)).size());
	}

	@Test
	@Transactional
	public void testGetImage()
	{
		tester = new SpringWicketTester();
		tester.startPage(SignUpPage.class);
		final University university1 = new University("testuniversity");
		setDefaultImage(university1);
		saveUniversities(university1);
		assertNotNull(universityDao.getImage(university1.getId()));
	}

	@Test
	@Transactional
	public void testGetLastModifiedTime()
	{
		final Date date = new Date();
		// courses
		final University university1 = new University("testuniversity");
		university1.setLastModified(date);
		saveUniversities(university1);

		assertEquals(date.getTime() / 1000, universityDao.getLastModifiedTime(university1.getId())
				.getTime() / 1000);
	}

	@Test
	@Transactional
	public void testGetThumb()
	{
		tester = new SpringWicketTester();
		tester.startPage(SignUpPage.class);
		final University university1 = new University("testuniversity");
		setDefaultImage(university1);
		saveUniversities(university1);
		assertNotNull(universityDao.getThumb(university1.getId()));
	}


}
