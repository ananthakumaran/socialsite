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

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.MessageDao;
import com.socialsite.dao.UniversityDao;
import com.socialsite.image.ImagePanel;
import com.socialsite.image.ImageType;
import com.socialsite.persistence.Message;
import com.socialsite.persistence.Staff;
import com.socialsite.persistence.StaffRequestMsg;
import com.socialsite.persistence.University;
import com.socialsite.persistence.User;
import com.socialsite.user.UserLink;
import com.socialsite.util.DateUtils;

/**
 * @author Ananth
 */
@AuthorizeAction(action = Action.RENDER, roles = "OWNER")
public class StaffRequestMsgPanel extends Panel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SpringBean(name = "messageDao")
	private MessageDao<Message> messageDao;

	@SpringBean(name = "universityDao")
	private UniversityDao universityDao;

	public StaffRequestMsgPanel(String id, final IModel<StaffRequestMsg> model,
			final WebMarkupContainer container)
	{
		super(id, model);
		UserLink<User> user;
		StaffRequestMsg staffRequestMsg = model.getObject();
		User sender = staffRequestMsg.getSender();
		// user image
		UserLink<User> userImageLink;
		Model<User> senderModel = new Model<User>(sender);
		add(userImageLink = new UserLink<User>("imagelink", senderModel));
		userImageLink.add(new ImagePanel("userthumb", sender.getId(), ImageType.USER, sender
				.getLastModified(), true));
		add(user = new UserLink<User>("user", new Model<User>(staffRequestMsg.getSender())));
		user.add(new Label("name", staffRequestMsg.getSender().getUserName()));
		add(new Link<StaffRequestMsg>("yes", model)
		{
			/**
		 * 
		 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{

				StaffRequestMsg staffRequestMsg = getModelObject();

				staffRequestMsg = (StaffRequestMsg)messageDao.load(staffRequestMsg.getId());

				// add him as the friend
				final Staff staff = staffRequestMsg.getSender();
				final University university = staffRequestMsg.getUniversity();
				university.addStaff(staff);
				universityDao.save(university);
				// remove the friend request
				messageDao.delete(staffRequestMsg);
				// update the list
			}
		});

		add(new AjaxLink<StaffRequestMsg>("no", model)
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(final AjaxRequestTarget target)
			{
				// remove the friend request
				messageDao.delete(getModelObject());

				// update the list
				target.addComponent(container);
			}
		});


		add(new Label("time", DateUtils.relativeTime(staffRequestMsg.getTime())));

	}
}
