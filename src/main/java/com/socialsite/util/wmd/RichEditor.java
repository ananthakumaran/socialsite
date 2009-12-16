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
