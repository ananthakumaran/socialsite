package com.socialsite.search;

import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;

import com.socialsite.BasePage;
import com.socialsite.dataprovider.SearchDataProvider;
import com.socialsite.entitymodel.StringWrapper;
import com.socialsite.persistence.User;
import com.socialsite.user.UserInfoPanel;

/**
 * search page
 * 
 * @author Ananth
 */
public class SearchPage extends BasePage
{

	/** search filter **/
	private StringWrapper	filter;

	/**
	 * constructor
	 * 
	 * @param searchText
	 *            search text
	 */
	public SearchPage(StringWrapper filter)
	{
		// intialize the filter
		this.filter = filter;

		// add the user info panel
		add(new UserInfoPanel("userinfo"));

		SearchDataProvider searchDataProvider = new SearchDataProvider(this.filter);

		DataView<User> searchList = new DataView<User>("searchlist",
			searchDataProvider, 10)
		{

			/**
			 * 
			 */
			private static final long	serialVersionUID	= 1L;

			@Override
			protected void populateItem(Item<User> item)
			{
				item
					.add(new SearchUserInfoPanel("userdetails", item.getModel()));
			}
		};

		add(new PagingNavigator("searchpagination", searchList));
		add(searchList);
	}
}
