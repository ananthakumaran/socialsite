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

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.BasePanel;
import com.socialsite.dao.CourseDao;
import com.socialsite.image.ImagePanel;
import com.socialsite.image.ImageService;
import com.socialsite.image.ImageType;
import com.socialsite.persistence.Course;
import com.socialsite.persistence.User;
import com.socialsite.user.UserLink;

/**
 * @author Ananth
 */
public class CourseInfoPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SpringBean(name = "courseDao")
	private CourseDao courseDao;

	public CourseInfoPanel(final String id, final IModel<Course> model)
	{
		super(id, model);
		final Course course = model.getObject();
		add(new ImagePanel("image", course.getId(), ImageType.COURSE, course.getLastModified())
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void saveImage(final byte[] imageData)
			{
				final ImageService imageService = new ImageService();
				course.changeImage(imageService.resize(imageData, ImageService.IMAGE_SIZE));
				course.changeThumb(imageService.resize(imageData, ImageService.THUMB_SIZE));
				courseDao.save(course);
			}

		});
		
		// join this course
		add(new JoinCoursePanel("join", model));
		
		UserLink<User> staffLink;
		add(staffLink = new UserLink<User>("stafflink", new Model<User>(course.getStaff())));
		staffLink.add(new Label("name", course.getStaff().getUserName()));
	}

}
