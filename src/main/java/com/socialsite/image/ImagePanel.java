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

package com.socialsite.image;

import java.util.Random;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.value.ValueMap;

import com.socialsite.ajax.fileupload.UploadPanel;
import com.socialsite.dao.ProfileDao;
import com.socialsite.persistence.User;

/**
 * @author Ananth
 */
public class ImagePanel extends Panel
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/** Feedback panel */
	// private final FeedbackPanel feedback;

	@SpringBean(name = "profileDao")
	private ProfileDao			profileDao;

	public ImagePanel(final String id, final User user)
	{
		super(id);

		// allow the modal window to update the panel
		this.setOutputMarkupId(true);
		final ResourceReference imageResource = new ResourceReference(
			"userImageResource");
		final Image userImage;
		add(userImage = new Image("userimage", imageResource, new ValueMap(
			"id=" + user.getId())));
		userImage.setOutputMarkupId(true);

		final ModalWindow modal;
		add(modal = new ModalWindow("modal"));

		modal.setContent(new UploadPanel(modal.getContentId())
		{

			/**
			 * 
			 */
			private static final long	serialVersionUID	= 1L;

			@Override
			public void onUploadFinished(AjaxRequestTarget target,
					String filename, String newFileUrl)
			{
				
				final ResourceReference imageResource = new ResourceReference(
					"userImageResource");

				Random rand = new Random();

				userImage.setImageResourceReference(imageResource,
					new ValueMap("id=" + user.getId() + ",rand="
							+ rand.nextLong()));
				target.addComponent(userImage);
			}

			@Override
			public String onFileUploaded(FileUpload upload)
			{

				if (upload == null)
				{
					// No image was provided
					error( "Please upload an image.");
				} else if (upload.getSize() == 0)
				{
					error("Please upload an image.");
				} else if (!checkContentType(upload.getContentType()))
				{
					error("Only images of types png, jpg, and gif are allowed.");
				} else
				{
					final ImageService imageService = new ImageService();

					user.getProfile().setThumb(
						imageService.resize(upload.getBytes(),
							ImageService.THUMB_SIZE));
					user.getProfile().setImage(
						imageService.resize(upload.getBytes(),
							ImageService.IMAGE_SIZE));
					profileDao.save(user.getProfile());
				}

				return null;
			}
		});
		modal.setTitle(" Select the image ");
		modal.setCookieName("modal");

		modal.setCloseButtonCallback(new ModalWindow.CloseButtonCallback()
		{
			/**
			 * 
			 */
			private static final long	serialVersionUID	= 1L;

			public boolean onCloseButtonClicked(AjaxRequestTarget target)
			{
				return true;
			}
		});

		modal.setWindowClosedCallback(new ModalWindow.WindowClosedCallback()
		{
			/**
			 * 
			 */
			private static final long	serialVersionUID	= 1L;

			public void onClose(AjaxRequestTarget target)
			{
			}
		});

		add(new AjaxLink("changeimage")
		{
			/**
			 * 
			 */
			private static final long	serialVersionUID	= 1L;

			@Override
			public void onClick(AjaxRequestTarget target)
			{
				modal.show(target);
			}
		});
	}

	private boolean checkContentType(final String contentType)
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
