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

package com.socialsite.scripts;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.NotTransactional;

import com.socialsite.authentication.SignUpPage;
import com.socialsite.dao.AbstractDaoTest;
import com.socialsite.dao.ProfileDao;
import com.socialsite.dao.UserDao;
import com.socialsite.image.DefaultImage;
import com.socialsite.persistence.Profile;
import com.socialsite.persistence.Student;
import com.socialsite.persistence.User;
import com.socialsite.util.SpringWicketTester;

public class LoadData extends AbstractDaoTest
{
	
	SpringWicketTester	tester;
	@Resource(name = "userDao")
	private  UserDao<User>	userDao;

	@Resource(name="profileDao")
	private ProfileDao profileDao;
	
	@Before
	public void setup()
	{
		tester = new SpringWicketTester();
		tester.startPage(SignUpPage.class);

	}
	
	/**
	 * run this method to load some data 
	 * for the site
	 */
	@Test
	@NotTransactional
	public  void load()
	{
	
		
		// clear the database
		SchemaCreator.create();

		// create some students
		User user1 = new Student("user1", "password");
		User user2 = new Student("user2", "password");
		User user3 = new Student("user3", "password");
		User user4 = new Student("user4", "password");
		User user5 = new Student("user5", "password");
		
		
		userDao.save(user1);
		userDao.save(user2);
		userDao.save(user3);
		userDao.save(user4);
		userDao.save(user5);
		
		
		Profile p1 = new Profile();
		p1.setUser(user1);
		user1.setProfile(p1);
		new DefaultImage(user1.getProfile());
		profileDao.save(user1.getProfile());
		
		

		Profile p2 = new Profile();
		p2.setUser(user2);
		user2.setProfile(p2);
		new DefaultImage(user2.getProfile());
		profileDao.save(user2.getProfile());
		
		

		Profile p3 = new Profile();
		p3.setUser(user3);
		user3.setProfile(p3);
		new DefaultImage(user3.getProfile());
		profileDao.save(user3.getProfile());
		

		Profile p4 = new Profile();
		p4.setUser(user4);
		user4.setProfile(p4);
		new DefaultImage(user4.getProfile());
		profileDao.save(user4.getProfile());
		

		Profile p5 = new Profile();
		p5.setUser(user5);
		user5.setProfile(p5);
		new DefaultImage(user5.getProfile());
		profileDao.save(user5.getProfile());
		user1.addFriend(user2);
		System.out.print("user2 is a friend of user1 ");
		
		user1.addFriend(user3);
		System.out.print("user3 is a friend of user1 ");
		user1.addFriend(user4);
		System.out.print("user4 is a friend of user1 ");
		user1.addFriend(user5);
		System.out.print("user5 is a friend of user1 ");
		
		userDao.save(user1);
		
		user2.addFriend(user3);
		System.out.print("user3 is a friend of user2 ");
		user2.addFriend(user4);
		System.out.print("user4 is a friend of user3 ");
		
		userDao.save(user2);
		
		user3.addFriend(user5);
		System.out.print("user5 is a friend of user3 ");
		
		userDao.save(user3);
		
		
		userDao.save(user1);
		userDao.save(user2);
		userDao.save(user3);
		userDao.save(user4);
		userDao.save(user5);
		
	}

}
