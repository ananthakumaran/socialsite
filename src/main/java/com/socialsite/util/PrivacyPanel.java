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

package com.socialsite.util;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

import com.socialsite.BasePanel;
import com.socialsite.authentication.SocialSiteRoles;
import com.socialsite.profile.Access;

/**
 * This shows the value only if the owner gave the permission to view it
 * 
 * @author Ananth
 */
public class PrivacyPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PrivacyModel privacyModel;

	public PrivacyPanel(String id, IModel<PrivacyModel> model, String label)
	{
		super(id, model);
		privacyModel = model.getObject();
		add(new Label("field", label));
		add(new Label("value", privacyModel.getValue()));

	}

	@Override
	public boolean isVisible()
	{
		// Don't show empty values
		if (privacyModel == null || privacyModel.getValue() == null)
		{
			return false;
		}
		Access access = privacyModel.getPrivacy();
		if (access == Access.EVERYONE || hasRole(SocialSiteRoles.OWNER)
				|| (access == Access.FRIENDS_ONLY && hasRole(SocialSiteRoles.FRIEND)))
		{
			return true;
		}
		return false;
	}
}
