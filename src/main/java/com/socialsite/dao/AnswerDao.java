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

import com.socialsite.persistence.Answer;

/**
 * 
 * @author Ananth
 * 
 */
public interface AnswerDao extends AbstractDao<Answer>
{
	/**
	 * gets the list of answer in the given question
	 * 
	 * @param questionId
	 *            question id
	 * @param first
	 *            first result index
	 * @param count
	 *            count of results
	 * @return list of answers
	 */
	public List<Answer> getAnswers(long questionId, int first, int count);

	/**
	 * gets the count of answer in the given question
	 * 
	 * @param questionId
	 *            question id
	 * @return count of answers
	 */
	public int getAnswersCount(long questionId);
}
