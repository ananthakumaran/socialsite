package com.socialsite.persistence;

public class Admin extends User
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private University university;
	
	public University getUniversity()
	{
		return university;
	}
	
	public void setUniversity(University university)
	{
		this.university = university;
	}
}
