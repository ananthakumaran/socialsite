package com.socialsite.entitymodel;

import org.apache.wicket.model.LoadableDetachableModel;

import com.socialsite.persistence.AbstractDomain;

/**
 * Abastract class for the generic entity model
 * 
 * @author Ananth
 */
public abstract class AbstractEntityModel<T extends AbstractDomain> extends
		LoadableDetachableModel<T>
{

	private static final long	serialVersionUID	= 1L;
	/** id of the domain object */
	protected long				id;
	/** domain object */
	protected T					domain;

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
