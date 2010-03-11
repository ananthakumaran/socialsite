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

package com.socialsite.user;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.SocialSiteSession;
import com.socialsite.dao.ProfileDao;
import com.socialsite.dao.UserDao;
import com.socialsite.friend.AddAsFriendPanel;
import com.socialsite.image.ImagePanel;
import com.socialsite.image.ImageService;
import com.socialsite.image.ImageType;
import com.socialsite.persistence.Profile;
import com.socialsite.persistence.User;

/**
 * @author Ananth
 */
public class UserInfoPanel extends Panel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** spring dao to handle user object */
	@SpringBean(name = "userDao")
	private UserDao<User> userDao;

	/** spring dao to handle profile object */
	@SpringBean(name = "profileDao")
	private ProfileDao profileDao;


	public UserInfoPanel(final String id)
	{
		super(id);

		final User user = userDao.load(SocialSiteSession.get().getUserId());

		add(new ImagePanel("userimage", user.getId(), ImageType.USER, user.getLastModified())
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void saveImage(final byte[] imageData)
			{
				final ImageService imageService = new ImageService();

				user.getProfile().changeThumb(
						imageService.resize(imageData, ImageService.THUMB_SIZE));
				user.getProfile().changeImage(
						imageService.resize(imageData, ImageService.IMAGE_SIZE));
				profileDao.save(user.getProfile());
				userDao.save(user);

			}
		});

		final Profile profile = user.getProfile();

		add(new Label("username", user.getUserName()));
		add(new Label("city", profile.getCurrentCity().getValue()));
		add(new Label("status", profile.getRelationshipStatus()));
		add(new Label("aboutme", profile.getAboutMe()));
		add(new AddAsFriendPanel("addasfriend"));
	}
}
