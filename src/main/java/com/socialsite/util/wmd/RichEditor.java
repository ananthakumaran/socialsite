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

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.file.File;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;

/**
 * WMD rich Editor. This uses the nathanborror branch of the wmd editor <a
 * href="http://github.com/nathanborror/wmd">github</a>
 * 
 * @author Ananth
 */
public class RichEditor extends FormComponentPanel<String> implements IHeaderContributor
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** antisamy config file */
	private static File file = new File("./src/main/resources/antisamy.xml");

	/**
	 * sanitizes the html
	 * 
	 * @param html
	 *            dirty html
	 * @return clean html or error message if it contains errors
	 */
	private static String getCleanHtml(final String html)
	{
		final AntiSamy sanitizer = new AntiSamy();
		CleanResults results = null;
		try
		{
			results = sanitizer.scan(html, Policy.getInstance(file));
		}
		catch (final ScanException e)
		{
			Logger.getLogger(RichEditor.class.getName()).log(Level.SEVERE, "", e);
		}
		catch (final PolicyException e)
		{
			Logger.getLogger(RichEditor.class.getName()).log(Level.SEVERE, "", e);
		}
		if (results != null)
		{
			return results.getCleanHTML();
		}
		else
		{
			return "<p>input contains errors<p>";
		}
	}

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
		setConvertedInput(getCleanHtml(textArea.getConvertedInput()));
	}

	public void renderHead(final IHeaderResponse response)
	{
		// wmd
		response.renderJavascriptReference("js/jquery/plugin/wmd.js");
		// initialize the editor
		response.renderJavascriptReference("js/socialsite/editor.js");
		// TextArea Resizer
		response.renderJavascriptReference("js/jquery/plugin/textarearesizer.js");
	}
}
