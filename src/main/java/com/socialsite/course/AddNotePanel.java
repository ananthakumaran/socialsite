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

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.BasePanel;
import com.socialsite.dao.NoteDao;
import com.socialsite.persistence.Course;
import com.socialsite.persistence.Note;
import com.socialsite.persistence.Staff;
import com.socialsite.persistence.User;

/**
 * @author Ananth
 */
public class AddNotePanel extends BasePanel
{

	private FileUploadField fileUploadField;
	private String description;

	/** feedback panel */
	FeedbackPanel feedback;

	private final Course course;
	@SpringBean(name = "noteDao")
	NoteDao noteDao;

	public AddNotePanel(String id, final IModel<Course> model)
	{
		super(id);
		this.course = model.getObject();
		Form<Void> form = new Form<Void>("form");
		add(form);
		form.add(fileUploadField = new FileUploadField("file"));
		form.add(new RequiredTextField<String>("description", new PropertyModel<String>(this,
				"description")));
		form.add(new SubmitLink("submit")
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit()
			{
				final FileUpload upload = fileUploadField.getFileUpload();
				if (upload == null || upload.getSize() == 0)
				{
					error("upload a file");
				}
				else if (upload.getSize() > 1024 * 1024)
				{
					// TODO change this to 10MB
					error("file size too large");
				}
				else
				{
					Note note = new Note();
					note.setCourse(model.getObject());
					note.setTime(new Date());
					note.setDescription(description);
					note.setData(upload.getBytes());
					noteDao.save(note);

					// TODO send a message to all the student in the course
				}
			}
		});

		add(feedback = new FeedbackPanel("feedback"));
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean isVisible()
	{
		User user = getSessionUser();
		if (user instanceof Staff)
		{
			return ((Staff)user).getCourses().contains(course);
		}
		return false;
	}

}
