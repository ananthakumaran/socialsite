package com.socialsite.scrap;

import com.socialsite.BasePage;
import com.socialsite.user.UserInfoPanel;

/**
 * scrap page
 * 
 * @author Ananth
 */
public class ScrapPage extends BasePage
{

	public ScrapPage()
	{
		
		//user info
		add(new UserInfoPanel("userinfo"));
		
		// scrap list panel
		ScrapListPanel scrapListPanel;
		add(scrapListPanel = new ScrapListPanel("scraps"));
		
		// scrap form
		add(new SendScrapPanel("send", scrapListPanel.scrapListContainer));
	}

}
