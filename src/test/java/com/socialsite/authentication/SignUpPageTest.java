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
