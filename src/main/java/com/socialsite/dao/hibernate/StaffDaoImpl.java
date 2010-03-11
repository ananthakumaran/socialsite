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

import org.hibernate.Query;

import com.socialsite.dao.StaffDao;
import com.socialsite.persistence.Staff;

/**
 * 
 * @author Ananth
 * 
 */
public class StaffDaoImpl extends UserDaoImpl<Staff> implements StaffDao
{
	public StaffDaoImpl()
	{
		super(Staff.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.dao.StaffDao#getStaffs(long, int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<Staff> getStaffs(final long universityId, final int first, final int count)
	{
		final Query query = getSession().createQuery(
				"select u.staffs from University u where u.id = :universityId").setParameter(
				"universityId", universityId).setFetchSize(count).setFirstResult(first)
				.setMaxResults(count);
		return query.list();
	}


}
