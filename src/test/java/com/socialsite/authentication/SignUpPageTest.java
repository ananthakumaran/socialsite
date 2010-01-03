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

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.socialsite.util.SpringWicketTester;

/**
 * @author Ananth
 * 
 */
public class SignUpPageTest
{

	SpringWicketTester tester;

	@Test
	@Transactional
	public void different_Password_Test()
	{
		final FormTester form = tester.newFormTester("signupform");
		form.setValue("username", "ananth");
		form.setValue("password", "password");
		form.setValue("re-password", "sdfasasdfas");
		form.setValue("email", "anatha@gmail.com");

		form.submit("signup");

		tester.assertErrorMessages(new String[] { "passwords should be same" });
	}

	@Before
	public void setup()
	{
		tester = new SpringWicketTester();
		tester.startPage(SignUpPage.class);

	}

	@Test
	@Transactional
	@Ignore
	public void signUpTest()
	{
		tester.assertNoErrorMessage();
		InjectorHolder.getInjector().inject(this);

		final FormTester form = tester.newFormTester("signupform");
		form.setValue("username", "ananth");
		form.setValue("password", "password");
		form.setValue("re-password", "password");
		form.setValue("email", "anatha@gmail.com");
		// FIXME don't know how to set the radio group
		form.setValue("usertype", "0:signupform:usertype:0");

		form.submit("signup");
		tester.assertNoErrorMessage();

	}

}
