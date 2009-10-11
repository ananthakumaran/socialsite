package com.socialsite.persistence;

import java.util.HashSet;
import java.util.Set;

import org.jasypt.util.password.StrongPasswordEncryptor;

/**
 * User Details .This is the business object that we persist to the Database
 * 
 * @author Ananth
 */
public class User implements AbstractDomain
{
	private static final long	serialVersionUID	= 1L;
	private long				id;
	private String				userName;
	private String				password;

	private Set<User>			friends				= new HashSet<User>();
	
	private Set<FriendRequest>	friendRequests		= new HashSet<FriendRequest>();

	private Set<Scrap> scraps = new HashSet<Scrap>();
	
	private Set<Scrap> scrapsSend = new HashSet<Scrap>();
	
	/**
	 * constructor
	 */
	public User()
	{

	}

	/**
	 * constructor
	 * 
	 * @param userName
	 *            username of the user
	 * @param password
	 *            password of the user
	 */
	public User(String userName, String password)
	{
		setUserName(userName);
		setPassword(encryptPassword(password));
	}

	/*** friends **/

	/**
	 * @param friend
	 *            other user
	 */
	public void addFriend(User friend)
	{
		getFriends().add(friend);
	}

	/**
	 * check for the password match
	 * 
	 * @param password
	 *            password
	 * @return true if the password matches or false
	 */
	public boolean matchPassword(String password)
	{
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		return passwordEncryptor.checkPassword(password, getPassword());
	}

	/**
	 * encrypts the password
	 * 
	 * @param password
	 *            password
	 * @return encrypted password
	 */
	private static String encryptPassword(String password)
	{
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		String encryptedPassword = passwordEncryptor.encryptPassword(password);
		return encryptedPassword;

	}

	/**************** accessor methods ***********************/
	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	private Set<User> getFriends()
	{
		return friends;
	}

	public void setFriends(Set<User> friends)
	{
		this.friends = friends;
	}

	public void setFriendRequests(Set<FriendRequest> friendRequests)
	{
		this.friendRequests = friendRequests;
	}

	public Set<FriendRequest> getFriendRequests()
	{
		return friendRequests;
	}

	public Set<Scrap> getScraps()
	{
		return scraps;
	}

	public void setScraps(Set<Scrap> scraps)
	{
		this.scraps = scraps;
	}

	public Set<Scrap> getScrapsSend()
	{
		return scrapsSend;
	}

	public void setScrapsSend(Set<Scrap> scrapsSend)
	{
		this.scrapsSend = scrapsSend;
	}

}
