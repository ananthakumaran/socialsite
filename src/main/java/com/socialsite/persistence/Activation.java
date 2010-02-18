package com.socialsite.persistence;

/**
 * 
 * Activation Domain Model
 * 
 * @author Ananth
 * 
 */
public class Activation implements AbstractDomain
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private Admin admin;
	private String universityName;

	public Activation()
	{
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public void setAdmin(Admin admin)
	{
		this.admin = admin;
	}

	public void setUniversityName(String universityName)
	{
		this.universityName = universityName;
	}

	public Admin getAdmin()
	{
		return admin;
	}

	public String getUniversityName()
	{
		return universityName;
	}

}
