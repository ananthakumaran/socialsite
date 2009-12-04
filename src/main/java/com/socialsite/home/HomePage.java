package com.socialsite.home;

import com.socialsite.BasePage;
import com.socialsite.friend.FriendsPanel;
import com.socialsite.message.MessagePanel;
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
		add(new MessagePanel("message"));
		add(new FriendsPanel("friends"));
	}
}
