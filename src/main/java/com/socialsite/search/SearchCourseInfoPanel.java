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

package com.socialsite.search;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.socialsite.BasePanel;
import com.socialsite.course.CourseLink;
import com.socialsite.image.ImagePanel;
import com.socialsite.image.ImageType;
import com.socialsite.persistence.Course;

/**
 * @author Ananth
 */
public class SearchCourseInfoPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SearchCourseInfoPanel(String id, IModel<Course> model)
	{
		super(id, model);
		Course course = model.getObject();
		add(new ImagePanel("image", course.getId(), ImageType.COURSE, course.getLastModified(),
				false, false));
		CourseLink courseLink;
		add(courseLink = new CourseLink("courselink", new Model<Course>(course)));
		courseLink.add(new Label("coursename", course.getName()));

		add(new Label("students", course.getStudents().size() + ""));
		add(new Label("questions", course.getQuestions().size() + ""));


	}

}
