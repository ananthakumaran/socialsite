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
 * Test suite for all the Dao tests
 * 
 * @author Ananth
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses( {
	
	LoginPageTest.class,
	ScrapDaoTest.class,
	UserDaoTest.class,
	ProfileDaoTest.class ,
	FriendRequestMsgDaoTest.class
	
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
