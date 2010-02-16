package com.socialsite.util;

import java.util.Arrays;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import com.socialsite.profile.Access;

/**
 * @author Ananth
 */
public class PrivacyField extends FormComponentPanel<PrivacyModel>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextField<String> textField;
	private DropDownChoice<Access> privacy;
	PrivacyModel model;

	public PrivacyField(String id, IModel<PrivacyModel> model)
	{
		super(id, model);
		this.model = getModelObject();
		add(textField = new TextField<String>("value", new PropertyModel<String>(model, "value")));
		add(privacy = new DropDownChoice<Access>("privacy", new PropertyModel<Access>(model,
				"privacy"), Arrays.asList(Access.EVERYONE, Access.FRIENDS_ONLY)));
	}

	@Override
	protected void convertInput()
	{
		model.setValue(textField.getConvertedInput());
		model.setPrivacy(privacy.getConvertedInput());
		setConvertedInput(model);
	}

}
