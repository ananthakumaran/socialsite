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

	private University			university;

	private Staff				staff;

	private Set<Student>		students			= new HashSet<Student>();

	public long getId()
	{
		return id;
	}

	public Staff getStaff()
	{
		return staff;
	}

	public Set<Student> getStudents()
	{
		return students;
	}

	public University getUniversity()
	{
		return university;
	}

	public void setId(final long id)
	{
		this.id = id;
	}

	public void setStaff(final Staff staff)
	{
		this.staff = staff;
	}

	public void setStudents(final Set<Student> students)
	{
		this.students = students;
	}

	public void setUniversity(final University university)
	{
		this.university = university;
	}
}
