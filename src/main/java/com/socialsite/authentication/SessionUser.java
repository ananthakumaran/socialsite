package com.socialsite.authentication;

import org.apache.wicket.IClusterable;
import org.apache.wicket.authorization.strategies.role.Roles;

/**
 * This object will be stored in the session
 * 
 * @author Ananth
 * 
 */
public class SessionUser implements IClusterable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private final long			id;
	private Roles				roles;

	public SessionUser(final long id, final Roles roles)
	{
		this.id = id;
		this.roles = roles;
	}

	public long getId()
	{
		return id;
	}

	public Roles getRoles()
	{
		return roles;
	}

	/**
	 * Whether this user has any of the given roles.
	 * 
	 * @param roles
	 *            set of roles
	 * @return whether this user has any of the given roles
	 */
	public boolean hasAnyRole(final Roles roles)
	{
		return this.roles.hasAnyRole(roles);
	}

	/**
	 * Whether this user has the given role.
	 * 
	 * @param role
	 * @return whether this user has the given role
	 */
	public boolean hasRole(final String role)
	{
		return roles.hasRole(role);
	}

	public void setRoles(final Roles roles)
	{
		this.roles = roles;
	}

}
