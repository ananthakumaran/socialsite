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
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.socialsite.BasePanel;
import com.socialsite.SocialSiteSession;
import com.socialsite.authentication.SessionUser;
import com.socialsite.authentication.SocialSiteRoles;
import com.socialsite.course.question.QuestionPage;
import com.socialsite.image.ImagePanel;
import com.socialsite.image.ImageType;
import com.socialsite.persistence.Question;
import com.socialsite.persistence.QuestionInfoMsg;
import com.socialsite.persistence.User;
import com.socialsite.user.UserLink;
import com.socialsite.util.DateUtils;

/**
 * @author Ananth
 */
public class QuestionInfoMsgPanel extends BasePanel
{

	public QuestionInfoMsgPanel(String id, IModel<QuestionInfoMsg> model, MarkupContainer dependent)
	{
		super(id, model);
		final QuestionInfoMsg infoMsg = model.getObject();
		final Question question = infoMsg.getQuestion();

		final User user = question.getUser();

		// user image
		UserLink<User> userImageLink;
		final Model<User> senderModel = new Model<User>(user);
		add(userImageLink = new UserLink<User>("imagelink", senderModel));
		userImageLink.add(new ImagePanel("userthumb", user.getId(), ImageType.USER, user
				.getLastModified(), true));
		Link<User> name;
		add(name = new UserLink<User>("home", senderModel));
		name.add(new Label("username", user.getUserName()));


		add(new Label("date", DateUtils.relativeTime(question.getTime())));

		final Model<Question> questionModel = new Model<Question>(question);

		final Link<Question> questionLink = new Link<Question>("questionlink", questionModel)
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				SessionUser user = SocialSiteSession.get().getSessionUser();
				if (getModelObject().getCourse().getStaff().getId() == user.getId())
				{
					user.setRoles(SocialSiteRoles.staffRole);
				}
				else
				{
					user.setRoles(SocialSiteRoles.userRole);
				}

				setResponsePage(new QuestionPage(questionModel));
			}

		};
		add(questionLink);
		questionLink.add(new Label("heading", question.getHeading()));

		// Question

		// TODO show only first two lines of the Question
		add(new Label("question", question.getText()).setEscapeModelStrings(false));

		add(new DeleteMsgLink<QuestionInfoMsg>("delete", model, dependent, this, 0));
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
