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

package com.socialsite.dao;

import java.util.Date;

/**
 * 
 * Abstract Dao for all domain object that have image
 * 
 * @author Ananth
 * 
 */
public interface AbstractImageDao<T> extends AbstractDao<T>
{
	/**
	 * gets the last modified date
	 * 
	 * @param id
	 *            id of the domain class
	 * @return last modified date
	 */
	public Date getLastModifiedTime(final long id);

	/**
	 * gets the image
	 * 
	 * @param id
	 *            id of the domain class
	 * @return image
	 */
	public byte[] getImage(final long id);

	/**
	 * gets the thumb
	 * 
	 * @param id
	 *            id of the domain class
	 * @return thumb
	 */
	public byte[] getThumb(final long id);
}
