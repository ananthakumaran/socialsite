package com.socialsite.dao.hibernate;

import com.socialsite.dao.ActivationDao;
import com.socialsite.persistence.Activation;

/**
 * 
 * @author Ananth
 * 
 */
public class ActivationDaoImpl extends AbstractDaoImpl<Activation> implements ActivationDao
{
	public ActivationDaoImpl()
	{
		super(Activation.class);
	}

}
