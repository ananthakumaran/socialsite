package com.socialsite.dao.hibernate;

import com.socialsite.dao.StudentDao;
import com.socialsite.persistence.Student;

public class StudentDaoImpl extends UserDaoImpl<Student> implements StudentDao
{

	public StudentDaoImpl()
	{
		super(Student.class);
	}

}
