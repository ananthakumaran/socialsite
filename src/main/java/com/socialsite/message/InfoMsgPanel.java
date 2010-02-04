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

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.BasePanel;
import com.socialsite.SocialSiteSession;
import com.socialsite.authentication.SessionUser;
import com.socialsite.authentication.SocialSiteRoles;
import com.socialsite.dao.MessageDao;
import com.socialsite.persistence.InfoMsg;
import com.socialsite.persistence.Message;

/**
 * @author Ananth
 */
public class InfoMsgPanel extends BasePanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		add(new Label("message", infoMsg.getMessage()));
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
				SessionUser user = SocialSiteSession.get().getSessionUser();
				// allow the owner and the sender to delete the msg
				if (user.hasRole(SocialSiteRoles.OWNER))
				{
					return true;
				}
				else
				{
					return infoMsg.getSender().getId() == user.getId();
				}
			}
		});
		// TODO add other details
	}

}
