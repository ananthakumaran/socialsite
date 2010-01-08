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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.wicket.RequestCycle;
import org.apache.wicket.markup.html.DynamicWebResource;
import org.apache.wicket.protocol.http.WebResponse;
import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
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

	@Override
	protected int getCacheDuration()
	{
		return 3600 * 24 * 30;
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
		final Date lastModified = getImageDao().getLastModifiedTime(id);
		// load the image from the database
		final ImageResourceState imageResourceState = new ImageResourceState(Time
				.valueOf(lastModified));
		try
		{

			imageResourceState.setContentType("image/jpeg");

			// Check for 304 request
			final ServletWebRequest request = (ServletWebRequest)RequestCycle.get().getRequest();
			final String ifModifiedSince = request.getHttpServletRequest().getHeader(
					"If-Modified-Since");
			if (ifModifiedSince != null)
			{
				final SimpleDateFormat format = new SimpleDateFormat(
						"EEE, dd MMM yyyy HH:mm:ss zzz");
				final Date date = format.parse(ifModifiedSince);
				if (date != null)
				{
					if (lastModified.compareTo(date) < 0 || lastModified.compareTo(date) == 0)
					{
						// this is a 304 not modified response.so no need to
						// fetch the image from the database
						return imageResourceState;
					}
				}
			}

			// normal request
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
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
			return new EmptyResourceState();
		}
		return imageResourceState;
	}

	@Override
	protected void setHeaders(final WebResponse response)
	{
		// sets the Expires
		super.setHeaders(response);
		if (isCacheable())
		{
			// allows the proxy to cache the image
			response.setHeader("Cache-Control", "max-age=" + getCacheDuration() + " ,public");
		}
	}
}
