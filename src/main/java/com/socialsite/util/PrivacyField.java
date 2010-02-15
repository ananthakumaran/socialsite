package com.socialsite.util;

import java.io.Serializable;

import com.socialsite.profile.Access;

public class PrivacyField implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String value;
	private Access privacy;


	public PrivacyField()
	{
	}

	public PrivacyField(String value, Access privacy)
	{
		this.value = value;
		this.privacy = privacy;
	}

	public Access getPrivacy()
	{
		return privacy;
	}

	public String getValue()
	{
		return value;
	}

	public void setPrivacy(Access privacy)
	{
		this.privacy = privacy;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
}
