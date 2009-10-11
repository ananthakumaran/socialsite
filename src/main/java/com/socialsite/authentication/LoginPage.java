package com.socialsite.authentication;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.SocialSiteSession;
import com.socialsite.dao.UserDao;
import com.socialsite.home.HomePage;
import com.socialsite.persistence.User;

/**
 * login page of the application
 * 
 * @author Ananth
 */
public class LoginPage extends WebPage
{
	/** Model for the username */
	private String	userName;
	/** Model for the password */
	private String	password;
	/** Spring Dao to handle user object */
	@SpringBean(name = "userDao")
	private UserDao	userDao;

	public LoginPage()
	{
		// intialize the spring DAO
		InjectorHolder.getInjector().inject(this);

		final StatelessForm<Object> form = new StatelessForm<Object>(
			"loginform");
		add(form);

		form.add(new RequiredTextField<String>("username",
			new PropertyModel<String>(this, "userName")));
		form.add(new PasswordTextField("password", new PropertyModel<String>(
			this, "password")));
		SubmitLink login;
		form.add(login = new SubmitLink("login")
		{

			/**
			 * 
			 */
			private static final long	serialVersionUID	= 1L;

			@Override
			public void onSubmit()
			{
				User user = userDao.checkUserStatus(userName, password);
				if (user == null)
				{
					error("Invalid username or password");
					return;
				}
				SocialSiteSession session = SocialSiteSession.get();
				session.setSessionUser(new SessionUser(user.getId(),
					SocialSiteRoles.ownerRole));
				session.setUserId(user.getId());
				setResponsePage(new HomePage());
			}
		});
		// submits the form when the user hit return
		form.setDefaultButton(login);

		// feedback panel
		add(new FeedbackPanel("feedback"));

	}
}
