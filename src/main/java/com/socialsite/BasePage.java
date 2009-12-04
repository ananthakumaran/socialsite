package com.socialsite;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.UserDao;

/**
 * BasePage for the socialsite Application
 * 
 * @author Ananth
 * 
 */
@AuthorizeInstantiation( { "USER", "FRIEND", "OWNER" })
public class BasePage extends WebPage
{
	private static final long	serialVersionUID	= 1L;

	/** spring dao to access the user object */
	@SpringBean(name = "userDao")
	private UserDao				userDao;

	protected HeaderPanel		headerPanel;

	/**
	 * Constructor
	 */
	public BasePage()
	{
		this(null);
	}

	/**
	 * Constructor.
	 * 
	 * @param model
	 */
	public BasePage(final IModel<?> model)
	{
		super(model);
		// header panel
		add(headerPanel = new HeaderPanel("header"));

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
