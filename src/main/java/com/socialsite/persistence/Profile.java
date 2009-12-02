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

	public String getEmail()
	{
		return email;
	}

	public String getFirstName()
	{
		return firstName;
	}

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

	public byte[] getImage()
	{
		return image;
	}

	public String getLastName()
	{
		return lastName;
	}

	public byte[] getThumb()
	{
		return thumb;
	}

	public User getUser()
	{
		return user;
	}

	public void setEmail(final String email)
	{
		this.email = email;
	}

	public void setFirstName(final String firstName)
	{
		this.firstName = firstName;
	}

	public void setId(final long id)
	{
		this.id = id;
	}

	public void setImage(final byte[] image)
	{
		this.image = image;
	}

	public void setLastName(final String lastName)
	{
		this.lastName = lastName;
	}

	public void setThumb(final byte[] thumb)
	{
		this.thumb = thumb;
	}

	public void setUser(final User user)
	{
		this.user = user;
	}
}
