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

import org.apache.wicket.markup.html.panel.Panel;

/**
 * shows a generic busy indicator at the top of the page for both ajax and
 * non-ajax links
 * 
 * @author Ananth
 */
public class BusyIndicatorPanel extends Panel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * constructor
	 * 
	 * @param id
	 *            id
	 */
	public BusyIndicatorPanel(final String id)
	{
		super(id);
	}
}
