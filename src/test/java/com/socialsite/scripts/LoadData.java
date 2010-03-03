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

package com.socialsite.scripts;


import java.util.Date;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.NotTransactional;

import com.socialsite.authentication.LoginPage;
import com.socialsite.dao.AbstractDaoTestHelper;
import com.socialsite.persistence.Admin;
import com.socialsite.persistence.Answer;
import com.socialsite.persistence.Course;
import com.socialsite.persistence.FriendRequestMsg;
import com.socialsite.persistence.InfoMsg;
import com.socialsite.persistence.Question;
import com.socialsite.persistence.Staff;
import com.socialsite.persistence.Student;
import com.socialsite.persistence.University;
import com.socialsite.persistence.User;
import com.socialsite.util.SpringWicketTester;

public class LoadData extends AbstractDaoTestHelper
{


	/**
	 * run this method to load some data for the site
	 */
	@Test
	@NotTransactional
	public void load()
	{

		// clear the database
		SchemaCreator.create();

		// create some students
		final User user1 = new Student("user1", "password");
		final User user2 = new Student("user2", "password");
		final User user3 = new Student("user3", "password");
		final User user4 = new Student("user4", "password");
		final User user5 = new Student("user5", "password");
		saveUsers(user1, user2, user3, user4, user5);

		// create the profiles
		createProfiles(user1, user2, user3, user4, user5);
		// add some friends for all users
		setFriend(user1, user2, user3, user4);
		setFriend(user2, user3, user4);
		setFriend(user3, user5);
		saveUsers(user1, user2, user3, user4, user5);

		// create admin for university
		final Admin admin1 = new Admin("admin1", "password");
		final Admin admin2 = new Admin("admin2", "password");
		saveUsers(admin1, admin2);
		createProfiles(admin1, admin2);

		// create universities
		final University university1 = new University("TestUniversity", admin1);
		final University university2 = new University("Harvard", admin2);
		setDefaultImage(university1, university2);
		saveUniversities(university1, university2);

		// staffs
		final Staff staff1 = new Staff("staff1", "password");
		final Staff staff2 = new Staff("staff2", "password", university2);
		saveUsers(staff1, staff2);
		saveUniversities(university1, university2);
		createProfiles(staff1, staff2);


		// courses
		final Course course1 = new Course("course1", staff1, university1);
		final Course course2 = new Course("course2", staff2, university2);
		setDefaultImage(course1, course2);
		saveCourses(course1, course2);
		saveUniversities(university1, university2);


		// Questions
		final Question question1 = new Question("sample Question1", course1, user1);
		question1.setText("<b>This is a sample Question1</b><em>this is em</em><p>new paragraph");
		final Question question2 = new Question("sample Question2", course1, user1);
		question2.setText("This is a sample Question2");
		saveQuestion(question1, question2);


		// Answers
		final Answer answer1 = new Answer(question1, user2);
		answer1.setText("this is a sample answer1");
		final Answer answer2 = new Answer(question1, user3);
		answer2.setText("this is a sample answer2");
		saveAnswers(answer1, answer2);


		// messages
		InfoMsg infoMsg1 = new InfoMsg();
		infoMsg1.setMessage("This is a info message");
		infoMsg1.setSender(user1);
		// multicast
		infoMsg1.addUser(user2);
		infoMsg1.addUser(user3);
		infoMsg1.addUser(user4);
		infoMsg1.addUser(user5);
		infoMsg1.setTime(new Date());

		InfoMsg infoMsg2 = new InfoMsg();
		infoMsg2.setMessage("Hi to all friends");
		infoMsg2.setSender(user1);
		// if you take the set from the user and assign it to the message
		// hibernate removes the set from the user(???).So create a new Set
		infoMsg2.setUsers(new HashSet<User>(user1.getFriends()));
		infoMsg2.setTime(new Date());

		InfoMsg infoMsg3 = new InfoMsg();
		infoMsg3.setMessage(" user1");
		infoMsg3.setSender(user2);
		infoMsg3.addUser(user1);
		infoMsg3.setTime(new Date());

		// friend reques message
		FriendRequestMsg frnRqMsg = new FriendRequestMsg();
		frnRqMsg.setSender(user5);
		frnRqMsg.addUser(user1);
		frnRqMsg.setMessage("Hai User1");
		frnRqMsg.setTime(new Date());

		saveMessage(infoMsg1, infoMsg2, infoMsg3, frnRqMsg);


	}

	@Before
	public void setup()
	{
		tester = new SpringWicketTester();
		tester.startPage(LoginPage.class);

	}
}
