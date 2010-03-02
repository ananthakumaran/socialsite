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

package com.socialsite.university;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.BasePanel;
import com.socialsite.SocialSiteSession;
import com.socialsite.dao.FriendRequestMsgDao;
import com.socialsite.dao.UserDao;
import com.socialsite.persistence.Staff;
import com.socialsite.persistence.University;
import com.socialsite.persistence.User;

/**
 * @author Ananth
 */
public class JoinUniversityPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7406758884244898081L;
	/** Spring Dao to access the user object */
	@SpringBean(name = "userDao")
	private UserDao<User> userDao;
	/** specifies the visibility */
	private Boolean isVisible = null;


	public JoinUniversityPanel(String id, IModel<University> university)
	{
		super(id);
		add(new AjaxSubmitLink("join")
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				Staff staff = (Staff)getSessionUser();
				
			}
		});
	}

	@Override
	public boolean isVisible()
	{
		// check the first time only
		if (isVisible == null)
		{
			isVisible = false;

		}
		return isVisible;
	}
}
