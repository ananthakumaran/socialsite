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

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.socialsite.dao.StaffRequestMsgDao;
import com.socialsite.persistence.Staff;
import com.socialsite.persistence.StaffRequestMsg;
import com.socialsite.persistence.University;

public class StaffRequestMsgDaoImpl extends MessageDaoImpl<StaffRequestMsg>
		implements
			StaffRequestMsgDao
{

	public StaffRequestMsgDaoImpl()
	{
		super(StaffRequestMsg.class);
	}

	public boolean hasRequest(Staff staff, University university)
	{
		final Criteria criteria = getSession().createCriteria(domainClass);
		criteria.add(Restrictions.eq("university", university));
		criteria.add(Restrictions.eq("sender", staff));
		criteria.setProjection(Projections.rowCount());
		final int result = (Integer)criteria.uniqueResult();
		return result == 0 ? false : true;
	}
}
