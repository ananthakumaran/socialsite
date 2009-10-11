package com.socialsite.persistence;

/**
 * @author Ananth
 * 
 */
public class Profile implements AbstractDomain
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private long				id;
	private User				user;
	private String				firstName;
	private String				lastName;
	private String				email;
	byte[]						image;
	private byte[]				thumb;

	/**
	 * TODO add other fields link address , birthday etc;
	 */

	/***** accessor methods ****/
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.persistence.AbstractDomain#getId()
	 */
	public long getId()
	{
		return id;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public byte[] getImage()
	{
		return image;
	}

	public void setImage(byte[] image)
	{
		this.image = image;
	}

	public byte[] getThumb()
	{
		return thumb;
	}

	public void setThumb(byte[] thumb)
	{
		this.thumb = thumb;
	}
}
