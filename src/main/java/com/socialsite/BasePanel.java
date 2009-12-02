package com.socialsite;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.UserDao;

/**
 * BasePanel for the socialsite
 * 
 * @author Ananth
 */
public class BasePanel extends Panel
{

	private static final long	serialVersionUID	= 1L;

	/** spring dao to access the user object */
	@SpringBean(name = "userDao")
	private UserDao				userDao;

	/**
	 * constructor
	 * 
	 * @param id
	 *            id
	 */
	public BasePanel(final String id)
	{
		super(id);
	}

	/**
	 * constructor
	 * 
	 * @param id
	 *            id
	 * @param model
	 *            model
	 */
	public BasePanel(final String id, final IModel<?> model)
	{
		super(id, model);
	}

	/**
	 * set the user id in the session and also sets the roles in the session
	 * 
	 * @param userId
	 *            user id
	 */
	public void setUserId(final long userId)
	{
		final SocialSiteSession session = SocialSiteSession.get();
		// set the user id
		session.setUserId(userId);
		// set the roles
		session.getSessionUser().setRoles(
			userDao.getUsersRelation(userId, session.getSessionUser().getId()));
	}
}
