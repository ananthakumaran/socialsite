package com.socialsite.authentication;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.hibernate.exception.ConstraintViolationException;

import com.socialsite.SocialSiteSession;
import com.socialsite.dao.ProfileDao;
import com.socialsite.dao.UserDao;
import com.socialsite.home.HomePage;
import com.socialsite.image.ImageService;
import com.socialsite.persistence.Profile;
import com.socialsite.persistence.User;

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
	/** Model object for email */
	private String				email;
	/** Feedback panel */
	private final FeedbackPanel	feedback;

	// file upload
	private FileUploadField		fileUploadField;

	/** Spring Dao to handle profile object */
	@SpringBean(name = "profileDao")
	private ProfileDao			profileDao;

	/** Spring Dao to handle user object */
	@SpringBean(name = "userDao")
	private UserDao				userDao;

	public SignUpPage()
	{
		// inject the spring resources
		InjectorHolder.getInjector().inject(this);
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
		form.add(fileUploadField = new FileUploadField("image"));
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

				// creates a user
				try
				{
					final User user = new User(userName, password);
					userDao.save(user);
					// The FileUpload object that will be provided by wicket
					// that holds info about
					// the file uploaded to the webapp
					final FileUpload fupload = fileUploadField.getFileUpload();
					if (fupload == null)
					{
						// No image was provided
						error("Please upload an image.");
						return;
					} else if (fupload.getSize() == 0)
					{
						error("The image you attempted to upload is empty.");
						return;
					} else if (!checkContentType(fupload.getContentType()))
					{
						getForm()
							.error(
								"Only images of types png, jpg, and gif are allowed.");
						return;
					} else
					{
						final ImageService imageService = new ImageService();

						// create a profile for the user
						final Profile userProfile = new Profile();
						userProfile.setUser(user);
						userProfile.setEmail(email);
						// set the image
						userProfile.setImage(imageService.resize(fupload
							.getBytes(), ImageService.IMAGE_SIZE));
						userProfile.setThumb(imageService.resize(fupload
							.getBytes(), ImageService.THUMB_SIZE));

						profileDao.save(userProfile);
						System.out.println(userName + password);
						User se = userDao.checkUserStatus(userName, password);
						if (se != null)
						{
							SocialSiteSession session = SocialSiteSession.get();
							session.setSessionUser(new SessionUser(
								user.getId(), SocialSiteRoles.ownerRole));
							session.setUserId(user.getId());
							setResponsePage(new HomePage());
						}
					}
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

	private boolean checkContentType(String contentType)
	{
		if (contentType.equalsIgnoreCase("image/gif")
				|| contentType.equalsIgnoreCase("image/jpeg")
				|| contentType.equalsIgnoreCase("image/png"))
		{
			return true;
		}
		return false;
	}

}
