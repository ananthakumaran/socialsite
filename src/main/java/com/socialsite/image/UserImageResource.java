package com.socialsite.image;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.markup.html.DynamicWebResource;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.value.ValueMap;

import com.socialsite.dao.ProfileDao;

/**
 * handles the request for the images
 * 
 * @author Ananth
 * 
 */
public class UserImageResource extends DynamicWebResource
{

	@SpringBean(name = "profileDao")
	private ProfileDao			profileDao;
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public UserImageResource()
	{

	}

	@Override
	protected ResourceState getResourceState()
	{
		// inject the spring dao
		InjectorHolder.getInjector().inject(this);
		
		// parameters
		ValueMap params = getParameters();
		
		// load the image from the database
		ImageResourceState imageResourceState = new ImageResourceState();
		try
		{
			imageResourceState.setContentType("image/jpeg");
			if (params.containsKey("thumb"))
			{
				imageResourceState.setData(profileDao.getUserThumb(params
					.getAsLong("id")));
			} else
			{
				imageResourceState.setData(profileDao.getUserImage(params
					.getAsLong("id")));
			}

		} catch (Exception e)
		{
			return new ResourceState()
			{

				@Override
				public byte[] getData()
				{
					return null;
				}

				@Override
				public String getContentType()
				{
					return null;
				}
			};
		}
		return imageResourceState;
	}
}
