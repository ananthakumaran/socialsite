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

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.data.IDataProvider;

/**
 * 
 * @author Ananth
 * 
 * @param <T>
 */
public class ShowAllLink<T> extends Link<Void>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	IDataProvider<T> dataProvider;
	Class<? extends Page> pageClazz;

	public ShowAllLink(String id, IDataProvider<T> dataProvider, Class<? extends Page> pageClazz)
	{
		super(id);
		this.dataProvider = dataProvider;
		this.pageClazz = pageClazz;
	}

	@Override
	public void onClick()
	{
		setResponsePage(pageClazz);
	}

	@Override
	public boolean isVisible()
	{
		if (dataProvider.size() > 9)
		{
			return true;
		}
		return false;
	}
}
