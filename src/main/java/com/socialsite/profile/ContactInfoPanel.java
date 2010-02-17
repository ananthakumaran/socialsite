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

import org.apache.wicket.model.Model;

import com.socialsite.BasePanel;
import com.socialsite.persistence.Profile;
import com.socialsite.util.NonEmptyPanel;
import com.socialsite.util.PrivacyModel;
import com.socialsite.util.PrivacyPanel;

/**
 * contains the contact info of the user
 * 
 * @author Ananth
 */
public class ContactInfoPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Profile profile;


	public ContactInfoPanel(String id)
	{
		super(id);
		this.profile = getUser().getProfile();
		setOutputMarkupId(true);
		// should we show the email
		add(new NonEmptyPanel("email", "Email", profile.getEmail()));
		add(new PrivacyPanel("mobilePhone", new Model<PrivacyModel>(profile.getMobilePhone()),
				"MobilePhone"));
		add(new PrivacyPanel("landPhone", new Model<PrivacyModel>(profile.getLandPhone()),
				"LandPhone"));
		add(new PrivacyPanel("address", new Model<PrivacyModel>(profile.getAddress()),
				"Address"));
		add(new NonEmptyPanel("city", "City", profile.getCity()));
		add(new NonEmptyPanel("neighborhood", "Neighborhood", profile.getNeighborhood()));

		add(new NonEmptyPanel("zip", "Zip", (profile.getZip() == null) ? null : profile.getZip()
				+ ""));
		add(new NonEmptyPanel("website", "WebSite", profile.getWebsite()));

	}
}
