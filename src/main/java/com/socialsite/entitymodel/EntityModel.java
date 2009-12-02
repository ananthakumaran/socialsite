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

import com.socialsite.dao.AbstractDao;
import com.socialsite.persistence.AbstractDomain;

/**
 * Generic LodableDetachable Model for all the Domain class in the Application
 * 
 * @author Ananth
 */
public class EntityModel<T extends AbstractDomain> extends
		AbstractEntityModel<T>
{

	private static final long		serialVersionUID	= 1L;
	/** DAO to load the domanin object */
	private final AbstractDao<T>	dao;

	/**
	 * creates a detachable Domain object
	 * 
	 * @param domain
	 *            detachable Domain object
	 * @param dao
	 *            Dao to acces the Domain object
	 */
	public EntityModel(final T domain, final AbstractDao<T> dao)
	{
		super(domain);
		this.dao = dao;
	}

	/**
	 * returns the Domain if available or fetch it from the database
	 * 
	 * @return Domain
	 */
	@Override
	protected T load()
	{
		if (domain == null)
		{
			domain = dao.load(id);
		}
		return domain;
	}
}
