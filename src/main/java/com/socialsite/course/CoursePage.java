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

package com.socialsite.course;

import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.BasePage;
import com.socialsite.course.question.AddQuestionPanel;
import com.socialsite.course.question.QuestionsPanel;
import com.socialsite.dao.CourseDao;
import com.socialsite.dataprovider.CourseStudentsDataProvider;
import com.socialsite.friend.FriendsPage;
import com.socialsite.persistence.Course;
import com.socialsite.user.UsersPanel;

/**
 * @author Ananth
 */
public class CoursePage extends BasePage
{
	/** spring dao to handle message object */
	@SpringBean(name = "courseDao")
	private CourseDao courseDao;

	public CoursePage(final IModel<Course> model)
	{
		// reload the model
		model.setObject(courseDao.load(model.getObject().getId()));
		add(new CourseInfoPanel("info", model));

		final QuestionsPanel questionsPanel = new QuestionsPanel("questions", model);
		add(questionsPanel);
		add(new AddQuestionPanel("addquestion", model, questionsPanel.getQuestionsContainer()));

		add(new UsersPanel("students", new CourseStudentsDataProvider(model.getObject().getId()),
				FriendsPage.class));
	}
}
