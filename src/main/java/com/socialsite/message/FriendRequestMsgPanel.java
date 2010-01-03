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

package com.socialsite.message;

import java.text.DateFormat;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.value.ValueMap;

import com.socialsite.dao.MessageDao;
import com.socialsite.dao.UserDao;
import com.socialsite.persistence.FriendRequestMsg;
import com.socialsite.persistence.Message;
import com.socialsite.persistence.User;
import com.socialsite.user.UserLink;

/**
 * @author Ananth
 */
@AuthorizeAction(action = Action.RENDER, roles = "OWNER")
public class FriendRequestMsgPanel extends Panel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** spring Dao to handle user object */
	@SpringBean(name = "userDao")
	private UserDao<User> userDao;

	@SpringBean(name = "messageDao")
	private MessageDao<Message> messageDao;

	public FriendRequestMsgPanel(final String id, final FriendRequestMsg friendRequestMsg,
			final WebMarkupContainer container)
	{
		super(id);

		UserLink<User> user;

		final ResourceReference imageResource = new ResourceReference("userImageResource");
		add(new Image("userthumb", imageResource, new ValueMap("id="
				+ friendRequestMsg.getSender().getId() + ",thumb=true")));

		add(user = new UserLink<User>("user", new Model<User>(friendRequestMsg.getSender())));
		user.add(new Label("name", friendRequestMsg.getSender().getUserName()));
		add(new Label("message", friendRequestMsg.getMessage()));
		add(new Link<FriendRequestMsg>("yes", new Model<FriendRequestMsg>(friendRequestMsg))
		{
			/**
		 * 
		 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{

				FriendRequestMsg friendRequestMsg = getModelObject();

				friendRequestMsg = (FriendRequestMsg)messageDao.load(friendRequestMsg.getId());

				// add him as the friend
				final User user = friendRequestMsg.getSender();

				user.addFriend(friendRequestMsg.getUsers().iterator().next());
				// save the user
				userDao.save(user);

				// remove the friend request
				messageDao.delete(friendRequestMsg);

			}
		});

		add(new AjaxLink<FriendRequestMsg>("no", new Model<FriendRequestMsg>(friendRequestMsg))
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(final AjaxRequestTarget target)
			{
				// remove the friend request
				messageDao.delete(getModelObject());

				// update the list
				target.addComponent(container);
			}
		});

		final DateFormat format = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT);

		add(new Label("time", format.format(friendRequestMsg.getTime())));

	}

}
