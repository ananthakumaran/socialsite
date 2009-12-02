package com.socialsite.image;

import org.apache.wicket.markup.html.DynamicWebResource.ResourceState;
import org.apache.wicket.util.time.Time;

class ImageResourceState extends ResourceState
{
	// CONSTRUCTORS

	private String	contentType;

	// MEMBERS

	private byte[]	data;

	private Time	lastModified;

	ImageResourceState()
	{
		super();
	}

	@Override
	public String getContentType()
	{
		return contentType;
	}

	@Override
	public byte[] getData()
	{
		return data;
	}

	@Override
	public int getLength()
	{
		return data.length;
	}

	@Override
	public Time lastModifiedTime()
	{
		return lastModified;
	}

	void setContentType(final String contentType)
	{
		this.contentType = contentType;
	}

	void setData(final byte[] data)
	{
		this.data = data;
	}

	// METHODS
}