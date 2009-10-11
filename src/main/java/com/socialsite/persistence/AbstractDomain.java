package com.socialsite.persistence;

import java.io.Serializable;

/**
 * abstract domain class
 * 
 * @author Ananth
 */
public interface AbstractDomain extends Serializable
{

	/**
	 * generalized method to get the id of the Domain object
	 * 
	 * @return id of the Domain object
	 */
	public long getId();
}
