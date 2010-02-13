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
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.panel.Panel;

import com.socialsite.BasePanel;
import com.socialsite.authentication.SocialSiteRoles;

/**
 * @author Ananth
 */
public class BasicTabPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Panel infoPanel = new BasicInfoPanel("basic");
	private Panel formPanel = new BasicFormPanel("basic");
	private Panel current = infoPanel;


	public BasicTabPanel(final String id)
	{
		super(id);
		add(current);
		add(new AjaxLink<Void>("edit")
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target)
			{
				current.replaceWith(formPanel);
				current = formPanel;
				target.addComponent(current);
			}

			@Override
			public boolean isVisible()
			{
				return hasRole(SocialSiteRoles.OWNER);
			}
		});

	}

}
