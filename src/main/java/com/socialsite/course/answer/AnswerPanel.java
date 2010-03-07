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

package com.socialsite.course.answer;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.socialsite.BasePanel;
import com.socialsite.course.comment.AddCommentPanel;
import com.socialsite.course.comment.CommentsPanel;
import com.socialsite.image.ImagePanel;
import com.socialsite.image.ImageType;
import com.socialsite.persistence.Answer;
import com.socialsite.persistence.User;
import com.socialsite.user.UserLink;
import com.socialsite.util.DateUtils;

/**
 * @author Ananth
 */
public class AnswerPanel extends BasePanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AnswerPanel(final String id, final IModel<Answer> model)
	{
		super(id, model);
		final Answer answer = model.getObject();
		final User user = answer.getUser();
		// user image
		UserLink<User> userImageLink;
		Model<User> senderModel = new Model<User>(user);
		add(userImageLink = new UserLink<User>("imagelink", senderModel));
		userImageLink.add(new ImagePanel("userthumb", user.getId(), ImageType.USER, user
				.getLastModified(), true));
		Link<User> name;
		add(name = new UserLink<User>("home", senderModel));
		name.add(new Label("username", user.getUserName()));


		add(new Label("date", DateUtils.relativeTime((answer.getTime()))));

		add(new Label("answer", answer.getText()).setEscapeModelStrings(false));

		MarkupContainer commentPanel;
		add(commentPanel = new CommentsPanel("comments", model));
		add(new AddCommentPanel("addcomment", model, commentPanel));

	}

}
