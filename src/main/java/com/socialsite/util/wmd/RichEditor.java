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

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;

/**
 * WMD rich Editor
 * 
 * TODO convert the button image into a single image (CSS Sprite)
 * 
 * @author Ananth
 */
public class RichEditor<T> extends FormComponentPanel<T> implements IHeaderContributor
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** text area */
	private TextArea<T> textArea;

	/**
	 * constructor
	 * 
	 * @param id
	 *            id
	 * @param model
	 *            model for the textarea
	 */
	public RichEditor(final String id, final IModel<T> model)
	{
		super(id, model);
		add(textArea = new TextArea<T>("textarea", model));
	}

	@Override
	protected void convertInput()
	{
		// TODO filter any javascript in the markup
		setConvertedInput(textArea.getConvertedInput());
	}

	public void renderHead(final IHeaderResponse response)
	{
		// jquery
		response.renderJavascriptReference("js/jquery/jquery.min.js");
		// TextArea Resizer
		final ResourceReference resizeRef = new ResourceReference(getClass(),
				"jquery.textarearesizer.js");
		response.renderJavascriptReference(resizeRef);
		response.renderOnDomReadyJavascript("$('textarea').TextAreaResizer();");
	}
}
