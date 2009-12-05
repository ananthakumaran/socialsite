package com.socialsite.persistence;

import java.util.HashSet;
import java.util.Set;

public class Student extends User
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private Set<Course>			courses				= new HashSet<Course>();

	public Student()
	{
	}

	public Student(final String userName, final String password)
	{
		super(userName, password);
	}

	public Set<Course> getCourses()
	{
		return courses;
	}

	public void setCourses(final Set<Course> courses)
	{
		this.courses = courses;
	}

}
