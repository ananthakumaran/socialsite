package com.socialsite;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;

/**
 * BasePage for the JQMS Application
 * 
 * @author Ananth
 * 
 */
@AuthorizeInstantiation( { "USER", "FRIEND", "OWNER" })
public class BasePage extends WebPage
{
	private static final long	serialVersionUID	= 1L;

	protected HeaderPanel		headerPanel;

	/**
	 * Constructor
	 */
	public BasePage()
	{
		this(null);
	}

	/**
	 * Constructor.
	 * 
	 * @param model
	 */
	public BasePage(IModel<?> model)
	{
		super(model);
		add(headerPanel = new HeaderPanel("header"));
	}
}
