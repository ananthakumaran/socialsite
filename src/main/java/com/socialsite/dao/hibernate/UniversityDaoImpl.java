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

package com.socialsite.dao.hibernate;

import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;

import com.socialsite.dao.UniversityDao;
import com.socialsite.persistence.University;

/**
 * 
 * @author Ananth
 * 
 */
public class UniversityDaoImpl extends AbstractDaoImpl<University> implements UniversityDao
{
	/**
	 * constructor
	 */
	public UniversityDaoImpl()
	{
		super(University.class);
	}

	/**
	 * @see com.socialsite.dao.UniversityDao#countAll(String)
	 */
	public int countAll(final String filter)
	{
		return count(filter, University.class, "name");
	}

	/**
	 * @see com.socialsite.dao.UniversityDao#findAll(String, int, int,
	 *      SortParam)
	 */
	@SuppressWarnings("unchecked")
	public List<University> findAll(final String filter, final int first, final int count,
			final SortParam sortParam)
	{
		return find(filter, first, count, sortParam, University.class, "name");
	}
}
