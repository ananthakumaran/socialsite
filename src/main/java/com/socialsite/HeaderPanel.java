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

package com.socialsite;

import java.util.Arrays;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.socialsite.authentication.LogoutPage;
import com.socialsite.course.UserCoursesPage;
import com.socialsite.entitymodel.StringWrapper;
import com.socialsite.friend.FriendsPage;
import com.socialsite.home.HomePage;
import com.socialsite.profile.ProfilePage;
import com.socialsite.search.SearchOption;
import com.socialsite.search.SearchPage;

/**
 * Header panel of the application
 * 
 * @author Ananth
 */
public class HeaderPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Model for search text box */
	public StringWrapper filter;

	/*
	 * construct
	 */
	public HeaderPanel(final String id)
	{
		super(id);

		add(new Link<Void>("home")
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				setUserId(SocialSiteSession.get().getSessionUser().getId());
				setResponsePage(HomePage.class);
			}

		});

		add(new Link<Void>("profile")
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				// TODO always show the session user profile
				setUserId(getUserId());
				setResponsePage(ProfilePage.class);
			}

		});

		add(new Link<Void>("friends")
		{

			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				setUserId(SocialSiteSession.get().getSessionUser().getId());
				setResponsePage(FriendsPage.class);
			}

		});

		add(new Link<Void>("courses")
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				setUserId(SocialSiteSession.get().getSessionUser().getId());
				setResponsePage(new UserCoursesPage());
			}

		});

		add(new BookmarkablePageLink<Object>("logout", LogoutPage.class));

		filter = new StringWrapper();
		SubmitLink search;
		final Form<Object> searchForm = new Form<Object>("searchform");
		add(searchForm);

		// search box
		searchForm.add(new TextField<String>("searchtextbox", new PropertyModel<String>(this,
				"filter.model")));

		// search options
		final DropDownChoice<SearchOption> searchOptions = new DropDownChoice<SearchOption>(
				"options", Arrays.asList(SearchOption.values()));
		searchOptions.setDefaultModel(new Model<SearchOption>(SearchOption.USER));
		searchForm.add(searchOptions);

		searchForm.add(search = new SubmitLink("search")
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit()
			{
				setResponsePage(new SearchPage(filter, searchOptions.getModelObject()));
			}
		});
		searchForm.setDefaultButton(search);

	}
}
