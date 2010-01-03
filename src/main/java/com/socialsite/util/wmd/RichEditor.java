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

package com.socialsite.util.wmd;

import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;

/**
 * WMD rich Editor
 * 
 * TODO convert the button images into a single image (CSS Sprite)
 * 
 * @author Ananth
 */
public class RichEditor extends FormComponentPanel<String> implements IHeaderContributor
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** text area */
	private TextArea<String> textArea;

	/**
	 * constructor
	 * 
	 * @param id
	 *            id
	 * @param model
	 *            model for the textarea
	 */
	public RichEditor(final String id, final IModel<String> model)
	{
		super(id, model);
		add(textArea = new TextArea<String>("textarea", model));
		textArea.setRequired(true);
	}

	@Override
	protected void convertInput()
	{
		// TODO filter any javascript in the markup
		setConvertedInput(textArea.getConvertedInput());
	}

	public void renderHead(final IHeaderResponse response)
	{
		// initialize the editor
		response.renderJavascriptReference("js/socialsite/editor.js");
		// wmd
		response.renderJavascriptReference("js/wmd/wmd.js");
		// TextArea Resizer
		response.renderJavascriptReference("js/jquery/plugin/textarearesizer.js");
	}
}
