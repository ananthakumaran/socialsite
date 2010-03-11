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

package com.socialsite.activation;

import java.util.Date;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.ActivationDao;
import com.socialsite.dao.UniversityDao;
import com.socialsite.image.DefaultImage;
import com.socialsite.persistence.Activation;
import com.socialsite.persistence.University;

/**
 * @author Ananth
 */
public class ActivationPage extends WebPage
{

	@SuppressWarnings("unused")
	private String messageText;
	@SpringBean(name = "activationDao")
	private ActivationDao activationDao;
	@SpringBean(name = "universityDao")
	private UniversityDao universityDao;

	public ActivationPage(final PageParameters params)
	{

		add(new Label("message", new PropertyModel<String>(this, "messageText")));
		final long id = params.getAsLong("id", 0);
		if (id == 0)
		{
			messageText = "Access denied";
		}
		else
		{
			final String action = params.getString("action");
			final Activation activation = activationDao.load(id);
			if (activation != null)
			{
				if (action.equals("activate"))
				{
					createUniversity(activation);
					sendNotification(activation, true);
					messageText = "University Created";
				}
				else
				{
					sendNotification(activation, false);
					messageText = "University request rejected";
				}
				activationDao.delete(activation);
			}
			else
			{
				messageText = "activation id not found";
			}
		}
	}

	public void createUniversity(final Activation activation)
	{
		final University university = new University(activation.getUniversityName(), activation
				.getAdmin());
		university.setLastModified(new Date());
		final DefaultImage defaultImage = new DefaultImage();
		defaultImage.forUniversity(university);
		universityDao.save(university);
	}

	public void sendNotification(final Activation activation, final boolean accept)
	{
		// TODO send a notification message to the admin
	}
}
