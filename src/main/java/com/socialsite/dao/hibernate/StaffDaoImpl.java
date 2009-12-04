package com.socialsite.dao.hibernate;

import com.socialsite.dao.StaffDao;
import com.socialsite.persistence.Staff;

public class StaffDaoImpl extends UserDaoImpl<Staff> implements StaffDao
{
	public StaffDaoImpl()
	{
		super(Staff.class);
	}

}
