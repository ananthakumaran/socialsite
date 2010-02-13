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

package com.socialsite.message;

import java.util.Date;
import java.util.HashSet;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.BasePanel;
import com.socialsite.authentication.SocialSiteRoles;
import com.socialsite.dao.MessageDao;
import com.socialsite.dao.UserDao;
import com.socialsite.persistence.InfoMsg;
import com.socialsite.persistence.Message;
import com.socialsite.persistence.User;
import com.socialsite.util.wmd.RichEditor;

/**
 * sends message to user
 * 
 * @author Ananth
 */
public class MessageSenderPanel extends BasePanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** feedback panel */
	FeedbackPanel feedback;
	/** model for answer text */
	private String text;

	/** spring dao to handle message object */
	@SpringBean(name = "messageDao")
	private MessageDao<Message> messageDao;

	/** Spring Dao to handle user object */
	@SpringBean(name = "userDao")
	private UserDao<User> userDao;

	/**
	 * constructor
	 * 
	 * @param id
	 *            id
	 */
	public MessageSenderPanel(final String id, final MarkupContainer dependent)
	{
		super(id);
		final Form<Void> form = new Form<Void>("form");
		add(form);
		form.add(new RichEditor("richeditor", new PropertyModel<String>(this, "text")));

		form.add(new AjaxSubmitLink("send")
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onError(final AjaxRequestTarget target, final Form<?> form)
			{
				super.onError(target, form);
				// show feedback messages
				target.addComponent(feedback);
			}

			@Override
			protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{

				User sender = getSessionUser();
				final InfoMsg infoMsg = new InfoMsg();
				infoMsg.setMessage(text);
				infoMsg.setSender(sender);
				infoMsg.setTime(new Date());
				if (hasRole(SocialSiteRoles.OWNER))
				{
					// send the status to all the friends and who????
					infoMsg.setUsers(new HashSet<User>(getSessionUser().getFriends()));

					// add the user also
					infoMsg.addUser(sender);
				}
				else
				{
					// may be friend or other user if he allows other to send
					// msg
					infoMsg.addUser(userDao.load(getUserId()));
				}
				target.addComponent(dependent);

				// TODO show some meaningful message
				target.appendJavascript("SocialSite.Message.show('Your message has been sent successfully ');");
				// fire the update event so the editor can intialize
				firePostAjaxUpdateEvent(target);
				messageDao.save(infoMsg);
			}
		});
		add(feedback = new FeedbackPanel("feedback"));
		feedback.setOutputMarkupId(true);
	}

	@Override
	public boolean isVisible()
	{
		// TODO should we allow other user to send msg.(check the user pref)
		return true;
	}
}
