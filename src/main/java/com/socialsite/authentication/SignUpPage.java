package com.socialsite.authentication;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.wicket.markup.html.PackageResource;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.hibernate.exception.ConstraintViolationException;

import com.socialsite.SocialSiteSession;
import com.socialsite.dao.ProfileDao;
import com.socialsite.dao.UserDao;
import com.socialsite.home.HomePage;
import com.socialsite.persistence.Profile;
import com.socialsite.persistence.Student;
import com.socialsite.persistence.User;
import com.socialsite.user.UserCreator;

/**
 * 
 * TODO send a confirmation message to the email
 * 
 * sign up form
 * 
 * @author Ananth
 */
public class SignUpPage extends WebPage
{
	/** Model object for username */
	private String				userName;
	/** Model object for password */
	private String				password;
	/** Model object for password */
	private String				rePassword;

	/** Model object for email */
	private String				email;
	/** Feedback panel */
	private final FeedbackPanel	feedback;

	/** Spring Dao to handle profile object */
	@SpringBean(name = "profileDao")
	private ProfileDao			profileDao;

	/** Spring Dao to handle user object */
	@SpringBean(name = "userDao")
	private UserDao<User>		userDao;

	public SignUpPage()
	{

		// sign up form
		final Form<Object> form = new Form<Object>("signupform");
		add(form);
		form.add(new RequiredTextField<String>("username",
			new PropertyModel<String>(this, "userName")));
		form.add(new RequiredTextField<String>("email",
			new PropertyModel<String>(this, "email")).add(EmailAddressValidator
			.getInstance()));
		form.add(new PasswordTextField("password", new PropertyModel<String>(
			this, "password")));

		form.add(new PasswordTextField("re-password",
			new PropertyModel<String>(this, "rePassword")));

		SubmitLink signUp;
		form.add(signUp = new SubmitLink("signup")
		{

			/**
			 * 
			 */
			private static final long	serialVersionUID	= 1L;

			@Override
			public void onSubmit()
			{

				if (!password.equals(rePassword))
				{
					error("passwords should be same");
					return;
				}

				// creates a user
				try
				{
					final User user = new Student(userName, password);
					userDao.save(user);

					final Profile p = new Profile();
					p.setUser(user);

					p.setEmail(email);

					final PackageResource imageRef = PackageResource.get(
						UserCreator.class, "user-150.png");
					final PackageResource iconRef = PackageResource.get(
						UserCreator.class, "user-100.png");
					try
					{
						p.setImage(IOUtils.toByteArray(imageRef
							.getResourceStream().getInputStream()));
						p.setThumb(IOUtils.toByteArray(iconRef
							.getResourceStream().getInputStream()));
					} catch (final IOException e)
					{
						Logger.getLogger(getClass().getName()).log(
							Level.SEVERE, null, e);
					} catch (final ResourceStreamNotFoundException e)
					{
						Logger.getLogger(getClass().getName()).log(
							Level.SEVERE, null, e);
					}

					profileDao.save(p);

					final SocialSiteSession session = SocialSiteSession.get();
					session.setSessionUser(new SessionUser(user.getId(),
						SocialSiteRoles.ownerRole));
					session.setUserId(user.getId());
					setResponsePage(new HomePage());

				} catch (final ConstraintViolationException ex)
				{
					// updates the feedback panel
					error("username already exists");
					// target.addComponent(feedback);
					return;
				}
			}
		});
		// submit the form on return key
		form.setDefaultButton(signUp);

		// feedback panel
		feedback = new FeedbackPanel("feedback");
		feedback.setOutputMarkupId(true);
		add(feedback);
	}

}
