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

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.socialsite.dao.UserDao;
import com.socialsite.home.HomePage;
import com.socialsite.persistence.Student;
import com.socialsite.persistence.User;
import com.socialsite.util.SpringWicketTester;


public class LoginPageTest
{

	@SpringBean(name = "userDao")
	private UserDao<User> userDao;

	SpringWicketTester tester;

	@Before
	public void setupTest()
	{

		tester = new SpringWicketTester();
		tester.startPage(LoginPage.class);
	}

	@Test
	@Ignore
	public void testLoginForm()
	{
		// FIXME need to rollback

		InjectorHolder.getInjector().inject(this);
		final User user = new Student("student", "password");
		userDao.save(user);

		assertNotNull("correct username and password", userDao.checkUserStatus("student",
				"password"));
		//
		final FormTester form = tester.newFormTester("loginform");

		form.setValue("username", "student");
		form.setValue("password", "password");

		form.submit("login");

		tester.assertNoErrorMessage();
		tester.assertRenderedPage(HomePage.class);

	}


	@Test
	public void testLoginFormWrongAuthentication()
	{
		final FormTester form = tester.newFormTester("loginform");

		form.setValue("username", "ananthas");
		form.setValue("password", "pathasfdasf");

		form.submit("login");
		tester.assertErrorMessages(new String[] { "Invalid username or password" });
		tester.assertRenderedPage(LoginPage.class);
	}

	@Test
	public void testLoginRender()
	{
		tester.assertNoErrorMessage();
		tester.assertRenderedPage(LoginPage.class);
	}

}
