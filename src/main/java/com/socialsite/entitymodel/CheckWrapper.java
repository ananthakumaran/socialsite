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

package com.socialsite.entitymodel;

import java.io.Serializable;

/**
 * 
 * name is the wrapped object that could be your business object. the selected
 * property is just here to record whether the checkbox for it was selected.
 * 
 * @author Ananth
 */
public class CheckWrapper<T> implements Serializable
{

	private static final long serialVersionUID = 1L;
	/** wrapper for the business object */
	private T name;
	/** boolean to hold the checkbox value */
	private Boolean selected = Boolean.FALSE;

	/**
	 * constructor
	 * 
	 * @param wrapped
	 *            object to be wrapped
	 */
	public CheckWrapper(final T wrapped)
	{
		this.name = wrapped;
	}

	/**
	 * getter
	 * 
	 * @return the name wrapped in the object
	 */
	public T getName()
	{
		return name;
	}

	/**
	 * getter
	 * 
	 * @return the boolean contains the state of the checkbox
	 */
	public Boolean getSelected()
	{
		return selected;
	}

	/**
	 * setter
	 * 
	 * @param wrapped
	 *            name to be wrapped in the object
	 */
	public void setName(final T wrapped)
	{
		this.name = wrapped;
	}

	/**
	 * setter
	 * 
	 * @param selected
	 *            checkbox value
	 */
	public void setSelected(final Boolean selected)
	{
		this.selected = selected;
	}

	/**
	 * 
	 * @return name and the checkbox value seperated by a colon
	 */
	@Override
	public String toString()
	{
		return name.toString() + ":" + selected;
	}
}
