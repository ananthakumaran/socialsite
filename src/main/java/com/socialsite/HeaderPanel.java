package com.socialsite;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;

import com.socialsite.authentication.LogoutPage;
import com.socialsite.entitymodel.StringWrapper;
import com.socialsite.home.HomePage;
import com.socialsite.scrap.ScrapPage;
import com.socialsite.search.SearchPage;

/**
 * Header panel of the application
 * 
 * @author Ananth
 */
public class HeaderPanel extends BasePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Model for search text box */
	public StringWrapper filter;

	/*
	 * construct
	 */
	public HeaderPanel(String id) {
		super(id);

		add(new Link<Object>("home") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setUserId(SocialSiteSession.get().getSessionUser().getId());
				setResponsePage(HomePage.class);
			}

		});

		add(new Link<Object>("scrap") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setUserId(SocialSiteSession.get().getSessionUser().getId());
				setResponsePage(ScrapPage.class);
			}

		});

		add(new BookmarkablePageLink<Object>("logout", LogoutPage.class));

		filter = new StringWrapper();
		SubmitLink search;
		Form<Object> searchForm = new Form<Object>("searchform");
		add(searchForm);

		// search box
		searchForm.add(new TextField<String>("searchtextbox",
				new PropertyModel<String>(this, "filter.model")));
		searchForm.add(search = new SubmitLink("search") {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				setResponsePage(new SearchPage(filter));
			}
		});
		searchForm.setDefaultButton(search);

	}
}
