package com.socialsite.scrap;

import java.util.Date;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.BasePanel;
import com.socialsite.dao.ScrapDao;
import com.socialsite.persistence.Scrap;

/**
 * @author Ananth
 */
@AuthorizeAction(action = Action.RENDER, roles = "OWNER")
public class ReplyPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/** spring dao to handle scrap objects */
	@SpringBean(name = "scrapDao")
	private ScrapDao			scrapDao;

	/** model for message */
	private String				message;

	public ReplyPanel(final String id, final IModel<Scrap> scrap)
	{
		super(id, scrap);
		// container show the link to the replied scrap
		final WebMarkupContainer sentScrapContainer = new WebMarkupContainer(
			"sent");

		sentScrapContainer.add(new Link<Scrap>("sentscrap", scrap)
		{

			/**
			 * 
			 */
			private static final long	serialVersionUID	= 1L;

			@Override
			public void onClick()
			{
				setUserId(getModelObject().getAuthor().getId());
				setResponsePage(ScrapPage.class);
			}

		});

		add(sentScrapContainer);
		// hide initially
		sentScrapContainer.setVisible(false);
		sentScrapContainer.setOutputMarkupPlaceholderTag(true);

		final Form<Scrap> replyForm = new Form<Scrap>("replyform", scrap);
		replyForm.add(new TextArea<String>("message",
			new PropertyModel<String>(this, "message")).setRequired(true));

		AjaxSubmitLink send;
		replyForm.add(send = new AjaxSubmitLink("send")
		{

			/**
			 * 
			 */
			private static final long	serialVersionUID	= 1L;

			@Override
			protected void onSubmit(final AjaxRequestTarget target,
					final Form<?> form)
			{

				final Scrap scrap = (Scrap) form.getModelObject();

				// create a reply scrap
				final Scrap replyScrap = new Scrap();
				replyScrap.setMessage(message);
				replyScrap.setAuthor(scrap.getReceiver());
				replyScrap.setReceiver(scrap.getAuthor());
				replyScrap.setTime(new Date());
				// save the reply scrap
				scrapDao.save(replyScrap);

				// show a link to the replied scrap
				sentScrapContainer.setVisible(true);
				target.addComponent(sentScrapContainer);

				// hide the reply form
				target.appendJavascript("$('#" + replyForm.getMarkupId()
						+ "').parent().hide()");
			}

		});

		replyForm.setDefaultButton(send);

		add(replyForm);

	}
}
