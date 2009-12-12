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

import com.socialsite.dao.MessageDao;
import com.socialsite.dao.UserDao;
import com.socialsite.entitymodel.EntityModel;
import com.socialsite.persistence.Message;
import com.socialsite.persistence.User;

public class MessageDataProvider extends SortableDataProvider<Message>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** user id */
	private final long id;

	/** spring dao to handle user object */
	@SpringBean(name = "userDao")
	private UserDao<User> userDao;

	/** spring dao to handle message object */
	@SpringBean(name = "messageDao")
	private MessageDao<Message> messageDao;

	/**
	 * constructor
	 * 
	 * @param id
	 *            user id
	 */
	public MessageDataProvider(final long id)
	{
		this.id = id;
		// intializes spring DAO
		InjectorHolder.getInjector().inject(this);
	}

	public Iterator<? extends Message> iterator(final int first, final int count)
	{
		return messageDao.getMessage(userDao.load(id), first, count).iterator();
	}

	public IModel<Message> model(final Message msg)
	{
		return new EntityModel<Message>(msg, messageDao);
	}

	public int size()
	{
		System.out.println("size" + messageDao.getMessageCount(userDao.load(id)));
		return messageDao.getMessageCount(userDao.load(id));

	}

}
