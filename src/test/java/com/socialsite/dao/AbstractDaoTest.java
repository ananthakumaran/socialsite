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

import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.socialsite.scripts.SchemaCreator;

/**
 * Abstract test class for all the Dao test
 * 
 * @author Ananth
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
abstract public class AbstractDaoTest extends AbstractTransactionalJUnit4SpringContextTests
{
	/**
	 * cleans the database before the execution of the test
	 */
	@BeforeClass
	public static void setUpDatabase()
	{
		SchemaCreator.create();
	}

	@Autowired
	protected SessionFactory	sessionFactory;
}
