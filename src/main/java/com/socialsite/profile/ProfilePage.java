package com.socialsite.profile;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.ajax.markup.html.tabs.AjaxTabbedPanel;
import org.apache.wicket.extensions.markup.html.tabs.AbstractTab;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.socialsite.BasePage;
import com.socialsite.user.UserInfoPanel;

/**
 * @author Ananth
 */
public class ProfilePage extends BasePage
{

	public ProfilePage()
	{
		// add the user info panel
		add(new UserInfoPanel("userinfo"));

		final List<ITab> tabs = new ArrayList<ITab>();

		tabs.add(new AbstractTab(new Model<String>("general"))
		{

			/**
			 * 
			 */
			private static final long	serialVersionUID	= 1L;

			@Override
			public Panel getPanel(final String panelId)
			{
				return new GeneralTabPanel(panelId);
			}
		});

		tabs.add(new AbstractTab(new Model<String>("general1"))
		{

			/**
			 * 
			 */
			private static final long	serialVersionUID	= 1L;

			@Override
			public Panel getPanel(final String panelId)
			{
				return new GeneralTabPanel(panelId);
			}
		});

		add(new AjaxTabbedPanel("tabs", tabs));
	}

}
