package com.socialsite.dao.hibernate;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.socialsite.dao.AbstractDaoTest;
import com.socialsite.dao.UniversityDao;
import com.socialsite.persistence.University;

/**
 * 
 * 
 * 
 * @author Ananth
 * 
 */
public class UniversityDaoTest extends AbstractDaoTest
{

	@Resource(name = "universityDao")
	private UniversityDao universityDao;

	@Test
	@Transactional
	public void countTest()
	{
		final University u = new University("test");
		universityDao.save(u);
		assertEquals(1, universityDao.countAll(""));
	}

	@Test
	@Transactional
	public void findAllTest()
	{
		// TODO check some more conditions
		final University u = new University("test");
		universityDao.save(u);
		assertEquals(1, universityDao.findAll("te", 0, 5, new SortParam("name", true)).size());
	}

}
