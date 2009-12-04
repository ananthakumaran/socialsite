package com.socialsite.persistence;

import java.util.HashSet;
import java.util.Set;

public class Staff extends User
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private University university;
	
	private Set<Course> courses = new HashSet<Course>();
	
	
	public void setUniversity(University university)
	{
		this.university = university;
	}
	
	public University getUniversity()
	{
		return university;
	}
	public void setCourses(Set<Course> courses)
	{
		this.courses = courses;
	}
	public Set<Course> getCourses()
	{
		return courses;
	}
}
