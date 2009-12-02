package com.socialsite.search;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.value.ValueMap;

import com.socialsite.BasePanel;
import com.socialsite.SocialSiteSession;
import com.socialsite.authentication.SessionUser;
import com.socialsite.dao.UserDao;
import com.socialsite.home.HomePage;
import com.socialsite.persistence.User;

/**
 * @author Ananth
 */
public class SearchUserInfoPanel extends BasePanel
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@SpringBean(name = "userDao")
	private UserDao				userDao;

	public SearchUserInfoPanel(final String id, final IModel<User> model)
	{
		super(id, model);
		final User user = model.getObject();
		final ResourceReference imageResource = new ResourceReference(
			"userImageResource");
		add(new Image("userimage", imageResource, new ValueMap("id="
				+ user.getId())));
		// link to the home page
		final Link<User> home = new Link<User>("home", model)
		{

			/**
			 * 
			 */
			private static final long	serialVersionUID	= 1L;

			@Override
			public void onClick()
			{
				final User user = getModelObject();
				SocialSiteSession.get().setUserId(user.getId());
				final SessionUser sessionUser = SocialSiteSession.get()
					.getSessionUser();
				sessionUser.setRoles(userDao.getUsersRelation(user.getId(),
					sessionUser.getId()));
				setResponsePage(HomePage.class);
			}
		};
		add(home);
		home.add(new Label("name", user.getUserName()));
	}
}
