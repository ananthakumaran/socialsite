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

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

import com.socialsite.SocialSiteSession;
import com.socialsite.authentication.SessionUser;
import com.socialsite.authentication.SocialSiteRoles;
import com.socialsite.persistence.Course;

public class CourseLink extends Link<Course>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourseLink(final String id, final IModel<Course> model)
	{
		super(id, model);
	}

	@Override
	public void onClick()
	{
		SessionUser user = SocialSiteSession.get().getSessionUser();
		if (getModelObject().getStaff().getId() == user.getId())
		{
			user.setRoles(SocialSiteRoles.staffRole);
		}
		setResponsePage(new CoursePage(getModel()));
	}

}
