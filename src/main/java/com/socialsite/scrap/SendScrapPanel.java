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

package com.socialsite.scrap;

import java.util.Date;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.BasePanel;
import com.socialsite.SocialSiteSession;
import com.socialsite.dao.ScrapDao;
import com.socialsite.dao.UserDao;
import com.socialsite.persistence.Scrap;
import com.socialsite.persistence.User;

/**
 * sends scrap
 * 
 * @author Ananth
 */
public class SendScrapPanel extends BasePanel
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/** model for message */
	private String				message;

	/** spring dao to handle user object */
	@SpringBean(name = "userDao")
	private UserDao<User>		userDao;

	/** spring dao to handle scrap object */
	@SpringBean(name = "scrapDao")
	private ScrapDao			scrapDao;

	/**
	 * constructor
	 * 
	 * @param id
	 *            id
	 * @param scrapListContainer
	 *            container to be updated when the scrap is send
	 */
	public SendScrapPanel(final String id,
			final WebMarkupContainer scrapListContainer)
	{
		super(id);

		final Form<Object> scrapForm = new Form<Object>("scrapform");
		add(scrapForm);

		scrapForm.add(new TextArea<String>("message",
			new PropertyModel<String>(this, "message")).setRequired(true));
		AjaxSubmitLink send;
		scrapForm.add(send = new AjaxSubmitLink("send")
		{

			/**
			 * 
			 */
			private static final long	serialVersionUID	= 1L;

			@Override
			protected void onSubmit(final AjaxRequestTarget target,
					final Form<?> form)
			{
				final SocialSiteSession session = SocialSiteSession.get();

				final User author = userDao.load(session.getSessionUser()
					.getId());
				final User receiver = userDao.load(session.getUserId());

				// save the scrap
				final Scrap scrap = new Scrap();
				scrap.setAuthor(author);
				scrap.setReceiver(receiver);
				scrap.setMessage(message);
				scrap.setTime(new Date());

				scrapDao.save(scrap);

				// update the scrap list
				target.addComponent(scrapListContainer);
			}

		});

		scrapForm.setDefaultButton(send);
	}
}
