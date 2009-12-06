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

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.wicket.markup.html.PackageResource;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.wicketstuff.validation.client.ClientAndServerLengthBetweenValidatingBehavior;
import org.wicketstuff.validation.client.ClientAndServerMaximumLengthValidatingBehavior;
import org.wicketstuff.validation.client.ClientAndServerRequiredValidatingBehavior;
import org.wicketstuff.validation.client.ClientAndServerValidatingFeedbackBehavior;

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
		
		
		TextField<String> username =new TextField<String>("username",
				new PropertyModel<String>(this, "userName"));
		username.add(new ClientAndServerRequiredValidatingBehavior(form));
		username.add(new ClientAndServerMaximumLengthValidatingBehavior(form, 16));
		
		
		form.add(username);
		
		TextField<String> emailTextField = new TextField<String>("email",new PropertyModel<String>(this, "email"));
		emailTextField.add(new ClientAndServerRequiredValidatingBehavior(form));
	//	emailTextField.add(new ClientAndServerEmailAddressValidator(form));
		
		form.add(emailTextField);
		
		PasswordTextField passwordTextField = new PasswordTextField("password",new PropertyModel<String>(this, "password"));
		passwordTextField.add(new ClientAndServerRequiredValidatingBehavior(form));
		passwordTextField.add(new ClientAndServerLengthBetweenValidatingBehavior(form,6,16));
		
		form.add(passwordTextField);
		
		PasswordTextField rePasswordTextField = new PasswordTextField("re-password",new PropertyModel<String>(this, "rePassword"));
		rePasswordTextField.add(new ClientAndServerRequiredValidatingBehavior(form));
		rePasswordTextField.add(new ClientAndServerLengthBetweenValidatingBehavior(form,6,16));

		form.add(rePasswordTextField);

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
						UserCreator.class, "user-125.png");
					final PackageResource iconRef = PackageResource.get(
						UserCreator.class, "user-75.png");
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
		feedback.add(new ClientAndServerValidatingFeedbackBehavior(form));
		feedback.setOutputMarkupId(true);
		add(feedback);
	}
}
