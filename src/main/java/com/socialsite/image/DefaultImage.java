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

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.wicket.markup.html.PackageResource;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;

import com.socialsite.persistence.Profile;

/**
 * sets the default image for a profile
 * 
 * @author Ananth
 * 
 */
public class DefaultImage
{

	/**
	 * constructor
	 * 
	 * @param profile
	 */
	public DefaultImage(final Profile profile)
	{
		final PackageResource imageRef = PackageResource.get(getClass(),
			"user-125.png");
		final PackageResource iconRef = PackageResource.get(getClass(),
			"user-75.png");
		try
		{
			profile.setImage(IOUtils.toByteArray(imageRef.getResourceStream()
				.getInputStream()));
			profile.setThumb(IOUtils.toByteArray(iconRef.getResourceStream()
				.getInputStream()));
		} catch (final IOException e)
		{
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
		} catch (final ResourceStreamNotFoundException e)
		{
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
		}
	}

}
