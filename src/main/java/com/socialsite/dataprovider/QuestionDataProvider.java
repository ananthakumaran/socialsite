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

import com.socialsite.dao.QuestionDao;
import com.socialsite.entitymodel.EntityModel;
import com.socialsite.persistence.Question;

/**
 * TODO: write separate routines to get the question list and size in the
 * question dao
 * 
 * @author Ananth
 * 
 */
public class QuestionDataProvider extends SortableDataProvider<Question>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SpringBean(name = "questionDao")
	private QuestionDao questionDao;

	private final long courseId;

	public QuestionDataProvider(final long courseId)
	{
		InjectorHolder.getInjector().inject(this);
		// reloads the university from DB.Avoids LIE
		this.courseId = courseId;
	}

	public Iterator<? extends Question> iterator(final int first, final int count)
	{
		return questionDao.getQuestions(courseId, first, count).iterator();
	}

	public IModel<Question> model(final Question model)
	{
		return new EntityModel<Question>(model, questionDao);
	}

	public int size()
	{
		return questionDao.getQuestionsCount(courseId);
	}

}
