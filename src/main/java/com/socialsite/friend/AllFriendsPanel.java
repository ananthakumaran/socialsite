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

package com.socialsite.friend;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;

import com.socialsite.BasePanel;
import com.socialsite.SocialSiteSession;
import com.socialsite.dataprovider.FriendsDataProvider;
import com.socialsite.image.ImagePanel;
import com.socialsite.image.ImageType;
import com.socialsite.persistence.User;
import com.socialsite.user.UserLink;

/**
 * @author Ananth
 */
public class AllFriendsPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AllFriendsPanel(String id)
	{
		super(id);
		// friends data provider
		final FriendsDataProvider friendsDataProvider = new FriendsDataProvider(SocialSiteSession
				.get().getUserId());

		final DataView<User> friendList = new DataView<User>("friends", friendsDataProvider, 9)
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final Item<User> item)
			{
				final User user = item.getModelObject();
				UserLink<User> userImageLink;
				item.add(userImageLink = new UserLink<User>("imagelink", item.getModel()));
				userImageLink.add(new ImagePanel("user", user.getId(), ImageType.USER, user
						.getLastModified(), false, false));
				Link<User> name;
				item.add(name = new UserLink<User>("home", item.getModel()));
				name.add(new Label("username", item.getModelObject().getUserName()));

				item.add(new Label("city", user.getProfile().getCurrentCity().getValue()));
				item.add(new Label("sex", user.getProfile().getSex()));

			}

		};

		add(friendList);
	}

}
