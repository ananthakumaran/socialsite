package com.socialsite.authentication;

import org.apache.wicket.authorization.strategies.role.Roles;

/**
 * holds the list of roles
 * 
 * @author Ananth
 * 
 */
public class SocialSiteRoles
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	public static final String	USER				= "USER";
	public static final String	FRIEND				= "FRIEND";
	public static final String	OWNER				= "OWNER";

	public static final Roles	userRole			= new Roles(USER);
	public static final Roles	friendRole			= new Roles(FRIEND);
	public static final Roles	ownerRole			= new Roles(OWNER);

}
