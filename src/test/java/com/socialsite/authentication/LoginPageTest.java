package com.socialsite.authentication;

import org.apache.wicket.util.tester.FormTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.socialsite.util.SpringWicketTester;

public class LoginPageTest 
{

//	@Resource(name = "userDao")
//	private UserDao		userDao;

	SpringWicketTester	tester;

	@Before
	public void setup()
	{
		tester = new SpringWicketTester();
		tester.startPage(LoginPage.class);
	}

	@Test
	public void loginRenderTest()
	{
		tester.assertNoErrorMessage();
		tester.assertRenderedPage(LoginPage.class);
	}

	@Test
	public void loginFormWrongAuthenticationTest()
	{
		FormTester form = tester.newFormTester("loginform");

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
	@Ignore
	public void loginFormTest()
	{

//		User user = new User("aaa", "aaa");
//		userDao.save(user);
//		// flush the session so we can get the record using JDBC template
//		SessionFactoryUtils.getSession(sessionFactory, false).flush();
//		
//		assertNotNull("correct username and password", userDao.checkUserStatus(
//			"aaa", "aaa"));
////		
//		FormTester form = tester.newFormTester("loginform");
//
//		form.setValue("username", "aaa");
//		form.setValue("password", "aaa");
//
//		// TODO find the reason for the two submits
//		form.submit();
//		tester.clickLink("loginform:login");
//		
//		tester.assertNoErrorMessage();
//		tester.assertRenderedPage(HomePage.class);
		
	}

}
