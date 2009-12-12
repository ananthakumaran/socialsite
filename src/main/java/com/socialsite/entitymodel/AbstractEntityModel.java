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

package com.socialsite.entitymodel;

import org.apache.wicket.model.LoadableDetachableModel;

import com.socialsite.persistence.AbstractDomain;

/**
 * Abastract class for the generic entity model
 * 
 * @author Ananth
 */
public abstract class AbstractEntityModel<T extends AbstractDomain>
		extends
			LoadableDetachableModel<T>
{

	private static final long serialVersionUID = 1L;
	/** id of the domain object */
	protected long id;
	/** domain object */
	protected T domain;

	/**
	 * creates a detachable Domain object
	 * 
	 * @param domain
	 *            detachableDomainobject
	 */
	public AbstractEntityModel(final T domain)
	{
		super();
		this.domain = domain;
		this.id = domain.getId();
	}

	/**
	 * Clear the reference to the Domain when the model is detatched.
	 */
	@Override
	protected void onDetach()
	{
		domain = null;
	}
}
