package com.socialsite.user;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.markup.html.PackageResource;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;

import com.socialsite.dao.ProfileDao;
import com.socialsite.persistence.User;

/**
 * 
 * creates a entry for a new user in the database and do all the other things
 * TODO: send a confirmation email
 * 
 * @author Ananth
 */
public class UserCreator
{

	/** Spring Dao to handle profile object */
	@SpringBean(name = "profileDao")
	private ProfileDao	profileDao;

	// /** Spring Dao to handle user object */
	// @SpringBean(name = "userDao")
	// private UserDao<User> userDao;

	public UserCreator(final User user)
	{
		// inject the spring resources
		InjectorHolder.getInjector().inject(this);

		final PackageResource imageRef = PackageResource.get(UserCreator.class,
			"user-150.png");
		final PackageResource iconRef = PackageResource.get(UserCreator.class,
			"user-100.png");

		// ResourceReference imageRef = new
		// ResourceReference(this.getClass(),"user-150.png");
		// ResourceReference iconRef = new
		// ResourceReference(this.getClass(),"user-100.png");

		try
		{
			System.out.println(user.getProfile().getEmail());
			user.getProfile().setImage(
				IOUtils.toByteArray(imageRef.getResourceStream()
					.getInputStream()));
			user.getProfile().setThumb(
				IOUtils.toByteArray(iconRef.getResourceStream()
					.getInputStream()));
		} catch (final IOException e)
		{
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
		} catch (final ResourceStreamNotFoundException e)
		{
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
		}

		profileDao.save(user.getProfile());

	}

	// final ImageService imageService = new ImageService();
	//
	//	
	//
	//	
	// // create a profile for the user
	// final Profile userProfile = new Profile();
	// userProfile.setUser(user);
	// userProfile.setEmail(email);
	// // set the image
	// userProfile.setImage(imageService.resize(fupload.getBytes(),
	// ImageService.IMAGE_SIZE));
	// userProfile.setThumb(imageService.resize(fupload.getBytes(),
	// ImageService.THUMB_SIZE));
	//	
	//	
	// ResourceReference rf = new ResourceReference("fs");
	//
	//	
	//	
	// IOUtils.toByteArray((rf.getResource().getResourceStream().getInputStream()));
	//	
	// ResourceReference rf = new ResourceReference("s");
	//	
	// ByteArrayOutputStream by = new ByteArrayOutputStream();
	//	
	//	
	//	
	//	
	//	
	// rf.getResource().getResourceStream().getInputStream().
	//	 
	//	
	//
	// profileDao.save(userProfile);
	// final User se = userDao.checkUserStatus(userName, password);
	// if (se != null)
	// {
	// final SocialSiteSession session = SocialSiteSession.get();
	// session.setSessionUser(new SessionUser(user.getId(),
	// SocialSiteRoles.ownerRole));
	// session.setUserId(user.getId());
	// setResponsePage(new HomePage());
	// }

	public void remove()
	{
		//		
		// // The FileUpload object that will be provided by wicket
		// // that holds info about
		// // the file uploaded to the webapp
		// final FileUpload fupload = fileUploadField.getFileUpload();
		// if (fupload == null)
		// {
		// // No image was provided
		// error("Please upload an image.");
		// return;
		// } else if (fupload.getSize() == 0)
		// {
		// error("The image you attempted to upload is empty.");
		// return;
		// } else if (!checkContentType(fupload.getContentType()))
		// {
		// getForm()
		// .error(
		// "Only images of types png, jpg, and gif are allowed.");
		// return;
		// } else
		// {
		//			
		// }

		// form.add(fileUploadField = new FileUploadField("image"));
	}

	// private boolean checkContentType(final String contentType)
	// {
	// if (contentType.equalsIgnoreCase("image/gif")
	// || contentType.equalsIgnoreCase("image/jpeg")
	// || contentType.equalsIgnoreCase("image/png"))
	// {
	// return true;
	// }
	// return false;
	// }

}
