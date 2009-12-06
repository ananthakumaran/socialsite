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

/**
 * 
 */
package com.socialsite.authentication;

import org.apache.wicket.util.tester.FormTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.socialsite.util.SpringWicketTester;

/**
 * @author Ananth
 * 
 */
public class SignUpPageTest
{

	SpringWicketTester	tester;

	@Test
	@Ignore
	public void different_Password_Test()
	{
		final FormTester form = tester.newFormTester("signupform");
		form.setValue("username", "ananth");
		form.setValue("password", "pass");
		form.setValue("re-password", "sdfas");
		form.setValue("email", "anatha@gmail.com");

		form.submit();

		// form.submitLink("signup", false);
		tester.assertErrorMessages(new String[] { "passwords should be same" });
	}

	@Before
	public void setup()
	{
		tester = new SpringWicketTester();
		tester.startPage(SignUpPage.class);

	}

	@Test
	public void signUpTest()
	{
		tester.assertNoErrorMessage();

		final FormTester form = tester.newFormTester("signupform");
		form.setValue("username", "ananth");
		form.setValue("password", "pass");
		form.setValue("re-password", "pass");
		form.setValue("email", "anatha@gmail.com");

		form.submit();
		tester.assertNoErrorMessage();

	}

}
