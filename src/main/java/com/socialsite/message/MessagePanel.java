package com.socialsite.message;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.SocialSiteSession;
import com.socialsite.dao.MessageDao;
import com.socialsite.dao.UserDao;
import com.socialsite.dataprovider.MessageDataProvider;
import com.socialsite.persistence.FriendRequestMsg;
import com.socialsite.persistence.Message;
import com.socialsite.persistence.User;
import com.socialsite.user.UserLink;

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
	private static final long	serialVersionUID	= 1L;

	/** container for the messages */
	WebMarkupContainer			messageContainer;

	

	

	/**
	 * constructor
	 * 
	 * @param id
	 *            id
	 */
	public MessagePanel(String id)
	{
		super(id);

		// container
		add(messageContainer = new WebMarkupContainer("container"));
		messageContainer.setOutputMarkupId(true);

		final MessageDataProvider MessageDataProvider = new MessageDataProvider(
			SocialSiteSession.get().getUserId());

		final DataView<Message> friendRequestDataView = new DataView<Message>(
			"messagelist", MessageDataProvider)
		{

			/**
			 * 
			 */
			private static final long	serialVersionUID	= 1L;

			@Override
			protected void populateItem(final Item<Message> item)
			{

				final Message message = item.getModelObject();
				if(message instanceof FriendRequestMsg)
				{
					item.add(new FriendRequestMsgPanel("message" , (FriendRequestMsg)message , messageContainer));
				}
				
			}
		};

		messageContainer.add(friendRequestDataView);

	}

}
