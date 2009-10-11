/**
 * JQMS (Java Quiz Management System)
 *
 * A web Application used to manage and conduct
 * online test
 *
 * JQMS Copyright (C) 2009  Anantha Kumaran.N
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 *  contacts  ananthakumaran@gmail.com
 *
 **/

package com.socialsite.entitymodel;

import java.io.Serializable;

/**
 * helper class to use string as a model
 * 
 * @author Ananth
 */
public class StringWrapper implements Serializable
{

	private static final long	serialVersionUID	= 1L;
	/** holds the model value */
	private String				model;

	/**
	 * constructor
	 */
	public StringWrapper()
	{
		// initialize with empty strings
		model = "";
	}

	/**
	 * constructor
	 * 
	 * @param model
	 *            string model
	 */
	public StringWrapper(String model)
	{
		this.model = model;
	}

	/**
	 * getter
	 * 
	 * @return model string
	 */
	public String getModel()
	{
		return model;
	}

	/**
	 * setter
	 * 
	 * @param model
	 *            string model
	 */
	public void setModel(String model)
	{
		this.model = model;
	}

	/**
	 * @return model string
	 */
	@Override
	public String toString()
	{
		return model;
	}
}
