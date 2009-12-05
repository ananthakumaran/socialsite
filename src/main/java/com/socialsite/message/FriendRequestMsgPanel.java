package com.socialsite.message;

import java.text.DateFormat;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

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
	private static final long	serialVersionUID	= 1L;

	/** spring Dao to handle user object */
	@SpringBean(name = "userDao")
	private UserDao<User>		userDao;

	@SpringBean(name = "messageDao")
	private MessageDao<Message>	messageDao;

	public FriendRequestMsgPanel(final String id,
			final FriendRequestMsg friendRequestMsg,
			final WebMarkupContainer container)
	{
		super(id);

		UserLink user;
		add(user = new UserLink("user", new Model<User>(friendRequestMsg
			.getSender())));
		user.add(new Label("name", friendRequestMsg.getSender().getUserName()));
		add(new Label("message", friendRequestMsg.getMessage()));
		add(new AjaxLink<FriendRequestMsg>("yes", new Model<FriendRequestMsg>(
			friendRequestMsg))
		{
			/**
		 * 
		 */
			private static final long	serialVersionUID	= 1L;

			@Override
			public void onClick(final AjaxRequestTarget target)
			{

				FriendRequestMsg friendRequestMsg = getModelObject();

				friendRequestMsg = (FriendRequestMsg) messageDao
					.load(friendRequestMsg.getId());

				// add him as the friend
				final User user = friendRequestMsg.getSender();

				user.addFriend(friendRequestMsg.getUser());
				// save the user
				userDao.save(user);

				// remove the friend request
				messageDao.delete(friendRequestMsg);

				// update the list
				target.addComponent(container);
			}
		});

		add(new AjaxLink<FriendRequestMsg>("no", new Model<FriendRequestMsg>(
			friendRequestMsg))
		{

			/**
			 * 
			 */
			private static final long	serialVersionUID	= 1L;

			@Override
			public void onClick(final AjaxRequestTarget target)
			{
				// remove the friend request
				messageDao.delete(getModelObject());

				// update the list
				target.addComponent(container);
			}
		});

		final DateFormat format = DateFormat.getDateTimeInstance(
			DateFormat.LONG, DateFormat.SHORT);

		add(new Label("time", format.format(friendRequestMsg.getTime())));

	}

}
