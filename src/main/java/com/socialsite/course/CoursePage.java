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

import org.apache.wicket.model.Model;

import com.socialsite.BasePage;
import com.socialsite.course.question.AddQuestionPanel;
import com.socialsite.course.question.QuestionsPanel;
import com.socialsite.persistence.Course;

/**
 * @author Ananth
 */
public class CoursePage extends BasePage
{
	public CoursePage(final Course course)
	{
		final Model<Course> courseModel = new Model<Course>(course);
		add(new CourseInfoPanel("info", courseModel));
		final QuestionsPanel questionsPanel = new QuestionsPanel("questions", courseModel);
		add(questionsPanel);
		add(new AddQuestionPanel("addquestion", courseModel, questionsPanel.getQuestionsContainer()));
	}
}
