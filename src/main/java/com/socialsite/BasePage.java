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

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.UserDao;
import com.socialsite.persistence.User;

/**
 * BasePage for the socialsite Application
 * 
 * @author Ananth
 * 
 */
@AuthorizeInstantiation( { "USER", "FRIEND", "OWNER" })
public class BasePage extends WebPage
{
	private static final long serialVersionUID = 1L;

	protected HeaderPanel headerPanel;

	@SpringBean(name = "userDao")
	private UserDao<User> userDao;

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
