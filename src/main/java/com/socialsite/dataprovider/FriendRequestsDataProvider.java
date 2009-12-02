/**
 * 
 */
package com.socialsite.dataprovider;

import java.util.Iterator;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.FriendRequestDao;
import com.socialsite.dao.UserDao;
import com.socialsite.entitymodel.EntityModel;
import com.socialsite.persistence.FriendRequest;

/**
 * Data provider for friend request
 * 
 * @author Ananth
 * 
 */
public class FriendRequestsDataProvider implements IDataProvider<FriendRequest>
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	/** filter text */

	/** user id */
	private final long			id;
	/** spring doa to handle user object */
	@SpringBean(name = "userDao")
	private UserDao				userDao;
	/** spring doa to handle friendrequest object */
	@SpringBean(name = "friendRequestDao")
	private FriendRequestDao	friendRequestDao;

	/**
	 * constructor
	 * 
	 * @param id
	 *            user id
	 */
	public FriendRequestsDataProvider(final long id)
	{
		this.id = id;
		// intializes spring DAO
		InjectorHolder.getInjector().inject(this);

	}

	public void detach()
	{

	}

	public Iterator<FriendRequest> iterator(final int first, final int count)
	{
		// TODO add implementation for the count
		return userDao.load(id).getFriendRequests().iterator();
	}

	public IModel<FriendRequest> model(final FriendRequest friendRequest)
	{
		return new EntityModel<FriendRequest>(friendRequest, friendRequestDao);
	}

	public int size()
	{
		// TODO write seperate method to calculate the
		// size in the dao
		return userDao.load(id).getFriendRequests().size();
	}

}
