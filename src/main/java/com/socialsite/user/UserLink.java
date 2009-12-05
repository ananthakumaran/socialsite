package com.socialsite.user;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.SocialSiteSession;
import com.socialsite.dao.UserDao;
import com.socialsite.home.HomePage;
import com.socialsite.persistence.User;

/**
 * Redirects to the home page of the user
 * 
 * @author Ananth
 * 
 */
public class UserLink extends Link<User>
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/** spring dao to handle user object */
	@SpringBean(name = "userDao")
	private UserDao<User>		userDao;

	/**
	 * constructor
	 * 
	 * @param id
	 *            component id
	 * @param model
	 *            user model object
	 */
	public UserLink(final String id, final IModel<User> model)
	{
		super(id, model);
		InjectorHolder.getInjector().inject(this);
	}

	@Override
	public void onClick()
	{
		final SocialSiteSession session = SocialSiteSession.get();
		// set the user id
		session.setUserId(getModelObject().getId());
		// set the rules
		session.getSessionUser().setRoles(
			userDao.getUsersRelation(getModelObject().getId(), session
				.getSessionUser().getId()));
		// redirect to the home page
		setResponsePage(new HomePage());
	}
}
