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
import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.markup.html.panel.Panel;

import com.socialsite.authentication.SocialSiteRoles;

public class EditLink extends AjaxLink<Void>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Panel infoPanel;
	Panel formPanel;
	Panel current;
	Roles roles;

	public EditLink(String id, Panel infoPanel, Panel formPanel, Panel current, Roles roles)
	{
		super(id);
		this.infoPanel = infoPanel;
		this.formPanel = formPanel;
		this.current = current;
		this.roles = roles;
	}

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
		return roles.hasRole(SocialSiteRoles.OWNER);
	}
}
