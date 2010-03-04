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
		final User user6 = new Student("user6", "password");
		final User user7 = new Student("user7", "password");
		final User user8 = new Student("user8", "password");
		final User user9 = new Student("user9", "password");
		final User user10 = new Student("user10", "password");
		final User user11 = new Student("user11", "password");
		final User user12 = new Student("user12", "password");
		final User user13 = new Student("user13", "password");
		final User user14 = new Student("user14", "password");
		final User user15 = new Student("user15", "password");
		saveUsers(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11,
				user12, user13, user14, user15);

		// create the profiles
		createProfiles(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10,
				user11, user12, user13, user14, user15);
		// add some friends for all users
		setFriend(user1, user2, user3, user4, user6, user7, user8, user9, user10, user11, user12);
		setFriend(user2, user3, user4, user5, user6, user7, user8, user9, user10, user12);
		setFriend(user3, user5);
		saveUsers(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11,
				user12, user13, user14, user15);

		// create admin for university
		final Admin admin1 = new Admin("admin1", "password");
		final Admin admin2 = new Admin("admin2", "password");
		final Admin admin3 = new Admin("admin3", "password");
		final Admin admin4 = new Admin("admin4", "password");
		final Admin admin5 = new Admin("admin5", "password");
		final Admin admin6 = new Admin("admin6", "password");
		final Admin admin7 = new Admin("admin7", "password");
		final Admin admin8 = new Admin("admin8", "password");
		final Admin admin9 = new Admin("admin9", "password");
		final Admin admin10 = new Admin("admin10", "password");
		final Admin admin11 = new Admin("admin11", "password");
		final Admin admin12 = new Admin("admin12", "password");
		final Admin admin13 = new Admin("admin13", "password");

		saveUsers(admin1, admin2, admin3, admin4, admin5, admin6, admin7, admin8, admin9, admin10,
				admin11, admin12, admin13);
		createProfiles(admin1, admin2, admin3, admin4, admin5, admin6, admin7, admin8, admin9,
				admin10, admin11, admin12, admin13);

		// create universities
		final University university1 = new University("University1", admin1);
		final University university2 = new University("University2", admin2);
		final University university3 = new University("University3", admin2);
		final University university4 = new University("University4", admin2);
		final University university5 = new University("University5", admin2);
		final University university6 = new University("University6", admin2);
		final University university7 = new University("University7", admin2);
		final University university8 = new University("University8", admin2);
		final University university9 = new University("University9", admin2);
		final University university10 = new University("University10", admin2);
		final University university11 = new University("University11", admin2);
		final University university12 = new University("University12", admin2);
		final University university13 = new University("University13", admin2);
		final University university14 = new University("University14", admin2);
		final University university15 = new University("University15", admin2);

		setDefaultImage(university1, university2, university3, university4, university5,
				university6, university7, university8, university9, university10, university11,
				university12, university13, university14, university15);
		saveUniversities(university1, university2, university3, university4, university5,
				university6, university7, university8, university9, university10, university11,
				university12, university13, university14, university15);

		// staffs
		final Staff staff1 = new Staff("staff1", "password",university1);
		final Staff staff2 = new Staff("staff2", "password", university2);
		final Staff staff3 = new Staff("staff3", "password", university2);
		final Staff staff4 = new Staff("staff4", "password", university2);
		final Staff staff5 = new Staff("staff5", "password", university2);
		final Staff staff6 = new Staff("staff6", "password", university2);
		final Staff staff7 = new Staff("staff7", "password", university2);
		final Staff staff8 = new Staff("staff8", "password", university2);
		final Staff staff9 = new Staff("staff9", "password", university2);
		final Staff staff10 = new Staff("staff10", "password", university2);
		final Staff staff11 = new Staff("staff11", "password");
		final Staff staff12 = new Staff("staff12", "password", university3);
		final Staff staff13 = new Staff("staff13", "password", university3);
		final Staff staff14 = new Staff("staff14", "password", university3);
		final Staff staff15 = new Staff("staff15", "password", university4);
		final Staff staff16 = new Staff("staff16", "password", university4);
		final Staff staff17 = new Staff("staff17", "password", university4);
		final Staff staff18 = new Staff("staff18", "password", university6);
		final Staff staff19 = new Staff("staff19", "password", university6);
		final Staff staff20 = new Staff("staff20", "password", university7);
		final Staff staff21 = new Staff("staff21", "password", university8);
		final Staff staff22 = new Staff("staff22", "password", university9);
		final Staff staff23 = new Staff("staff23", "password", university10);
		final Staff staff24 = new Staff("staff24", "password", university2);
		final Staff staff25 = new Staff("staff25", "password", university2);
		final Staff staff26 = new Staff("staff26", "password", university2);
		final Staff staff27 = new Staff("staff27", "password", university2);
		final Staff staff28 = new Staff("staff28", "password", university2);
		final Staff staff29 = new Staff("staff29", "password", university2);
		final Staff staff30 = new Staff("staff30", "password", university2);
		final Staff staff31 = new Staff("staff32", "password", university2);


		saveUsers(staff1, staff2, staff3, staff4, staff5, staff6, staff7, staff8, staff9, staff10,
				staff11, staff12, staff13, staff14, staff15, staff16, staff17, staff18, staff19,
				staff20, staff21, staff22, staff23, staff24, staff25, staff26, staff27, staff28,
				staff29, staff30, staff31);
		saveUniversities(university1, university2, university3, university4, university5,
				university6, university7, university8, university9, university10, university11,
				university12, university13, university14, university15);
		createProfiles(staff1, staff2, staff3, staff4, staff5, staff6, staff7, staff8, staff9,
				staff10, staff11, staff12, staff13, staff14, staff15, staff16, staff17, staff18,
				staff19, staff20, staff21, staff22, staff23, staff24, staff25, staff26, staff27,
				staff28, staff29, staff30, staff31);


		// courses
		final Course course1 = new Course("course1", staff1);
		final Course course2 = new Course("course2", staff2);
		final Course course3 = new Course("course3", staff3);
		final Course course4 = new Course("course4", staff1);
		final Course course5 = new Course("course5", staff1);
		final Course course6 = new Course("course6", staff1);
		final Course course7 = new Course("course7", staff4);
		final Course course8 = new Course("course8", staff5);
		final Course course9 = new Course("course9", staff6);
		final Course course10 = new Course("course10", staff7);
		final Course course11 = new Course("course11", staff8);
		final Course course12 = new Course("course12", staff4);
		final Course course13 = new Course("course13", staff23);
		final Course course14 = new Course("course14", staff12);
		final Course course15 = new Course("course15", staff3);
		final Course course16 = new Course("course16", staff3);
		final Course course17 = new Course("course17", staff3);
		final Course course18 = new Course("course18", staff3);
		final Course course19 = new Course("course19", staff3);
		final Course course20 = new Course("course20", staff3);
		final Course course21 = new Course("course21", staff3);
		final Course course22 = new Course("course22", staff17);
		final Course course23 = new Course("course23", staff7);
		final Course course24 = new Course("course24", staff7);
		final Course course25 = new Course("course25", staff7);
		final Course course26 = new Course("course26", staff7);
		final Course course27 = new Course("course27", staff7);
		final Course course28 = new Course("course28", staff7);
		final Course course29 = new Course("course29", staff1);
		final Course course30 = new Course("course30", staff1);
		final Course course31 = new Course("course31", staff1);
		final Course course32 = new Course("course32", staff1);
		final Course course33 = new Course("course33", staff1);
		final Course course34 = new Course("course34", staff1);
		final Course course35 = new Course("course35", staff1);
		final Course course36 = new Course("course36", staff1);
		final Course course37 = new Course("course37", staff1);
		final Course course38 = new Course("course38", staff1);
		final Course course39 = new Course("course39", staff1);
		setDefaultImage(course1, course2, course3, course4, course5, course6, course7, course8,
				course9, course10, course11, course12, course13, course14, course15, course16,
				course17, course16, course17, course18, course19, course20, course21, course22,
				course23, course24, course25, course26, course27, course28, course29, course30,
				course31, course32, course33, course34, course35, course36, course37, course38,
				course39);
		saveCourses(course1, course2, course3, course4, course5, course6, course7, course8,
				course9, course10, course11, course12, course13, course14, course15, course16,
				course17, course16, course17, course18, course19, course20, course21, course22,
				course23, course24, course25, course26, course27, course28, course29, course30,
				course31, course32, course33, course34, course35, course36, course37, course38,
				course39);
		saveUniversities(university1, university2, university3, university4, university5,
				university6, university7, university8, university9, university10, university11,
				university12, university13, university14, university15);

		
		joinCourse(user1, course1,course10,course3,course4,course5,course6,course7,course8,course9,course10);
		joinCourse(user2, course1,course10,course3,course4,course5,course6,course7,course8,course9,course10);
		joinCourse(user3, course1,course10,course3,course4,course5,course6,course7,course8,course9,course10);
		joinCourse(user4, course1,course10,course3,course21,course5,course6,course7,course8,course9,course10);
		joinCourse(user5, course1,course12,course3,course22,course5,course6,course7,course8,course9,course10);
		joinCourse(user6, course1,course16,course3,course4,course5,course6,course7,course8,course9,course10);
		joinCourse(user7, course1,course17,course3,course23,course5,course6,course7,course8,course9,course10);
		joinCourse(user8, course1,course19,course3,course22,course5,course6,course7,course8,course9,course10);
		joinCourse(user9, course1,course20,course3,course11,course5,course6,course7,course8,course9,course10);
		joinCourse(user10, course1,course10,course3,course4,course5,course6,course7,course8,course9,course10);
		joinCourse(user11, course1,course10,course3,course4,course5,course6,course7,course8,course9,course10);
		

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
