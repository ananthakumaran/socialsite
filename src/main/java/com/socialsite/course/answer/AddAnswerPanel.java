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

package com.socialsite.course.answer;

import java.util.Date;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.BasePanel;
import com.socialsite.dao.AnswerDao;
import com.socialsite.persistence.Answer;
import com.socialsite.persistence.Question;
import com.socialsite.util.wmd.RichEditor;

/**
 * @author Ananth
 */
public class AddAnswerPanel extends BasePanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** feedback panel */
	FeedbackPanel feedback;
	/** model for answer text */
	private String text;

	@SpringBean(name = "answerDao")
	private AnswerDao answerDao;

	public AddAnswerPanel(final String id, final IModel<Question> model,
			final MarkupContainer dependent)
	{
		super(id, model);
		final Form<Void> form = new Form<Void>("form");
		add(form);
		form.add(new RichEditor("richeditor", new PropertyModel<String>(this, "text")));
		final AjaxSubmitLink addAnswer = new AjaxSubmitLink("addanswer")
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onError(final AjaxRequestTarget target, final Form<?> form)
			{
				super.onError(target, form);
				// show feedback messages
				target.addComponent(feedback);
			}

			@Override
			protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				// save the answers
				final Question question = model.getObject();
				final Answer answer = new Answer();
				answer.setUser(getSessionUser());
				answer.setText(text);
				answer.setTime(new Date());
				question.addAnswer(answer);

				answerDao.save(answer);
				target.addComponent(feedback);
				target.addComponent(dependent);
				// fire the update event so the editor can intialize
				firePostAjaxUpdateEvent(target);
				// slideup the reply panel
				String id = AddAnswerPanel.this.getMarkupId();
				target.appendJavascript(" $('#" + id + " .slideText').trigger('click'); ");
			}
		};
		form.add(addAnswer);
		form.setDefaultButton(addAnswer);
		add(feedback = new FeedbackPanel("feedback"));
		feedback.setOutputMarkupId(true);
		setOutputMarkupId(true);
	}
}
