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

package com.socialsite.search;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.value.ValueMap;

import com.socialsite.BasePanel;
import com.socialsite.SocialSiteSession;
import com.socialsite.authentication.SessionUser;
import com.socialsite.dao.UserDao;
import com.socialsite.home.HomePage;
import com.socialsite.image.ImageType;
import com.socialsite.persistence.Profile;
import com.socialsite.persistence.User;

/**
 * @author Ananth
 */
public class SearchUserInfoPanel extends BasePanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SpringBean(name = "userDao")
	private UserDao<User> userDao;

	public SearchUserInfoPanel(final String id, final IModel<User> model)
	{
		super(id, model);
		final User user = model.getObject();
		final ResourceReference imageResource = new ResourceReference(ImageType.USER.name());
		add(new Image("userimage", imageResource, new ValueMap("id=" + user.getId())));
		// link to the home page
		final Link<User> home = new Link<User>("home", model)
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				final User user = getModelObject();
				SocialSiteSession.get().setUserId(user.getId());
				final SessionUser sessionUser = SocialSiteSession.get().getSessionUser();
				sessionUser.setRoles(userDao.getUsersRelation(user.getId(), sessionUser.getId()));
				setResponsePage(HomePage.class);
			}
		};
		add(home);
		final Profile profile = user.getProfile();
		home.add(new Label("name", user.getUserName()));
		add(new Label("city", profile.getCurrentCity().getValue()));
		add(new Label("sex", profile.getSex()));
	}
}
