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

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.StaffDao;
import com.socialsite.dao.UserDao;
import com.socialsite.entitymodel.EntityModel;
import com.socialsite.persistence.University;
import com.socialsite.persistence.User;

/**
 * 
 * @author Ananth
 * 
 */
public class StaffDataProvider extends SortableDataProvider<User>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** spring dao to handle user object */
	@SpringBean(name = "staffDao")
	private StaffDao staffDao;

	@SpringBean(name = "userDao")
	private UserDao<User> userDao;

	/** university */
	private final University university;

	/**
	 * 
	 * @param university
	 */
	public StaffDataProvider(final University university)
	{
		this.university = university;
		// intializes spring DAO
		InjectorHolder.getInjector().inject(this);
	}

	public Iterator<User> iterator(final int first, final int count)
	{
		return new ArrayList<User>(staffDao.getStaffs(university.getId(), first, count)).iterator();
	}

	public IModel<User> model(final User staff)
	{
		return new EntityModel<User>(staff, userDao);
	}

	public int size()
	{
		// TODO write methods in staff dao
		return university.getStaffs().size();
	}

}
