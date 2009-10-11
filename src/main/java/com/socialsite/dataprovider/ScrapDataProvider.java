package com.socialsite.dataprovider;

import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.ScrapDao;
import com.socialsite.dao.UserDao;
import com.socialsite.entitymodel.EntityModel;
import com.socialsite.persistence.Scrap;

/**
 * data provider for scrap list
 * 
 * @author Ananth
 * 
 */
public class ScrapDataProvider extends SortableDataProvider<Scrap>
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	
	/** user id */
	private long id;
	
	/** spring dao to handle scrap object */
	@SpringBean(name="scrapDao")
	private ScrapDao scrapDao;
	
	/** spring dao to handle user object */
	@SpringBean(name="userDao")
	private UserDao userDao;

	public ScrapDataProvider(long id)
	{
		this.id = id;
		InjectorHolder.getInjector().inject(this);
	}
	
	public Iterator<Scrap> iterator(int first, int count)
	{
		return scrapDao.getScraps(userDao.load(id), first, count).iterator();
	}

	public IModel<Scrap> model(Scrap scrap)
	{
		return new EntityModel<Scrap>(scrap,scrapDao);
	}

	public int size()
	{
		return scrapDao.getScrapsCount(userDao.load(id));
	}

}
