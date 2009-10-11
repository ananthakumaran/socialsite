package com.socialsite.dao;

import java.util.List;

import com.socialsite.persistence.Scrap;
import com.socialsite.persistence.User;

/**
 * DAO for the Scrap object
 * 
 * @author Ananth
 * 
 */
public interface ScrapDao extends AbstractDao<Scrap>
{
	/**
	 * find the scraps
	 * 
	 * @param user
	 *            user object
	 * @param first
	 *            first item no of the user List
	 * @param count
	 *            no of items needed in the user List
	 * @return List containig all the scraps
	 */
	public List<Scrap> getScraps(User user, int first, int count);

	/**
	 * get the count of scraps
	 * 
	 * @param user
	 *            user object
	 * @return the count of scraps
	 */
	public int getScrapsCount(User user);

}
