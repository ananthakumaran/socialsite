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

package com.socialsite.friend;

import java.util.Date;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.BasePanel;
import com.socialsite.SocialSiteSession;
import com.socialsite.dao.FriendRequestMsgDao;
import com.socialsite.dao.UserDao;
import com.socialsite.persistence.FriendRequestMsg;
import com.socialsite.persistence.User;

/**
 * @author Ananth
 */
@AuthorizeAction(action = Action.RENDER, roles = "USER")
public class AddAsFriendPanel extends BasePanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** Model for message */
	private String message;

	/** Spring Dao to access the user object */
	@SpringBean(name = "userDao")
	private UserDao<User> userDao;
	/** Spring Dao to access the friendrequest object */
	@SpringBean(name = "friendRequestMsgDao")
	private FriendRequestMsgDao friendRequestMsgDao;
	/** feedback panel */
	private FeedbackPanel feedback;

	/**
	 * constructor
	 */
	public AddAsFriendPanel(final String id)
	{
		super(id);
		final Form<Object> addAsFriendForm = new Form<Object>("addasfriendform");
		add(addAsFriendForm);
		addAsFriendForm.add(new RequiredTextField<String>("message", new PropertyModel<String>(
				this, "message")));
		AjaxSubmitLink send;
		addAsFriendForm.add(send = new AjaxSubmitLink("send")
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				// load the users
				final User user = userDao.load(SocialSiteSession.get().getSessionUser().getId());
				final User friend = userDao.load(SocialSiteSession.get().getUserId());
				// create a new friend request
				final FriendRequestMsg friendRequest = new FriendRequestMsg();
				friendRequest.setSender(user);
				friendRequest.getUsers().add(friend);
				friendRequest.setMessage(message);
				friendRequest.setTime(new Date());
				friendRequestMsgDao.save(friendRequest);
				// show feedback
				info("friend request sent");
				// TODO add javascript to hide the form after sending the friend
				// request
				target.addComponent(feedback);

				// remove the form
				target.appendJavascript(" SocialSite.Home.AddAsFriend.removeAll();");
			}
		});
		addAsFriendForm.setDefaultButton(send);
		add(feedback = new FeedbackPanel("feedback"));
		feedback.setOutputMarkupId(true);
	}

	@Override
	public boolean isVisible()
	{
		return !friendRequestMsgDao.hasFriendRequest(SocialSiteSession.get().getSessionUser()
				.getId(), SocialSiteSession.get().getUserId());

		// return true;

	}
}
