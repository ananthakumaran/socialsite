package com.socialsite.dao;

import com.socialsite.persistence.Profile;

/**
 * DAO for the User object
 * 
 * @author Ananth
 */
public interface ProfileDao extends AbstractDao<Profile>
{

	/**
	 * get the userImage of the user
	 * 
	 * @param userId
	 *            user id
	 * @return byte[] contains the userImage
	 */
	public byte[] getUserImage(Long userId);

	/**
	 * get the thumbnail userImage of the user
	 * 
	 * @param userId
	 *            user id
	 * @return byte[] thumbnail contains the userImage
	 */
	public byte[] getUserThumb(Long userId);

}
