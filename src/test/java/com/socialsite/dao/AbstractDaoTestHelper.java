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

package com.socialsite.dao;

import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.socialsite.image.DefaultImage;
import com.socialsite.persistence.Answer;
import com.socialsite.persistence.Course;
import com.socialsite.persistence.Message;
import com.socialsite.persistence.Profile;
import com.socialsite.persistence.Question;
import com.socialsite.persistence.Student;
import com.socialsite.persistence.University;
import com.socialsite.persistence.User;
import com.socialsite.util.SpringWicketTester;

/**
 * Abstract test class for all the Dao test. provides many helper methods
 * 
 * @author Ananth
 * 
 */
@SuppressWarnings("restriction")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
abstract public class AbstractDaoTestHelper extends AbstractTransactionalJUnit4SpringContextTests
{

	protected SpringWicketTester tester;

	@Resource(name = "userDao")
	protected UserDao<User> userDao;

	@Resource(name = "profileDao")
	protected ProfileDao profileDao;

	@Resource(name = "universityDao")
	protected UniversityDao universityDao;

	@Resource(name = "courseDao")
	protected CourseDao courseDao;

	@Resource(name = "questionDao")
	protected QuestionDao questionDao;

	@Resource(name = "answerDao")
	protected AnswerDao answerDao;

	@Resource(name = "friendRequestMsgDao")
	protected FriendRequestMsgDao friendRequestMsgDao;

	@Resource(name = "staffRequestMsgDao")
	protected StaffRequestMsgDao staffRequestMsgDao;

	@Resource(name = "adminDao")
	protected AdminDao adminDao;

	@Resource(name = "commentDao")
	protected CommentDao commmentDao;

	@Resource(name = "studentDao")
	protected StudentDao studentDao;

	@Resource(name = "staffDao")
	protected StaffDao staffDao;

	@Resource(name = "scrapDao")
	protected ScrapDao scrapDao;

	@Resource(name = "messageDao")
	protected MessageDao<Message> messageDao;

	@Resource(name = "infoMsgDao")
	protected InfoMsgDao infoMsgDao;

	@Resource(name = "activationDao")
	protected ActivationDao activationDao;

	@Autowired
	protected SessionFactory sessionFactory;

	/**
	 * helper to create profile for users
	 * 
	 * @param users
	 *            users
	 */
	public void createProfiles(final User... users)
	{
		final DefaultImage image = new DefaultImage();

		for (final User user : users)
		{
			final Profile profile = new Profile();
			profile.setUser(user);
			user.setProfile(profile);
			image.forUser(user.getProfile());
			profileDao.save(user.getProfile());
		}
	}

	/**
	 * helper to save multiple answers
	 * 
	 * @param answers
	 *            answers
	 */
	public void saveAnswers(final Answer... answers)
	{
		System.out.println("saving Answers");
		for (final Answer answer : answers)
		{
			answerDao.save(answer);
		}
	}

	/**
	 * helper to save multiple courses
	 * 
	 * @param courses
	 *            courses
	 */
	public void saveCourses(final Course... courses)
	{
		System.out.println("saving Courses");
		for (final Course course : courses)
		{
			courseDao.save(course);
		}
	}

	/**
	 * helper to save messages
	 * 
	 * @param messages
	 *            messages
	 */
	public void saveMessage(final Message... messages)
	{
		for (final Message message : messages)
		{
			messageDao.save(message);
		}
	}

	/**
	 * helper to save multiple questions
	 * 
	 * @param questions
	 *            questions
	 */
	public void saveQuestion(final Question... questions)
	{
		System.out.println("saving questions");
		for (final Question question : questions)
		{
			question.setTime(new Date());
			questionDao.save(question);
		}
	}


	/**
	 * helper to save multiple universities
	 * 
	 * @param uinversities
	 *            universities
	 */
	public void saveUniversities(final University... uinversities)
	{
		System.out.println("saving universities");
		for (final University university : uinversities)
		{
			university.setLastModified(new Date());
			universityDao.save(university);
		}
	}

	/**
	 * helper to save users
	 * 
	 * @param users
	 *            users
	 */
	public void saveUsers(final User... users)
	{
		System.out.println("saving users");
		for (final User user : users)
		{
			user.setLastModified(new Date());
			userDao.save(user);
		}
	}

	/**
	 * helper to set the default image for the courses
	 * 
	 * @param courses
	 *            courses
	 */
	public void setDefaultImage(final Course... courses)
	{
		System.out.println("setting default image for courses");
		final DefaultImage image = new DefaultImage();
		for (final Course course : courses)
		{
			image.forCourse(course);
		}

	}

	/**
	 * helper to set the default image for the courses
	 * 
	 * @param universities
	 *            courses
	 */
	public void setDefaultImage(final University... universities)
	{
		System.out.println("setting default image for university");
		final DefaultImage image = new DefaultImage();
		for (final University university : universities)
		{
			image.forUniversity(university);
		}
	}

	/**
	 * helper method to add many friends
	 * 
	 * @param user
	 *            user
	 * @param friends
	 *            friends to be added
	 */
	public void setFriend(final User user, final User... friends)
	{
		for (final User friend : friends)
		{
			user.addFriend(friend);
		}
		userDao.save(user);
	}

	/**
	 * helper to join the course
	 * @param user
	 * @param courses
	 */
	public void joinCourse(final User user, final Course... courses)
	{
		for (final Course course : courses)
		{
			course.addStudents((Student)user);
			courseDao.save(course);
		}
	}

}
