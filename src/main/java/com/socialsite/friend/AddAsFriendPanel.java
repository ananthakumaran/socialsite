package com.socialsite.friend;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.BasePanel;
import com.socialsite.SocialSiteSession;
import com.socialsite.dao.FriendRequestDao;
import com.socialsite.dao.UserDao;
import com.socialsite.persistence.FriendRequest;
import com.socialsite.persistence.User;

/**
 * @author Ananth
 */
@AuthorizeAction(action = Action.RENDER, roles = "USER")
public class AddAsFriendPanel extends BasePanel
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	/** Model for message */
	private String				message;

	/** Spring Dao to access the user object */
	@SpringBean(name = "userDao")
	private UserDao				userDao;
	/** Spring Dao to access the friendrequest object */
	@SpringBean(name = "friendRequestDao")
	private FriendRequestDao	friendRequestDao;
	/** feedback panel */
	private FeedbackPanel		feedback;

	/**
	 * constructor
	 */
	public AddAsFriendPanel(String id)
	{
		super(id);
		Form<Object> addAsFriendForm = new Form<Object>("addasfriendform");
		add(addAsFriendForm);
		addAsFriendForm.add(new RequiredTextField<String>("message",
			new PropertyModel<String>(this, "message")));
		AjaxSubmitLink send;
		addAsFriendForm.add(send = new AjaxSubmitLink("send")
		{

			/**
			 * 
			 */
			private static final long	serialVersionUID	= 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form)
			{
				// load the users
				User user = userDao.load(SocialSiteSession.get()
					.getSessionUser().getId());
				User friend = userDao.load(SocialSiteSession.get().getUserId());
				// create a new friend request
				FriendRequest friendRequest = new FriendRequest();
				friendRequest.setFriend(friend);
				friendRequest.setUser(user);
				friendRequest.setMessage(message);
				friendRequestDao.save(friendRequest);
				// show feedback
				info("friend request sent");
				// TODO add javascript to hide the form after sending the friend
				// request
				target.addComponent(feedback);
				
				// remove the form
				target.appendJavascript(" SocialSite.Home.AddAsFriend.removeAll();");
			}
		});
		addAsFriendForm.setDefaultButton(send);
		add(feedback = new FeedbackPanel("feedback"));
		feedback.setOutputMarkupId(true);
	}

	@Override
	public boolean isVisible()
	{
		return !friendRequestDao.hasFriendRequest(SocialSiteSession.get()
			.getSessionUser().getId(), SocialSiteSession.get().getUserId());
	}
}
