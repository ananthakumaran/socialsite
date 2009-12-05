package com.socialsite.dataprovider;

import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.UserDao;
import com.socialsite.entitymodel.EntityModel;
import com.socialsite.entitymodel.StringWrapper;
import com.socialsite.persistence.User;

/**
 * data provider for search page
 * 
 * @author Ananth
 * 
 */
public class SearchDataProvider extends SortableDataProvider<User>
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	/** filter text */
	private final StringWrapper	filter;
	/** DAO to access the group details */
	@SpringBean(name = "userDao")
	private UserDao<User>		userDao;

	public SearchDataProvider(final StringWrapper filter)
	{
		this.filter = filter;
		// intializes spring DAO
		InjectorHolder.getInjector().inject(this);

		setSort("userName", true);
	}

	public Iterator<User> iterator(final int first, final int count)
	{
		return userDao.findAll(filter.toString(), first, count, getSort())
			.iterator();
	}

	public IModel<User> model(final User domain)
	{
		return new EntityModel<User>(domain, userDao);
	}

	public int size()
	{
		return userDao.countAll(filter.toString());
	}

}
