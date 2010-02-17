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

import org.apache.wicket.markup.html.panel.Panel;

import com.socialsite.BasePanel;

/**
 * Contact Tab
 * 
 * @author Ananth
 */
public class ContactTabPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Panel infoPanel = new ContactInfoPanel("contact");
	private Panel formPanel = new ContactFormPanel("contact");
	private Panel current = infoPanel;

	public ContactTabPanel(String id)
	{
		super(id);
		add(current);
		add(new EditLink("edit", infoPanel, formPanel, current, getRoles()));
	}


}
