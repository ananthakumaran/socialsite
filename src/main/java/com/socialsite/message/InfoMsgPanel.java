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

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.BasePanel;
import com.socialsite.authentication.SocialSiteRoles;
import com.socialsite.dao.MessageDao;
import com.socialsite.persistence.InfoMsg;
import com.socialsite.persistence.Message;
import com.socialsite.util.wmd.RichEditor;

/**
 * @author Ananth
 */
public class InfoMsgPanel extends BasePanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** model for reply text */
	private String text;

	/** feedback panel */
	FeedbackPanel feedback;
	/** spring dao to handle message object */
	@SpringBean(name = "messageDao")
	private MessageDao<Message> messageDao;

	/**
	 * constructor
	 * 
	 * @param id
	 *            id
	 * @param infoMsg
	 *            message
	 */
	public InfoMsgPanel(final String id, final IModel<InfoMsg> infoMsgModel,
			final MarkupContainer dependent)
	{
		super(id, infoMsgModel);
		final InfoMsg infoMsg = infoMsgModel.getObject();
		add(new Label("message", infoMsg.getMessage()).setEscapeModelStrings(false));
		add(new AjaxLink<InfoMsg>("delete", infoMsgModel)
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target)
			{
				messageDao.delete(getModelObject());
				target.addComponent(dependent);
			}

			@Override
			public boolean isVisible()
			{
				// allow the owner and the sender to delete the msg
				if (hasRole(SocialSiteRoles.OWNER))
				{
					return true;
				}
				else
				{
					return infoMsg.getSender().getId() == getSessionUserId();
				}
			}
		});

		final Form<InfoMsg> form = new Form<InfoMsg>("form", infoMsgModel);
		add(form);
		form.add(new RichEditor("richeditor", new PropertyModel<String>(this, "text")));
		form.add(new AjaxSubmitLink("send")
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isVisible()
			{
				// don't allow the user to reply himself
				if (infoMsg.getSender().getId() == getSessionUserId())
				{
					return false;
				}
				return hasRole(SocialSiteRoles.OWNER);
			}

			@Override
			protected void onError(final AjaxRequestTarget target, final Form<?> form)
			{
				super.onError(target, form);
				// show feedback messages
				target.addComponent(feedback);
			}

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form)
			{
				try
				{
					InfoMsg msg = (InfoMsg)form.getModelObject();
					final InfoMsg replyMsg = new InfoMsg();
					replyMsg.setMessage(text);
					replyMsg.setSender(getSessionUser());
					replyMsg.setTime(new Date());
					replyMsg.addUser(msg.getSender());
					messageDao.save(replyMsg);
				}
				catch (Exception ex)
				{
					// TODO show the failure message
				}

				// slideup the reply panel
				String id = InfoMsgPanel.this.getMarkupId();
				target.appendJavascript(" $('#" + id + " .slideText').trigger('click'); ");

				// TODO show the success message
				// either use a global success or failure message handler
				// or show the messge near this message

				// clears the old feedback message
				target.addComponent(feedback);
			}
		});

		add(feedback = new FeedbackPanel("feedback"));
		feedback.setOutputMarkupId(true);
		setOutputMarkupId(true);
	}
}
