package com.socialsite.dataprovider;

import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.UserDao;
import com.socialsite.entitymodel.EntityModel;
import com.socialsite.persistence.User;

/**
 * DataProvider for friends
 * 
 * @author Ananth
 * 
 */
public class FriendsDataProvider extends SortableDataProvider<User>
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/** user id */
	private final long			id;

	/** spring dao to handle user object */
	@SpringBean(name = "userDao")
	private UserDao<User>		userDao;

	public FriendsDataProvider(final long id)
	{
		this.id = id;
		// intializes spring DAO
		InjectorHolder.getInjector().inject(this);
	}

	public Iterator<User> iterator(final int first, final int count)
	{
		return userDao.getFriends(userDao.load(id), first, count).iterator();
	}

	public IModel<User> model(final User user)
	{
		return new EntityModel<User>(user, userDao);
	}

	public int size()
	{
		return userDao.getFriendsCount(userDao.load(id));
	}

}
