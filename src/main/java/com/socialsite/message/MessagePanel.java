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

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;

import com.socialsite.SocialSiteSession;
import com.socialsite.dataprovider.MessageDataProvider;
import com.socialsite.persistence.FriendRequestMsg;
import com.socialsite.persistence.InfoMsg;
import com.socialsite.persistence.Message;

/**
 * list all the message for the user
 * 
 * @author Ananth
 * 
 */
public class MessagePanel extends Panel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** container for the messages */
	WebMarkupContainer messageContainer;

	/**
	 * constructor
	 * 
	 * @param id
	 *            id
	 */
	public MessagePanel(final String id)
	{
		super(id);

		// container
		add(messageContainer = new WebMarkupContainer("container"));
		messageContainer.setOutputMarkupId(true);

		final MessageDataProvider MessageDataProvider = new MessageDataProvider(SocialSiteSession
				.get().getUserId());

		final DataView<Message> friendRequestDataView = new DataView<Message>("messagelist",
				MessageDataProvider)
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final Item<Message> item)
			{

				final Message message = item.getModelObject();
				if (message instanceof FriendRequestMsg)
				{
					item.add(new FriendRequestMsgPanel("message", (FriendRequestMsg)message,
							messageContainer));
				}
				else if (message instanceof InfoMsg)
				{
					item.add(new InfoMsgPanel("message", new Model<InfoMsg>(((InfoMsg)message)),messageContainer));
				}

			}
		};

		messageContainer.add(friendRequestDataView);

	}

}
