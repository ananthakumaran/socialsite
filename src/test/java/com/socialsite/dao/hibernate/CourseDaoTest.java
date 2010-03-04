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
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.transaction.annotation.Transactional;

import com.socialsite.authentication.SignUpPage;
import com.socialsite.dao.AbstractDaoTestHelper;
import com.socialsite.persistence.Course;
import com.socialsite.persistence.Student;
import com.socialsite.persistence.User;
import com.socialsite.util.SpringWicketTester;

/**
 * 
 * @author Ananth
 * 
 */
public class CourseDaoTest extends AbstractDaoTestHelper
{
	@Test
	@Transactional
	public void testCountAll()
	{
		// courses
		final Course course1 = new Course();
		course1.setName("test1");
		final Course course2 = new Course();
		course2.setName("test2");
		saveCourses(course1, course2);
		assertEquals(2, courseDao.countAll("test"));
		assertEquals(1, courseDao.countAll("test1"));
		assertEquals(0, courseDao.countAll("none"));
		assertEquals(2, courseDao.countAll(null));
	}


	@Transactional
	@Test
	public void testFindAll()
	{
		// courses
		final Course course1 = new Course();
		course1.setName("test1");
		final Course course2 = new Course();
		course2.setName("test2");
		saveCourses(course1, course2);
		assertEquals(2, courseDao.findAll("test", 0, 2, new SortParam("name", true)).size());
		assertEquals(2, courseDao.findAll(null, 0, 2, new SortParam("name", false)).size());
		assertEquals(2, courseDao.findAll().size());
	}

	@Test
	@Transactional
	public void testGetImage()
	{
		tester = new SpringWicketTester();
		tester.startPage(SignUpPage.class);
		final Course course1 = new Course();
		course1.setName("test1");
		setDefaultImage(course1);
		saveCourses(course1);
		assertNotNull(courseDao.getImage(course1.getId()));
	}

	@Test
	@Transactional
	public void testGetLastModifiedTime()
	{
		final Date date = new Date();
		// courses
		final Course course1 = new Course();
		course1.setName("test1");
		course1.setLastModified(date);
		saveCourses(course1);

		assertEquals(date.getTime() / 1000, courseDao.getLastModifiedTime(course1.getId())
				.getTime() / 1000);
	}

	@Test
	@Transactional
	public void testGetThumb()
	{
		tester = new SpringWicketTester();
		tester.startPage(SignUpPage.class);
		final Course course1 = new Course();
		course1.setName("test1");
		setDefaultImage(course1);
		saveCourses(course1);
		assertNotNull(courseDao.getThumb(course1.getId()));
	}

	@Test
	@Transactional
	public void testAddStudentInCourse()
	{

		final Course course1 = new Course();
		course1.setName("test1");
		saveCourses(course1);
		final User user1 = new Student("user1", "password");
		saveUsers(user1);
		course1.addStudents((Student)user1);
		saveCourses(course1);
		// flush the session so we can get the record using JDBC template
		SessionFactoryUtils.getSession(sessionFactory, false).flush();
		assertEquals(1, course1.getStudents().size());
	}

}
