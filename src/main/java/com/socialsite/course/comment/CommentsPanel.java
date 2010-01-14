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

package com.socialsite.course.comment;

import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;

import com.socialsite.BasePanel;
import com.socialsite.dataprovider.CommentDataProvider;
import com.socialsite.persistence.Answer;
import com.socialsite.persistence.Comment;

/**
 * @author Ananth
 */
public class CommentsPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommentsPanel(final String id, final IModel<Answer> model)
	{
		super(id, model);
		// allow other panels to update this panel using ajax
		setOutputMarkupId(true);
		final Answer answer = model.getObject();
		final DataView<Comment> commentView = new DataView<Comment>("comments",
				new CommentDataProvider(answer.getId()))
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final Item<Comment> item)
			{
				item.add(new CommentPanel("comment", item.getModel()));
			}
		};
		add(commentView);
	}

}
