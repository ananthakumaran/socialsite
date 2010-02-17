package com.socialsite.util;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

import com.socialsite.BasePanel;
import com.socialsite.authentication.SocialSiteRoles;
import com.socialsite.profile.Access;

/**
 * @author Ananth
 */
public class PrivacyPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PrivacyModel privacyModel;

	/**
	 * shows the panel if the current is allowed to see the data
	 * 
	 * @param id
	 * @param model
	 * @param label
	 */
	public PrivacyPanel(String id, IModel<PrivacyModel> model, String label)
	{
		super(id, model);
		privacyModel = model.getObject();
		add(new Label("field", label));
		add(new Label("value",privacyModel.getValue()));

	}

	@Override
	public boolean isVisible()
	{
		if (privacyModel == null)
		{
			return false;
		}
		Access access = privacyModel.getPrivacy();
		if (access == Access.EVERYONE || hasRole(SocialSiteRoles.OWNER)
				|| (access == Access.FRIENDS_ONLY && hasRole(SocialSiteRoles.FRIEND)))
		{
			return true;
		}
		return false;
	}
}
