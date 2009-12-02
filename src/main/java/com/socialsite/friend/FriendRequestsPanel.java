package com.socialsite.friend;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.BasePanel;
import com.socialsite.SocialSiteSession;
import com.socialsite.dao.FriendRequestDao;
import com.socialsite.dao.UserDao;
import com.socialsite.dataprovider.FriendRequestsDataProvider;
import com.socialsite.persistence.FriendRequest;
import com.socialsite.persistence.User;
import com.socialsite.user.UserLink;

/**
 * shows all the friend requests
 * 
 * @author Ananth
 */
@AuthorizeAction(action = Action.RENDER, roles = "OWNER")
public class FriendRequestsPanel extends BasePanel
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/** container for the friend requests */
	WebMarkupContainer			friendRequestContainer;

	/** spring Dao to handle user object */
	@SpringBean(name = "userDao")
	private UserDao				userDao;
	/** spring Dao to handle friendrequest object */
	@SpringBean(name = "friendRequestDao")
	private FriendRequestDao	friendRequestDao;

	public FriendRequestsPanel(final String id)
	{
		super(id);
		// inject all the spring beans
		InjectorHolder.getInjector().inject(this);

		// container
		add(friendRequestContainer = new WebMarkupContainer("container"));
		friendRequestContainer.setOutputMarkupId(true);

		final FriendRequestsDataProvider friendRequestDataProvider = new FriendRequestsDataProvider(
			SocialSiteSession.get().getUserId());

		final DataView<FriendRequest> friendRequestDataView = new DataView<FriendRequest>(
			"friendrequest", friendRequestDataProvider)
		{

			/**
			 * 
			 */
			private static final long	serialVersionUID	= 1L;

			@Override
			protected void populateItem(final Item<FriendRequest> item)
			{

				final FriendRequest friendRequest = item.getModelObject();

				UserLink user;
				item.add(user = new UserLink("user", new Model<User>(
					friendRequest.getUser())));
				user.add(new Label("name", friendRequest.getUser()
					.getUserName()));
				item.add(new Label("message", friendRequest.getMessage()));
				item.add(new AjaxLink<FriendRequest>("yes", item.getModel())
				{

					/**
					 * 
					 */
					private static final long	serialVersionUID	= 1L;

					@Override
					public void onClick(final AjaxRequestTarget target)
					{
						final FriendRequest friendRequest = getModelObject();

						// add him as the friend
						final User user = friendRequest.getUser();
						user.addFriend(friendRequest.getFriend());
						// save the user
						userDao.save(user);

						// remove the friend request
						friendRequestDao.delete(friendRequest);

						// update the list
						target.addComponent(friendRequestContainer);
					}
				});

				item.add(new AjaxLink<FriendRequest>("no", item.getModel())
				{

					/**
						 * 
						 */
					private static final long	serialVersionUID	= 1L;

					@Override
					public void onClick(final AjaxRequestTarget target)
					{
						// remove the friend request
						friendRequestDao.delete(getModelObject());

						// update the list
						target.addComponent(friendRequestContainer);
					}
				});
			}

		};

		friendRequestContainer.add(friendRequestDataView);

	}

}
