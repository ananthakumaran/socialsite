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

import java.util.Date;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.BasePanel;
import com.socialsite.dao.StaffRequestMsgDao;
import com.socialsite.dao.UserDao;
import com.socialsite.persistence.Staff;
import com.socialsite.persistence.StaffRequestMsg;
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

	@SpringBean(name = "staffRequestMsgDao")
	private StaffRequestMsgDao staffRequestMsgDao;

	/** specifies the visibility */
	private Boolean isVisible = null;

	private University university;

	public JoinUniversityPanel(String id, IModel<University> model)
	{
		super(id);
		this.university = model.getObject();
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
				StaffRequestMsg msg = new StaffRequestMsg(staff);
				msg.setTime(new Date());
				msg.addUser(university.getAdmin());
				msg.setUniversity(university);
				staffRequestMsgDao.save(msg);
				isVisible = false;
				target.addComponent(this);
				target
						.appendJavascript("SocialSite.Message.show('Your request has been sent to the admin');");
			}
		});
	}

	@Override
	public boolean isVisible()
	{
		// check the first time only
		if (isVisible == null)
		{
			if (getSessionUser() instanceof Staff)
			{
				// TODO check if the staff is already joined in this university
				isVisible = staffRequestMsgDao.hasRequest((Staff)getSessionUser(), university);
			}
			else
			{
				isVisible = false;
			}
		}
		return isVisible;
	}
}
