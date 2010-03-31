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
import com.socialsite.persistence.Admin;
import com.socialsite.persistence.Course;
import com.socialsite.persistence.Staff;
import com.socialsite.persistence.Student;
import com.socialsite.persistence.University;
import com.socialsite.persistence.User;
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

	@Test
	@Transactional
	public void testGetStudents()
	{
		// create some students
		final User user1 = new Student("user1", "password");
		final User user2 = new Student("user2", "password");
		final User user3 = new Student("user3", "password");
		final User user4 = new Student("user4", "password");

		saveUsers(user1, user2, user3, user4);

		// create admin for university
		final Admin admin1 = new Admin("admin1", "password");

		saveUsers(admin1);
		// create universities
		final University university1 = new University("University1", admin1);

		saveUniversities(university1);

		// staffs
		final Staff staff1 = new Staff("staff1", "password", university1);
		final Staff staff2 = new Staff("staff2", "password", university1);

		saveUsers(staff1, staff2);

		// courses
		final Course course1 = new Course("course1", staff1);
		final Course course2 = new Course("course2", staff2);
		final Course course3 = new Course("course3", staff2);

		saveCourses(course1, course2, course3);

		joinCourse(user1, course1, course2, course3);
		joinCourse(user2, course3);
		joinCourse(user3, course3);
		joinCourse(user4, course1, course2);

		assertEquals(4, universityDao.getStudents(university1.getId()).size());

		assertEquals(4, universityDao.getStudentsCount(university1.getId()));


	}
}
