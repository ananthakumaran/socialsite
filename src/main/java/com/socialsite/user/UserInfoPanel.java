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
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.SocialSiteSession;
import com.socialsite.dao.UserDao;
import com.socialsite.friend.AddAsFriendPanel;
import com.socialsite.image.ImagePanel;
import com.socialsite.persistence.User;
import com.socialsite.scrap.ScrapPage;

/**
 * @author Ananth
 */
public class UserInfoPanel extends Panel
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	/** spring dao to handle user object */
	@SpringBean(name = "userDao")
	private UserDao<User>		userDao;

	public UserInfoPanel(final String id)
	{
		super(id);

		final User user = userDao.load(SocialSiteSession.get().getUserId());
		
		add(new ImagePanel("userimage", user).setOutputMarkupId(true));
		
		add(new Label("username", user.getUserName()));
		add(new Link<Object>("scrap")
		{

			/**
			 * 
			 */
			private static final long	serialVersionUID	= 1L;

			@Override
			public void onClick()
			{
				setResponsePage(ScrapPage.class);
			}

		});
		add(new AddAsFriendPanel("addasfriend"));
	}
}
