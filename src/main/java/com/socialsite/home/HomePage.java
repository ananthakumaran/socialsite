package com.socialsite.home;

import com.socialsite.BasePage;
import com.socialsite.friend.FriendRequestsPanel;
import com.socialsite.friend.FriendsPanel;
import com.socialsite.user.UserInfoPanel;

/**
 * user Home page
 * 
 * @author Ananth
 */
public class HomePage extends BasePage
{

	public HomePage()
	{
		add(new UserInfoPanel("userinfo"));
		add(new FriendRequestsPanel("friendrequest"));
		add(new FriendsPanel("friends"));
	}
}
