package com.socialsite.dao.hibernate;

import static junit.framework.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.transaction.annotation.Transactional;

import com.socialsite.dao.AbstractDaoTest;
import com.socialsite.dao.ProfileDao;
import com.socialsite.persistence.Profile;
import com.socialsite.persistence.User;
/**
 * 
 * @author Ananth
 *
 */
public class ProfileDaoTest extends AbstractDaoTest
{
	@Resource(name = "profileDao")
	private ProfileDao	profileDao;

	@Test
	@Transactional
	public void testCreate()
	{
		User ananth = new User("ananth", "pass");
		Profile ananthProfile = new Profile();
		ananthProfile.setUser(ananth);
		ananthProfile.setEmail("ananth@gmail.com");
		ananthProfile.setFirstName("ananth");
		ananthProfile.setLastName("Kumaran");

		profileDao.save(ananthProfile);

		// flush the session so we can get the record using JDBC template
		SessionFactoryUtils.getSession(sessionFactory, false).flush();

		int userResult = simpleJdbcTemplate
			.queryForInt("select count(*) from user");
		assertEquals("user table should contain one entry ", userResult, 1);

		int profileResult = simpleJdbcTemplate
			.queryForInt("select count(*) from profile");
		assertEquals("profile table should contain one entry ", profileResult,
			1);

		Long user_id = simpleJdbcTemplate
			.queryForLong("select id from user where username='ananth' ");
		Long profile_user_id = simpleJdbcTemplate
			.queryForLong("select user_id from profile where firstname='ananth' ");

		assertEquals("Both the user_id and profile_user_id should be equal",
			user_id, profile_user_id);

	}

	@Test
	@Transactional
	public void testGetUserImage()
	{
		User ananth = new User("ananth", "pass");
		Profile ananthProfile = new Profile();
		ananthProfile.setUser(ananth);
		// set some dummy data for image
		ananthProfile.setImage("dummy data".getBytes());
		profileDao.save(ananthProfile);

		byte[] image = profileDao.getUserImage(ananth.getId());
		assertNotNull("should return the data", image);
		assertEquals("image data should be equal to  'dummy data' ",
			new String(image), "dummy data");

	}
	
	@Test
	@Transactional
	public void testGetUserThumb()
	{
		User ananth = new User("ananth", "pass");
		Profile ananthProfile = new Profile();
		ananthProfile.setUser(ananth);
		// set some dummy data for image
		ananthProfile.setThumb("dummy data".getBytes());
		profileDao.save(ananthProfile);

		byte[] image = profileDao.getUserThumb(ananth.getId());
		assertNotNull("should return the data", image);
		assertEquals("image data should be equal to  'dummy data' ",
			new String(image), "dummy data");

	}

}
