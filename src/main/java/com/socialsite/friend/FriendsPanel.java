package com.socialsite.friend;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.util.value.ValueMap;

import com.socialsite.BasePanel;
import com.socialsite.SocialSiteSession;
import com.socialsite.dataprovider.FriendsDataProvider;
import com.socialsite.persistence.User;
import com.socialsite.user.UserLink;

/**
 * @author Ananth
 */
public class FriendsPanel extends BasePanel
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public FriendsPanel(String id)
	{
		super(id);
		// friends data provider
		FriendsDataProvider friendsDataProvider = new FriendsDataProvider(
			SocialSiteSession.get().getUserId());

		DataView<User> friendList = new DataView<User>("friends",
			friendsDataProvider)
		{
			/**
			 * 
			 */
			private static final long	serialVersionUID	= 1L;

			@Override
			protected void populateItem(Item<User> item)
			{
				final User user = item.getModelObject();

				ResourceReference imageResource = new ResourceReference(
					"userImageResource");
				item.add(new Image("userthumb", imageResource, new ValueMap(
					"id=" + user.getId() + ",thumb=true")));
				Link<User> name;
				item.add(name = new UserLink("home", item.getModel()));
				name.add(new Label("username", item.getModelObject()
					.getUserName()));
			}

		};

		add(friendList);
	}
}
