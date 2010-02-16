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

import java.io.Serializable;

import com.socialsite.profile.Access;

public class PrivacyModel implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String value;
	private Access privacy;


	public PrivacyModel()
	{
	}

	public PrivacyModel(String value, Access privacy)
	{
		this.value = value;
		this.privacy = privacy;
	}

	public Access getPrivacy()
	{
		return privacy;
	}

	public String getValue()
	{
		return value;
	}

	public void setPrivacy(Access privacy)
	{
		this.privacy = privacy;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
}
