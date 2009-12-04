package com.socialsite.persistence;

import java.util.HashSet;
import java.util.Set;

public class Student extends User
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	
	
	public Student()
	{
	}	
	public Student(String userName, String password)
	{
		super(userName, password);
	}




	private Set<Course> courses = new HashSet<Course>();
	
	public void setCourses(Set<Course> courses)
	{
		this.courses = courses;
	}
	
	public Set<Course> getCourses()
	{
		return courses;
	}
	
	
}
