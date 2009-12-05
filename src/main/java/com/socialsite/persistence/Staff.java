package com.socialsite.persistence;

import java.util.HashSet;
import java.util.Set;

public class Staff extends User
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private University			university;

	private Set<Course>			courses				= new HashSet<Course>();

	public Set<Course> getCourses()
	{
		return courses;
	}

	public University getUniversity()
	{
		return university;
	}

	public void setCourses(final Set<Course> courses)
	{
		this.courses = courses;
	}

	public void setUniversity(final University university)
	{
		this.university = university;
	}
}
