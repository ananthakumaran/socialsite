package com.socialsite.dao.hibernate;

import com.socialsite.dao.AdminDao;
import com.socialsite.persistence.Admin;

public class AdminDaoImpl extends UserDaoImpl<Admin> implements AdminDao
{

	public AdminDaoImpl()
	{
		super(Admin.class);
	}
}
