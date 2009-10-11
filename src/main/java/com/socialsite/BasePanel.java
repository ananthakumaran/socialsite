package com.socialsite;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

/**
 * BasePanel for the socialsite
 * 
 * @author Ananth
 */
public class BasePanel extends Panel {

	private static final long serialVersionUID = 1L;

	/**
	 * constructor
	 * 
	 * @param id
	 *            id
	 */
	public BasePanel(String id) {
		super(id);
	}

	/**
	 * constructor
	 * 
	 * @param id
	 *            id
	 * @param model
	 *            model
	 */
	public BasePanel(String id, IModel<?> model) {
		super(id, model);
	}

}
