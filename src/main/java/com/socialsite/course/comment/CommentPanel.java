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

package com.socialsite.course.comment;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.BasePanel;
import com.socialsite.authentication.SocialSiteRoles;
import com.socialsite.dao.CommentDao;
import com.socialsite.persistence.Comment;
import com.socialsite.persistence.User;
import com.socialsite.user.UserLink;
import com.socialsite.util.DateUtils;

/**
 * @author Ananth
 */
public class CommentPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SpringBean(name = "commentDao")
	CommentDao commentDao;

	public CommentPanel(final String id, final IModel<Comment> model)
	{
		super(id, model);
		setOutputMarkupId(true);
		final Comment comment = model.getObject();
		add(new Label("comment", comment.getText()).setEscapeModelStrings(false));
		final User user = comment.getUser();
		final Model<User> senderModel = new Model<User>(user);
		Link<User> name;
		add(name = new UserLink<User>("home", senderModel));
		name.add(new Label("username", user.getUserName()));
		add(new Label("date", DateUtils.relativeTime(comment.getTime())));

		add(new AjaxLink<Comment>("delete", model)
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target)
			{
				commentDao.delete(getModelObject());
				CommentPanel.this.setVisible(false);
				target.addComponent(CommentPanel.this);
			}

			@Override
			public boolean isVisible()
			{
				// let the staff and author to delete the comment
				return (hasRole(SocialSiteRoles.STAFF) || getModelObject().getUser().getId() == getSessionUserId());
			}
		});
	}
}
