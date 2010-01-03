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

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.UserDao;
import com.socialsite.persistence.User;

/**
 * BasePanel for the socialsite
 * 
 * @author Ananth
 */
public class BasePanel extends Panel implements IHeaderContributor
{

	private static final long serialVersionUID = 1L;

	/** spring dao to access the user object */
	@SpringBean(name = "userDao")
	private UserDao<User> userDao;

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
	 * gets the id of the session user
	 * 
	 * @return id of the session user
	 */
	public long getSessionUserId()
	{
		return SocialSiteSession.get().getSessionUser().getId();
	}

	public void renderHead(final IHeaderResponse response)
	{

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

	/**
	 * gets the domain model object of the the user in the session
	 * 
	 * @return user
	 */
	public User getSessionUser()
	{
		return userDao.load(getSessionUserId());
	}

	/**
	 * fires a event with the collection of all the updated dom elements after
	 * the wicket ajax response. To subscribe the event call the
	 * <code>SocialSite.Ajax.registerPostAjax</code>. Your callback function
	 * will be called with a jQuery Wrapped set of all the update dom as the
	 * first argument.
	 * 
	 * NOTE: call this only once after all the components are added to the
	 * target
	 * 
	 * 
	 * @param target
	 *            ajax target
	 */
	public void firePostAjaxUpdateEvent(AjaxRequestTarget target)
	{
		StringBuffer script = new StringBuffer(" SocialSite.Ajax.handle([");
		for (Component component : target.getComponents())
		{
			script.append("\"" + component.getMarkupId() + "\",");
		}
		script.append("])");

		target.getHeaderResponse().renderOnDomReadyJavascript(script.toString());
	}
}
