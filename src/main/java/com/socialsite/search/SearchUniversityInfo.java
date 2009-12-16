package com.socialsite.search;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.socialsite.persistence.University;

/**
 * @author Ananth
 */
public class SearchUniversityInfo extends Panel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param id
	 *            component id
	 * @param model
	 *            university model
	 */
	public SearchUniversityInfo(final String id, final IModel<University> model)
	{
		super(id);
		final University university = model.getObject();
		add(new Label("name", university.getName()));
	}

}
