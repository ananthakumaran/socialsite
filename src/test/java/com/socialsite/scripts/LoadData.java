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
import com.socialsite.dao.UniversityDao;
import com.socialsite.dao.UserDao;
import com.socialsite.image.DefaultImage;
import com.socialsite.persistence.Profile;
import com.socialsite.persistence.Student;
import com.socialsite.persistence.University;
import com.socialsite.persistence.User;
import com.socialsite.util.SpringWicketTester;

public class LoadData extends AbstractDaoTest
{

	SpringWicketTester tester;
	@Resource(name = "userDao")
	private UserDao<User> userDao;

	@Resource(name = "profileDao")
	private ProfileDao profileDao;

	@Resource(name = "universityDao")
	private UniversityDao universityDao;

	/**
	 * helper to create profile for users
	 * 
	 * @param users
	 *            users
	 */
	public void createProfiles(final User... users)
	{
		for (final User user : users)
		{
			final Profile profile = new Profile();
			profile.setUser(user);
			user.setProfile(profile);
			new DefaultImage(user.getProfile());
			profileDao.save(user.getProfile());
		}
	}

	/**
	 * run this method to load some data for the site
	 */
	@Test
	@NotTransactional
	public void load()
	{

		// clear the database
		SchemaCreator.create();

		// create some students
		final User user1 = new Student("user1", "password");
		final User user2 = new Student("user2", "password");
		final User user3 = new Student("user3", "password");
		final User user4 = new Student("user4", "password");
		final User user5 = new Student("user5", "password");
		saveUsers(user1, user2, user3, user4, user5);

		// create the profiles
		createProfiles(user1, user2, user3, user4, user5);
		// add some friends for all users
		setFriend(user1, user2, user3, user4, user5);
		setFriend(user2, user3, user4);
		setFriend(user3, user5);
		saveUsers(user1, user2, user3, user4, user5);

		final University university1 = new University("TestUniversity");
		final University university2 = new University("Harvard");
		saveUniversities(university1, university2);

	}

	public void saveUniversities(final University... uinversities)
	{
		for (final University uinversity : uinversities)
		{
			universityDao.save(uinversity);
		}
	}

	/**
	 * helper to save users
	 * 
	 * @param users
	 *            users
	 */
	public void saveUsers(final User... users)
	{
		for (final User user : users)
		{
			userDao.save(user);
		}
	}

	/**
	 * helper method to add many friends
	 * 
	 * @param user
	 *            user
	 * @param friends
	 *            friends to be added
	 */
	public void setFriend(final User user, final User... friends)
	{
		for (final User friend : friends)
		{
			user.addFriend(friend);
		}
		userDao.save(user);
	}

	@Before
	public void setup()
	{
		tester = new SpringWicketTester();
		tester.startPage(SignUpPage.class);

	}
}
