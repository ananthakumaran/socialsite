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

package com.socialsite.authentication;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.apache.wicket.util.tester.FormTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.transaction.annotation.Transactional;

import com.socialsite.dao.AbstractDaoTest;
import com.socialsite.dao.UserDao;
import com.socialsite.home.HomePage;
import com.socialsite.persistence.Student;
import com.socialsite.persistence.User;
import com.socialsite.util.SpringWicketTester;

public class LoginPageTest extends AbstractDaoTest
{

	@Resource(name = "userDao")
	private UserDao<User>	userDao;

	SpringWicketTester		tester;

	@Test
	@Transactional
	@Ignore
	public void loginFormTest()
	{

		final User user = new Student("student", "password");
		userDao.save(user);
		// flush the session so we can get the record using JDBC template
		SessionFactoryUtils.getSession(sessionFactory, false).flush();

		assertNotNull("correct username and password", userDao.checkUserStatus(
			"student", "password"));
		//
		final FormTester form = tester.newFormTester("loginform");

		form.setValue("username", "student");
		form.setValue("password", "password");

		form.submit("login");

		tester.assertNoErrorMessage();
		tester.assertRenderedPage(HomePage.class);

	}

	@Test
	@Transactional
	public void loginFormWrongAuthenticationTest()
	{
		final FormTester form = tester.newFormTester("loginform");

		form.setValue("username", "ananthas");
		form.setValue("password", "pathasfdasf");

		// TODO find the reason for the two submits
		form.submit("login");
		tester
			.assertErrorMessages(new String[] { "Invalid username or password" });
		tester.assertRenderedPage(LoginPage.class);
	}

	@Test
	@Transactional
	public void loginRenderTest()
	{
		tester.assertNoErrorMessage();
		tester.assertRenderedPage(LoginPage.class);
	}

	@Before
	public void setup()
	{
		tester = new SpringWicketTester();
		tester.startPage(LoginPage.class);
	}

}
