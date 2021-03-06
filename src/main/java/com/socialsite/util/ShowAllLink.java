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

import com.socialsite.persistence.User;
import com.socialsite.user.AllUsersPage;

/**
 * 
 * @author Ananth
 * 
 * @param <T>
 */
public class ShowAllLink extends Link<Void>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final IDataProvider<User> dataProvider;
	int size;
	Class<? extends Page> pageClazz = null;

	public ShowAllLink(final String id, final IDataProvider<User> dataProvider)
	{
		super(id);
		this.dataProvider = dataProvider;
		if (dataProvider != null)
			this.size = dataProvider.size();
	}


	public ShowAllLink(final String id, final int size, final Class<? extends Page> pageClazz)
	{
		this(id, null);
		this.size = size;
		this.pageClazz = pageClazz;
	}

	@Override
	public boolean isVisible()
	{
		if (size > 9)
		{
			return true;
		}
		return false;
	}


	@Override
	public void onClick()
	{
		if (pageClazz == null)
		{
			setResponsePage(new AllUsersPage(dataProvider));
		}
		else
		{
			setResponsePage(pageClazz);
		}
	}
}
