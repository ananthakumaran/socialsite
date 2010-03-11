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

package com.socialsite.home;

import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.panel.Panel;

import com.socialsite.BasePage;
import com.socialsite.SocialSiteSession;
import com.socialsite.course.CoursesPanel;
import com.socialsite.friend.FriendsPanel;
import com.socialsite.message.MessagePanel;
import com.socialsite.message.MessageSenderPanel;
import com.socialsite.user.UserInfoPanel;

/**
 * user Home page
 * 
 * @author Ananth
 */
public class HomePage extends BasePage
{

	/**
	 * constructor
	 * 
	 * Home page of the user
	 * 
	 */
	public HomePage()
	{
		// update the message panel after sending the message panel
		final Panel msgPanel = new MessagePanel("message");
		msgPanel.setOutputMarkupId(true);
		add(new UserInfoPanel("userinfo"));
		add(msgPanel);
		add(new FriendsPanel("friends"));
		add(new CoursesPanel("courses", SocialSiteSession.get().getUserId()));
		add(new MessageSenderPanel("sender", msgPanel));
	}

	@Override
	public void renderHead(final IHeaderResponse response)
	{
		super.renderHead(response);
		response.renderJavascriptReference("js/socialsite/home.js");
	}
}
