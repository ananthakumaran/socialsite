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

package com.socialsite.dataprovider;

import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.AnswerDao;
import com.socialsite.entitymodel.EntityModel;
import com.socialsite.persistence.Answer;

/**
 * 
 * @author Ananth
 * 
 */
public class AnswerDataProvider extends SortableDataProvider<Answer>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SpringBean(name = "answerDao")
	private AnswerDao answerDao;

	private final long questionId;

	public AnswerDataProvider(final long questionId)
	{
		InjectorHolder.getInjector().inject(this);
		// reloads the university from DB.Avoids LIE
		this.questionId = questionId;
	}

	public Iterator<? extends Answer> iterator(final int first, final int count)
	{
		return answerDao.getAnswers(questionId, first, count).iterator();
	}

	public IModel<Answer> model(final Answer model)
	{
		return new EntityModel<Answer>(model, answerDao);
	}

	public int size()
	{
		return answerDao.getAnswersCount(questionId);
	}

}
