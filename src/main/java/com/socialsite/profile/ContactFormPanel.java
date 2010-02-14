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

package com.socialsite.profile;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.EmailAddressValidator;

import com.socialsite.BasePanel;
import com.socialsite.dao.ProfileDao;
import com.socialsite.persistence.Profile;

/**
 * @author Ananth
 */
public class ContactFormPanel extends BasePanel
{

	private Profile profile;

	@SpringBean(name = "profileDao")
	private ProfileDao profileDao;

	/** feedback panel */
	FeedbackPanel feedback;

	public ContactFormPanel(String id)
	{
		super(id);
		this.profile = getSessionUser().getProfile();
		setOutputMarkupId(true);
		Form<Profile> form = new Form<Profile>("form", new CompoundPropertyModel<Profile>(profile));
		add(form);
		form.add(new RequiredTextField<String>("email").add(EmailAddressValidator.getInstance()));
		form.add(new TextField<String>("mobilePhone"));
		form.add(new TextField<String>("landPhone"));
		form.add(new TextField<String>("address"));
		form.add(new TextField<String>("city"));
		form.add(new TextField<String>("neighborhood"));
		form.add(new TextField<Integer>("zip"));
		form.add(new TextField<String>("website"));

		form.add(new AjaxSubmitLink("save")
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;


			@Override
			protected void onError(final AjaxRequestTarget target, final Form<?> form)
			{
				super.onError(target, form);
				// show feedback messages
				target.addComponent(feedback);
			}

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form)
			{
				profileDao.save(profile);
				target.appendJavascript("SocialSite.Message.show('profile updated')");
				// remove old feedback message
				target.addComponent(feedback);
			}
		});
		add(feedback = new FeedbackPanel("feedback"));
		feedback.setOutputMarkupId(true);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
