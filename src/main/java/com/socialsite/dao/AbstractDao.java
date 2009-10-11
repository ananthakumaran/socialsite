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

package com.socialsite.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * Abstract DAO for all Domain Objects
 * 
 * @author Ananth
 */
public interface AbstractDao<T>
{

	/**
	 * find the number of unique rows in the given domainclass (table)
	 * 
	 * @return no of unique rows in the given domainclass (table)
	 */
	int countAll();

	/**
	 * removes the given domainclass object from the Database
	 * 
	 * @param object
	 *            object to be deleted
	 */
	@Transactional
	void delete(T o);

	/**
	 * find all the rows in the table and return it as a list
	 * 
	 * @return List contains all the rows in the given domainclass (table)
	 */
	List<T> findAll();

	/**
	 * load the domainclass of the given id
	 * 
	 * @param id
	 *            id of the object
	 * @return entity object or null if the id doesn't match
	 */
	T load(long id);

	/**
	 * merges the Detached object with the Session
	 * 
	 * @param object
	 *            object to be merged
	 */
	T merge(T o);

	/**
	 * saves or update the object state in the database
	 * 
	 * @param object
	 *            object to be saved
	 */
	@Transactional
	void save(T o);
}
