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
import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.ResourceLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.value.ValueMap;

import com.socialsite.BasePanel;
import com.socialsite.course.CourseLink;
import com.socialsite.image.ImagePanel;
import com.socialsite.image.ImageType;
import com.socialsite.persistence.Course;
import com.socialsite.persistence.CourseNoteMsg;
import com.socialsite.persistence.Note;
import com.socialsite.util.DateUtils;

/**
 * @author Ananth
 */
public class CourseNoteMsgPanel extends BasePanel
{

	public CourseNoteMsgPanel(String id, IModel<CourseNoteMsg> model,
			final MarkupContainer dependent)
	{
		super(id, model);

		final CourseNoteMsg noteMsg = model.getObject();
		final Note note = noteMsg.getNote();
		final Course course = note.getCourse();


		add(new Label("description", note.getDescription()));

		CourseLink courseLink;
		add(courseLink = new CourseLink("course", new Model<Course>(course)));
		courseLink.add(new Label("courseName", course.getName()));

		CourseLink courseImageLink;
		add(courseImageLink = new CourseLink("image", new Model<Course>(course)));
		courseImageLink.add(new ImagePanel("coursethumb", course.getId(), ImageType.COURSE, course
				.getLastModified(), true));

		ResourceReference reference = new ResourceReference("note");
		add(new ResourceLink<Void>("download", reference, new ValueMap("id=" + note.getId())));

		add(new Label("date", DateUtils.relativeTime(noteMsg.getTime())));

		// delete link
		add(new DeleteMsgLink<CourseNoteMsg>("delete", model, dependent, this, 0));
		setOutputMarkupId(true);


	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
