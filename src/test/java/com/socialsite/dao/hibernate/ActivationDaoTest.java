package com.socialsite.dao.hibernate;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.transaction.annotation.Transactional;

import com.socialsite.dao.AbstractDaoTestHelper;
import com.socialsite.persistence.Activation;
import com.socialsite.persistence.Admin;

/**
 * 
 * @author Ananth
 * 
 */
public class ActivationDaoTest extends AbstractDaoTestHelper
{
	@Test
	@Transactional
	public void createActivation()
	{
		Admin admin = new Admin();
		admin.setUserName("admin");
		admin.setPassword("password");
		saveUsers(admin);

		Activation activation = new Activation();
		activation.setAdmin(admin);
		activation.setUniversityName("testUniversity");

		activationDao.save(activation);
		// flush the session so we can get the record using JDBC template
		SessionFactoryUtils.getSession(sessionFactory, false).flush();

		assertEquals("activation count should be one ", 1, simpleJdbcTemplate
				.queryForInt("select count(*) activation"));
	}
}
