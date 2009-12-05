package com.socialsite.persistence;

import java.util.HashSet;
import java.util.Set;

public class University implements AbstractDomain
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private long				id;

	private Admin				admin;

	private Set<Staff>			staffs				= new HashSet<Staff>();

	private Set<Course>			courses				= new HashSet<Course>();

	public Admin getAdmin()
	{
		return admin;
	}

	public Set<Course> getCourses()
	{
		return courses;
	}

	public long getId()
	{
		return id;
	}

	public Set<Staff> getStaffs()
	{
		return staffs;
	}

	public void setAdmin(final Admin admin)
	{
		this.admin = admin;
	}

	public void setCourses(final Set<Course> courses)
	{
		this.courses = courses;
	}

	public void setId(final long id)
	{
		this.id = id;
	}

	public void setStaffs(final Set<Staff> staffs)
	{
		this.staffs = staffs;
	}
}
