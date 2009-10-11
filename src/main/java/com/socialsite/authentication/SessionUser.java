package com.socialsite.authentication;

import org.apache.wicket.IClusterable;
import org.apache.wicket.authorization.strategies.role.Roles;

/**
 * This object will be stored in the session
 * 
 * @author Ananth
 * 
 */
public class SessionUser implements IClusterable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final long id;
	private Roles roles;

	public SessionUser(long id, Roles roles) {
		this.id = id;
		this.roles = roles;
	}

	/**
	 * Whether this user has the given role.
	 * 
	 * @param role
	 * @return whether this user has the given role
	 */
	public boolean hasRole(String role) {
		return this.roles.hasRole(role);
	}

	/**
	 * Whether this user has any of the given roles.
	 * 
	 * @param roles
	 *            set of roles
	 * @return whether this user has any of the given roles
	 */
	public boolean hasAnyRole(Roles roles) {
		return this.roles.hasAnyRole(roles);
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	public long getId() {
		return id;
	}

}
