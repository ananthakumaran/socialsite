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

import java.util.Date;
import java.util.HashSet;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.BasePanel;
import com.socialsite.dao.CourseDao;
import com.socialsite.dao.MessageDao;
import com.socialsite.persistence.Course;
import com.socialsite.persistence.CourseJoinedMsg;
import com.socialsite.persistence.Message;
import com.socialsite.persistence.Student;
import com.socialsite.persistence.User;

/**
 * @author Ananth
 */
public class JoinCoursePanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** specifies the visibility */
	private Boolean isVisible = null;
	private Course course;

	@SpringBean(name = "messageDao")
	private MessageDao<Message> messageDao;

	/** spring dao to handle message object */
	@SpringBean(name = "courseDao")
	private CourseDao courseDao;

	public JoinCoursePanel(String id, IModel<Course> model)
	{
		super(id, model);
		this.course = model.getObject();
		setOutputMarkupId(true);
		add(new AjaxLink<Void>("join")
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(final AjaxRequestTarget target)
			{
				// reload
				course = courseDao.load(course.getId());
				User user = getSessionUser();
				CourseJoinedMsg msg = new CourseJoinedMsg();
				msg.setSender(user);
				msg.setCourse(course);
				msg.setTime(new Date());
				msg.setUsers(new HashSet<User>(user.getFriends()));
				messageDao.save(msg);
				isVisible = false;
				course.addStudents((Student)user);
				courseDao.save(course);
				target.addComponent(JoinCoursePanel.this);
				target.appendJavascript("SocialSite.Message.show('You joined the course');");
			}
		});
	}

	@Override
	public boolean isVisible()
	{
		// check the first time only
		if (isVisible == null)
		{
			if (getSessionUser() instanceof Student)
			{
				Student student = (Student)getSessionUser();
				isVisible = !student.getCourses().contains(course);
			}
			else
			{
				isVisible = false;
			}
		}
		return isVisible;
	}
}
