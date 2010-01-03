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

import com.socialsite.persistence.Course;
import com.socialsite.persistence.Profile;
import com.socialsite.persistence.University;

/**
 * sets the default image for a profile
 * 
 * @author Ananth
 * 
 */
public class DefaultImage
{

	/**
	 * sets the default image for a course
	 * 
	 * @param course
	 *            course
	 */
	public void forCourse(final Course course)
	{
		course.setImage(getImageData("course-125.png"));
		course.setThumb(getImageData("course-75.png"));
	}

	/**
	 * sets the default image for a university
	 * 
	 * @param university
	 *            university
	 */
	public void forUniversity(final University university)
	{
		university.setImage(getImageData("university-125.png"));
		university.setThumb(getImageData("university-75.png"));
	}

	/**
	 * set the default image for a user
	 * 
	 * @param profile
	 *            profile
	 */
	public void forUser(final Profile profile)
	{
		profile.setImage(getImageData("user-125.png"));
		profile.setThumb(getImageData("user-75.png"));
	}

	/**
	 * gets the image as byte array
	 * 
	 * @param name
	 *            name of the image file
	 * @return byte array of the image
	 */
	public byte[] getImageData(final String name)
	{
		final PackageResource imageRef = PackageResource.get(getClass(), name);
		try
		{
			return IOUtils.toByteArray(imageRef.getResourceStream().getInputStream());
		}
		catch (final IOException e)
		{
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
		}
		catch (final ResourceStreamNotFoundException e)
		{
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
		}
		return null;
	}

}
