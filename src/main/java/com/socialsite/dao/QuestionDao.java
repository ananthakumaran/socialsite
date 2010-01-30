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

package com.socialsite.dao;

import java.util.List;

import com.socialsite.persistence.Question;

/**
 * 
 * @author Ananth
 * 
 */
public interface QuestionDao extends AbstractDao<Question>
{
	/**
	 * gets the list of questions
	 * 
	 * @param courseId
	 *            course id
	 * @param first
	 *            first result index
	 * @param count
	 *            count of results
	 * @return list of questions
	 */
	public List<Question> getQuestions(long courseId, int first, int count);

	/**
	 * gets the count of questions
	 * 
	 * @param courseId
	 *            course id
	 * @return count of questions
	 */
	public int getQuestionsCount(long courseId);
}
