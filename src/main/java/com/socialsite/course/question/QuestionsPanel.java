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

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;

import com.socialsite.BasePanel;
import com.socialsite.dataprovider.QuestionDataProvider;
import com.socialsite.persistence.Course;
import com.socialsite.persistence.Question;

/**
 * @author Ananth
 */
public class QuestionsPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MarkupContainer questionsContainer;

	public QuestionsPanel(final String id, final IModel<Course> model)
	{
		super(id, model);

		final Course course = model.getObject();

		add(questionsContainer = new WebMarkupContainer("questionscontainer"));
		questionsContainer.setOutputMarkupId(true);

		final DataView<Question> questionsView = new DataView<Question>("questions",
				new QuestionDataProvider(course.getId()), 5)
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final Item<Question> item)
			{
				item.add(new QuestionPanel("question", item.getModel()));
			}
		};

		questionsContainer.add(questionsView);

	}

	public MarkupContainer getQuestionsContainer()
	{
		return questionsContainer;
	}


}
