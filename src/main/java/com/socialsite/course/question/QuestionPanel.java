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

package com.socialsite.course.question;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.socialsite.persistence.Question;

/**
 * @author Ananth
 */
public class QuestionPanel extends Panel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QuestionPanel(final String id, final IModel<Question> model)
	{
		super(id, model);
		final Question question = model.getObject();
		Link<Void> questionLink = new Link<Void>("question")
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				setResponsePage(new QuestionPage(model));
			}

		};
		add(questionLink);
		questionLink.add(new Label("heading", question.getHeading()));
		add(new Label("text", question.getText()).setEscapeModelStrings(false));
	}

}
