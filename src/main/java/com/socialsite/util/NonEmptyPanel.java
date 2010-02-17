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
import org.apache.wicket.markup.html.panel.Panel;

/**
 * @author Ananth
 */
public class NonEmptyPanel extends Panel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String value;

	public NonEmptyPanel(String id, String label, String value)
	{
		super(id);
		this.value = value;
		add(new Label("label", label));
		add(new Label("value", value));
	}

	@Override
	public boolean isVisible()
	{
		if (value == null || value.equals(""))
		{
			return false;
		}
		return true;
	}


}
