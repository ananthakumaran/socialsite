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

import java.util.Calendar;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.socialsite.dao.AbstractDaoTestHelper;
import com.socialsite.persistence.Answer;
import com.socialsite.persistence.Course;
import com.socialsite.persistence.Question;
import com.socialsite.persistence.Student;
import com.socialsite.persistence.User;

/**
 * 
 * @author Ananth
 * 
 */
public class AnswerDaoTest extends AbstractDaoTestHelper
{
	@Test
	@Transactional
	public void testGetAnswers()
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
		saveQuestion(question1);
		// courses
		course1.addQuestions(question1);
		saveCourses(course1);

		// Answers
		final Answer answer1 = new Answer(question1, user1);
		answer1.setText("this is a sample answer1");
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, 1);
		// makes sure the answer comes first in the list of anwers
		answer1.setTime(cal.getTime());
		final Answer answer2 = new Answer(question1, user1);
		answer2.setText("this is a sample answer2");
		saveAnswers(answer1, answer2);


		assertEquals(answer1.getId(), answerDao.getAnswers(question1.getId(), 0, 1).get(0).getId());
		assertEquals(2, answerDao.getAnswers(question1.getId(), 0, 2).size());

	}

	@Test
	@Transactional
	public void testGetAnswersCount()
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
		saveQuestion(question1);
		// courses
		course1.addQuestions(question1);
		saveCourses(course1);

		// Answers
		final Answer answer1 = new Answer(question1, user1);
		answer1.setText("this is a sample answer1");
		final Answer answer2 = new Answer(question1, user1);
		answer2.setText("this is a sample answer2");
		saveAnswers(answer1, answer2);

		assertEquals(2, answerDao.getAnswersCount(question1.getId()));
	}

}
