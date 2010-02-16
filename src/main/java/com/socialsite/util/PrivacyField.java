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
