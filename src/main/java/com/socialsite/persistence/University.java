package com.socialsite.persistence;

import java.util.HashSet;
import java.util.Set;

public class University implements AbstractDomain
{
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private long id;
	
	
	private Admin admin;
	
	private Set<Staff> staffs = new HashSet<Staff>();
	
	private Set<Course> courses = new HashSet<Course>();
	
	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}
	
	public void setStaffs(Set<Staff> staffs)
	{
		this.staffs = staffs;
	}
	public Set<Staff> getStaffs()
	{
		return staffs;
	}
	public void setAdmin(Admin admin)
	{
		this.admin = admin;
	}
	public Admin getAdmin()
	{
		return admin;
	}
	public Set<Course> getCourses()
	{
		return courses;
	}
	public void setCourses(Set<Course> courses)
	{
		this.courses = courses;
	}
}
