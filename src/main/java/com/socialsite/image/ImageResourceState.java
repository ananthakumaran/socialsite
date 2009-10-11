package com.socialsite.image;

import org.apache.wicket.markup.html.DynamicWebResource.ResourceState;
import org.apache.wicket.util.time.Time;

class ImageResourceState extends ResourceState
{
	// CONSTRUCTORS

	ImageResourceState()
	{
		super();
	}

	// MEMBERS

	private String	contentType;

	@Override
	public String getContentType()
	{
		return contentType;
	}

	void setContentType(String contentType)
	{
		this.contentType = contentType;
	}

	private byte[]	data;

	@Override
	public byte[] getData()
	{
		return data;
	}

	void setData(byte[] data)
	{
		this.data = data;
	}

	@Override
	public int getLength()
	{
		return data.length;
	}

	private Time	lastModified;

	@Override
	public Time lastModifiedTime()
	{
		return lastModified;
	}

	// METHODS
}