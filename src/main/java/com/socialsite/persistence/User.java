package com.socialsite.persistence;

import java.util.HashSet;
import java.util.Set;

import org.jasypt.util.password.StrongPasswordEncryptor;

/**
 * User Details .This is the business object that we persist to the Database
 * 
 * @author Ananth
 */
public abstract class User implements AbstractDomain
{
	private static final long	serialVersionUID	= 1L;

	/**
	 * encrypts the password
	 * 
	 * @param password
	 *            password
	 * @return encrypted password
	 */
	private static String encryptPassword(final String password)
	{
		final StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		final String encryptedPassword = passwordEncryptor
			.encryptPassword(password);
		return encryptedPassword;

	}

	private long		id;
	private String		userName;

	private String		password;

	private Profile		profile;

	private Set<User>	friends		= new HashSet<User>();

	private Set<Scrap>	scraps		= new HashSet<Scrap>();

	private Set<Scrap>	scrapsSend	= new HashSet<Scrap>();

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
	public User(final String userName, final String password)
	{
		setUserName(userName);
		setPassword(encryptPassword(password));
	}

	/**
	 * @param friend
	 *            other user
	 */
	public void addFriend(final User friend)
	{
		getFriends().add(friend);
		friend.getFriends().add(this);

	}

	/*** friends **/

	public Set<User> getFriends()
	{
		return friends;
	}

	/**************** accessor methods ***********************/
	public long getId()
	{
		return id;
	}

	public String getPassword()
	{
		return password;
	}

	public Profile getProfile()
	{
		return profile;
	}

	public Set<Scrap> getScraps()
	{
		return scraps;
	}

	public Set<Scrap> getScrapsSend()
	{
		return scrapsSend;
	}

	public String getUserName()
	{
		return userName;
	}

	/**
	 * check for the password match
	 * 
	 * @param password
	 *            password
	 * @return true if the password matches or false
	 */
	public boolean matchPassword(final String password)
	{
		final StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		return passwordEncryptor.checkPassword(password, getPassword());
	}

	public void setFriends(final Set<User> friends)
	{
		this.friends = friends;
	}

	public void setId(final long id)
	{
		this.id = id;
	}

	public void setPassword(final String password)
	{
		this.password = password;
	}

	public void setProfile(final Profile profile)
	{
		this.profile = profile;
	}

	public void setScraps(final Set<Scrap> scraps)
	{
		this.scraps = scraps;
	}

	public void setScrapsSend(final Set<Scrap> scrapsSend)
	{
		this.scrapsSend = scrapsSend;
	}

	public void setUserName(final String userName)
	{
		this.userName = userName;
	}

}
