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

package com.socialsite;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.socialsite.authentication.LoginPageTest;
import com.socialsite.authentication.SignUpPageTest;
import com.socialsite.dao.hibernate.AdminDaoTest;
import com.socialsite.dao.hibernate.AnswerDaoTest;
import com.socialsite.dao.hibernate.CommentDaoTest;
import com.socialsite.dao.hibernate.CourseDaoTest;
import com.socialsite.dao.hibernate.FriendRequestMsgDaoTest;
import com.socialsite.dao.hibernate.InfoMsgDaoTest;
import com.socialsite.dao.hibernate.MessageDaoTest;
import com.socialsite.dao.hibernate.ProfileDaoTest;
import com.socialsite.dao.hibernate.QuestionDaoTest;
import com.socialsite.dao.hibernate.ScrapDaoTest;
import com.socialsite.dao.hibernate.StaffDaoTest;
import com.socialsite.dao.hibernate.StudentDaoTest;
import com.socialsite.dao.hibernate.UniversityDaoTest;
import com.socialsite.dao.hibernate.UserDaoTest;
import com.socialsite.scripts.SchemaCreator;

/**
 * Test suite for all the tests
 * 
 * @author Ananth
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { LoginPageTest.class, SignUpPageTest.class, ScrapDaoTest.class,
		UserDaoTest.class, ProfileDaoTest.class, FriendRequestMsgDaoTest.class,
		UniversityDaoTest.class, CourseDaoTest.class, AdminDaoTest.class, AnswerDaoTest.class,
		CommentDaoTest.class, InfoMsgDaoTest.class, MessageDaoTest.class, QuestionDaoTest.class,
		StaffDaoTest.class, StudentDaoTest.class })
public class AllTests
{

	@BeforeClass
	public static void setup()
	{
		// clean the database before running the tests
		SchemaCreator.create();
	}
	// run thisclass to run all the test
}
