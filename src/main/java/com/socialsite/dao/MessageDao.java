package com.socialsite.dao;

import java.util.List;

import com.socialsite.persistence.User;

public interface MessageDao<T> extends AbstractDao<T>
{

	/**
	 * get the list of message for the user
	 * 
	 * @param user
	 *            user object
	 * @param first
	 *            first item index of the result
	 * @param count
	 *            no of item
	 * @return List of messages
	 */
	public List<T> getMessage(User user, int first, int count);

	/**
	 * gets the no of messages
	 * 
	 * @param user
	 *            user object
	 * @return no of message for the user
	 */
	public int getMessageCount(User user);

}
