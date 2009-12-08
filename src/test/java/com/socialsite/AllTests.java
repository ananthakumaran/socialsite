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

package com.socialsite;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.socialsite.authentication.LoginPageTest;
import com.socialsite.dao.hibernate.FriendRequestMsgDaoTest;
import com.socialsite.dao.hibernate.ProfileDaoTest;
import com.socialsite.dao.hibernate.ScrapDaoTest;
import com.socialsite.dao.hibernate.UserDaoTest;
import com.socialsite.scripts.SchemaCreator;

/**
 * Test suite for all the tests
 * 
 * @author Ananth
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( {

LoginPageTest.class, ScrapDaoTest.class, UserDaoTest.class,
		ProfileDaoTest.class, FriendRequestMsgDaoTest.class

})
public class AllTests
{

	@BeforeClass
	public static void setup()
	{

		SchemaCreator.create();
	}
	// run thisclass to run all the test
}
