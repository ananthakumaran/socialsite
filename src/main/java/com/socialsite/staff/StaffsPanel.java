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

package com.socialsite.staff;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;

import com.socialsite.BasePanel;
import com.socialsite.dataprovider.StaffDataProvider;
import com.socialsite.friend.FriendsPage;
import com.socialsite.image.ImagePanel;
import com.socialsite.image.ImageType;
import com.socialsite.persistence.Staff;
import com.socialsite.persistence.University;
import com.socialsite.user.UserLink;
import com.socialsite.util.ShowAllLink;

/**
 * @author Ananth
 */
public class StaffsPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StaffsPanel(final String id, final University university)
	{
		super(id);
		// friends data provider
		final StaffDataProvider staffDataProvider = new StaffDataProvider(university);

		final DataView<Staff> friendList = new DataView<Staff>("staffs", staffDataProvider, 9)
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final Item<Staff> item)
			{
				final Staff staff = item.getModelObject();
				UserLink<Staff> userImageLink;
				item.add(userImageLink = new UserLink<Staff>("imagelink", item.getModel()));
				userImageLink.add(new ImagePanel("userthumb", staff.getId(), ImageType.USER, staff
						.getLastModified(), true));
				Link<Staff> name;
				item.add(name = new UserLink<Staff>("home", item.getModel()));
				name.add(new Label("username", item.getModelObject().getUserName()));
			}

		};
		add(new ShowAllLink<Staff>("showall", friendList.getDataProvider().size(),
				FriendsPage.class));
		add(friendList);

	}
}
