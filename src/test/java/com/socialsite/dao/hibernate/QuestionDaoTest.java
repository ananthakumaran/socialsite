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
import com.socialsite.persistence.Course;
import com.socialsite.persistence.Question;
import com.socialsite.persistence.Student;
import com.socialsite.persistence.User;

/**
 * 
 * @author Ananth
 * 
 */
public class QuestionDaoTest extends AbstractDaoTestHelper
{
	@Test
	@Transactional
	public void testGetQuestions()
	{

		// create some students
		final User user1 = new Student("user1", "password");
		saveUsers(user1);

		final Course course1 = new Course();
		course1.setName("test1");
		saveCourses(course1);
		// Questions
		final Question question1 = new Question("sample Question1", course1, user1);
		question1.setText("<b>This is a sample Question1</b><em>this is em</em><p>new paragraph");
		final Question question2 = new Question("sample Question2", course1, user1);
		question2.setText("This is a sample Question2");
		saveQuestion(question1, question2);
		// courses
		course1.addQuestions(question1);
		course1.addQuestions(question2);
		saveCourses(course1);
		assertEquals(2, questionDao.getQuestions(course1.getId(), 0, 2).size());
	}

	@Test
	@Transactional
	public void testGetQuestionsCount()
	{
		// create some students
		final User user1 = new Student("user1", "password");
		saveUsers(user1);

		final Course course1 = new Course();
		course1.setName("test1");
		saveCourses(course1);
		// Questions
		final Question question1 = new Question("sample Question1", course1, user1);
		question1.setText("<b>This is a sample Question1</b><em>this is em</em><p>new paragraph");
		final Question question2 = new Question("sample Question2", course1, user1);
		question2.setText("This is a sample Question2");
		saveQuestion(question1, question2);
		// courses
		course1.addQuestions(question1);
		course1.addQuestions(question2);
		saveCourses(course1);
		assertEquals(2, questionDao.getQuestionsCount(course1.getId()));

	}
}
