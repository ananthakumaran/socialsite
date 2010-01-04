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

package com.socialsite.image;

import org.apache.wicket.markup.html.DynamicWebResource;
import org.apache.wicket.util.time.Time;
import org.apache.wicket.util.value.ValueMap;

import com.socialsite.dao.AbstractImageDao;

/**
 * Abstract class for the image resource in the application
 * 
 * @author Ananth
 * 
 * @param <T>
 */
public abstract class AbstractImageResource<T> extends DynamicWebResource
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AbstractImageResource()
	{
		setCacheable(true);
	}

	/**
	 * override this method and return the Dao to access the image
	 * 
	 * NOTE: Inject the Bean lazily to allow the Wicket tester to run properly.
	 * ie Don't inject the bean inside the contructor
	 * 
	 * @return Dao
	 */
	public abstract AbstractImageDao<T> getImageDao();

	@Override
	protected ResourceState getResourceState()
	{
		// parameters
		final ValueMap params = getParameters();
		final long id = params.getAsLong("id");
		// load the image from the database
		final ImageResourceState imageResourceState = new ImageResourceState(Time
				.valueOf(getImageDao().getLastModifiedTime(id)));
		try
		{
			imageResourceState.setContentType("image/jpeg");
			if (params.containsKey("thumb"))
			{
				imageResourceState.setData(getImageDao().getThumb(id));
			}
			else
			{
				imageResourceState.setData(getImageDao().getImage(id));
			}
		}
		catch (final Exception e)
		{
			return new EmptyResourceState();
		}
		return imageResourceState;
	}
}
