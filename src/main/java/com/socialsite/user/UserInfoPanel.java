package com.socialsite.user;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.value.ValueMap;

import com.socialsite.SocialSiteSession;
import com.socialsite.dao.UserDao;
import com.socialsite.friend.AddAsFriendPanel;
import com.socialsite.persistence.User;
import com.socialsite.scrap.ScrapPage;

/**
 * @author Ananth
 */
public class UserInfoPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** spring dao to handle user object */
	@SpringBean(name = "userDao")
	private UserDao userDao;

	public UserInfoPanel(String id) {
		super(id);

		User user = userDao.load(SocialSiteSession.get().getUserId());
		ResourceReference imageResource = new ResourceReference(
				"userImageResource");
		add(new Image("userimage", imageResource, new ValueMap("id="
				+ user.getId())));
		add(new Label("username", user.getUserName()));
		add(new Link<Object>("scrap") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(ScrapPage.class);
			}

		});
		add(new AddAsFriendPanel("addasfriend"));
	}
}
