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

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.markup.html.DynamicWebResource;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.value.ValueMap;

import com.socialsite.dao.ProfileDao;

/**
 * handles the request for the images
 * 
 * @author Ananth
 * 
 */
public class UserImageResource extends DynamicWebResource
{

	@SpringBean(name = "profileDao")
	private ProfileDao profileDao;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserImageResource()
	{

	}

	@Override
	protected ResourceState getResourceState()
	{
		// inject the spring dao
		InjectorHolder.getInjector().inject(this);

		// parameters
		final ValueMap params = getParameters();

		// load the image from the database
		final ImageResourceState imageResourceState = new ImageResourceState();
		try
		{
			imageResourceState.setContentType("image/jpeg");
			if (params.containsKey("thumb"))
			{
				imageResourceState.setData(profileDao.getUserThumb(params.getAsLong("id")));
			}
			else
			{
				imageResourceState.setData(profileDao.getUserImage(params.getAsLong("id")));
			}

		}
		catch (final Exception e)
		{
			return new ResourceState()
			{

				@Override
				public String getContentType()
				{
					return null;
				}

				@Override
				public byte[] getData()
				{
					return null;
				}
			};
		}
		return imageResourceState;
	}
}
