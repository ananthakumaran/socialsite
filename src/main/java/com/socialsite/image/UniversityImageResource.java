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
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.AbstractImageDao;
import com.socialsite.dao.UniversityDao;
import com.socialsite.persistence.University;

/**
 * 
 * @author Ananth
 * 
 */
public class UniversityImageResource extends AbstractImageResource<University>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** used to access the university images **/
	@SpringBean(name = "universityDao")
	private UniversityDao universityDao;


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.image.AbstractImageResource#getImageDao()
	 */
	@Override
	public AbstractImageDao<University> getImageDao()
	{
		if (universityDao == null)
		{
			// inject the spring dao
			InjectorHolder.getInjector().inject(this);
		}
		return universityDao;
	}

}
