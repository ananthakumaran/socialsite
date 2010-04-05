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

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;

import com.socialsite.BasePanel;
import com.socialsite.persistence.Staff;
import com.socialsite.persistence.User;

/**
 * @author Ananth
 */
public class NewCoursePanel extends BasePanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NewCoursePanel(String id)
	{
		super(id);
		final ModalWindow courseModal;
		add(courseModal = new ModalWindow("coursemodal"));

		courseModal.setContent(new NewCourseModal(courseModal.getContentId()));
		courseModal.setTitle("Create new Course");
		courseModal.setCookieName("coursemodal");

		courseModal.setCloseButtonCallback(new ModalWindow.CloseButtonCallback()
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean onCloseButtonClicked(AjaxRequestTarget target)
			{
				return true;
			}
		});

		courseModal.setWindowClosedCallback(new ModalWindow.WindowClosedCallback()
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void onClose(AjaxRequestTarget target)
			{

			}
		});

		add(new AjaxLink<Void>("newcourse")
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target)
			{
				courseModal.show(target);
			}
		});
	}

	@Override
	public boolean isVisible()
	{
		if (!hasRole("OWNER"))
		{
			return false;
		}
		User user = getSessionUser();
		if (user instanceof Staff)
		{
			return (((Staff)user).getUniversity() != null);
		}
		return false;
	}

}
