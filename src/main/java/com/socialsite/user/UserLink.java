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
public class UserLink<T extends User> extends Link<T>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** spring dao to handle user object */
	@SpringBean(name = "userDao")
	private UserDao<User> userDao;

	/**
	 * constructor
	 * 
	 * @param id
	 *            component id
	 * @param model
	 *            user model object
	 */
	public UserLink(final String id, final IModel<T> model)
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
				userDao
						.getUsersRelation(getModelObject().getId(), session.getSessionUser()
								.getId()));
		// redirect to the home page
		setResponsePage(new HomePage());
	}
}
