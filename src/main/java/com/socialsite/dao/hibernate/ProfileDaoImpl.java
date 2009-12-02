package com.socialsite.dao.hibernate;

import com.socialsite.dao.ProfileDao;
import com.socialsite.persistence.Profile;

/**
 * DAO implementation for Profile object
 * 
 * @author Ananth
 * 
 */
public class ProfileDaoImpl extends AbstractDaoImpl<Profile> implements
		ProfileDao
{
	/**
	 * constructor
	 */
	public ProfileDaoImpl()
	{
		super(Profile.class);
	}

	/**
	 * @see com.socialsite.dao.ProfileDao#getUserImage(Long)
	 */
	public byte[] getUserImage(final Long userId)
	{
		return (byte[]) getSession().createQuery(
			"select image from Profile where user.id = :userid ").setParameter(
			"userid", userId).uniqueResult();
	}

	/**
	 * @see com.socialsite.dao.ProfileDao#getUserThumb(Long)
	 */
	public byte[] getUserThumb(final Long userId)
	{
		return (byte[]) getSession().createQuery(
			"select thumb from Profile where user.id = :userid ").setParameter(
			"userid", userId).uniqueResult();
	}
}
