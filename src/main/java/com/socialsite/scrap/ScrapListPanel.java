package com.socialsite.scrap;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;

import com.socialsite.BasePanel;
import com.socialsite.SocialSiteSession;
import com.socialsite.dataprovider.ScrapDataProvider;
import com.socialsite.persistence.Scrap;
import com.socialsite.persistence.User;
import com.socialsite.user.UserLink;

/**
 * @author Ananth
 */
public class ScrapListPanel extends BasePanel
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/** container for the scraplist **/
	public WebMarkupContainer	scrapListContainer;

	public ScrapListPanel(String id)
	{
		super(id);

		// container
		scrapListContainer = new WebMarkupContainer("container");
		scrapListContainer.setOutputMarkupId(true);
		add(scrapListContainer);

		ScrapDataProvider scrapDataProvider = new ScrapDataProvider(SocialSiteSession.get().getUserId());
		
		DataView<Scrap> scrapDataView = new DataView<Scrap>("scrap" ,scrapDataProvider , 10)
		{

			/**
			 * 
			 */
			private static final long	serialVersionUID	= 1L;

			@Override
			protected void populateItem(Item<Scrap> item)
			{
				// model 
				Scrap scrap = item.getModelObject();
				User author = scrap.getAuthor();
				
				// link to the author page
				UserLink userLink; 
				item.add(userLink = new UserLink("user" , new Model<User>(author)));
				userLink.add(new Label("name" , author.getUserName()));
				
				// message
				item.add(new Label("message" , scrap.getMessage()));
				
				// add reply form
				
				// delete link
				
				
			}
		};
		
		scrapListContainer.add(scrapDataView);
	}
}
