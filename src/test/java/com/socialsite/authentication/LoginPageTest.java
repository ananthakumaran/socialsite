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

import org.apache.wicket.util.tester.FormTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.socialsite.util.SpringWicketTester;

public class LoginPageTest
{

	// @Resource(name = "userDao")
	// private UserDao userDao;

	SpringWicketTester	tester;

	@Test
	@Ignore
	public void loginFormTest()
	{

		// User user = new User("aaa", "aaa");
		// userDao.save(user);
		// // flush the session so we can get the record using JDBC template
		// SessionFactoryUtils.getSession(sessionFactory, false).flush();
		//		
		// assertNotNull("correct username and password",
		// userDao.checkUserStatus(
		// "aaa", "aaa"));
		// //
		// FormTester form = tester.newFormTester("loginform");
		//
		// form.setValue("username", "aaa");
		// form.setValue("password", "aaa");
		//
		// // TODO find the reason for the two submits
		// form.submit();
		// tester.clickLink("loginform:login");
		//		
		// tester.assertNoErrorMessage();
		// tester.assertRenderedPage(HomePage.class);

	}

	@Test
	public void loginFormWrongAuthenticationTest()
	{
		final FormTester form = tester.newFormTester("loginform");

		form.setValue("username", "ananth");
		form.setValue("password", "path");

		// TODO find the reason for the two submits
		form.submit();
		tester.clickLink("loginform:login");

		tester
			.assertErrorMessages(new String[] { "Invalid username or password" });
		tester.assertRenderedPage(LoginPage.class);
	}

	@Test
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
