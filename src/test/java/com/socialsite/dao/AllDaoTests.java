package com.socialsite.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.socialsite.dao.hibernate.ProfileDaoTest;
import com.socialsite.dao.hibernate.UserDaoTest;

/**
 * Test suite for all the Dao tests
 * 
 * @author Ananth
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( { UserDaoTest.class, ProfileDaoTest.class })
public class AllDaoTests
{
	// run this class to run all the test
}
