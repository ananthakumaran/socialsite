/**
 *     Copyright SocialSite (C) 2009
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
import com.socialsite.persistence.User;

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
	private static final long serialVersionUID = 1L;

	/** user id */
	private final long id;

	/** spring dao to handle scrap object */
	@SpringBean(name = "scrapDao")
	private ScrapDao scrapDao;

	/** spring dao to handle user object */
	@SpringBean(name = "userDao")
	private UserDao<User> userDao;

	public ScrapDataProvider(final long id)
	{
		this.id = id;
		InjectorHolder.getInjector().inject(this);
	}

	public Iterator<Scrap> iterator(final int first, final int count)
	{
		return scrapDao.getScraps(userDao.load(id), first, count).iterator();
	}

	public IModel<Scrap> model(final Scrap scrap)
	{
		return new EntityModel<Scrap>(scrap, scrapDao);
	}

	public int size()
	{
		return scrapDao.getScrapsCount(userDao.load(id));
	}

}
