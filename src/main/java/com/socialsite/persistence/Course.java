package com.socialsite.persistence;

import java.util.HashSet;
import java.util.Set;

public class Course implements AbstractDomain
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private long				id;

	private University university;
	
	private Staff staff;
	
	private Set<Student> students = new HashSet<Student>();
	
	
	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}
	
	public Set<Student> getStudents()
	{
		return students;
	}
	public void setStudents(Set<Student> students)
	{
		this.students = students;
	}
	
	public void setStaff(Staff staff)
	{
		this.staff = staff;
	}
	public Staff getStaff()
	{
		return staff;
	}
	
	public University getUniversity()
	{
		return university;
	}
	
	public void setUniversity(University university)
	{
		this.university = university;
	}
}
