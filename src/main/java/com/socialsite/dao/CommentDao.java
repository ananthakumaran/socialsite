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

import com.socialsite.persistence.Comment;

/**
 * 
 * @author Ananth
 * 
 */
public interface CommentDao extends AbstractDao<Comment>
{
	/**
	 * gets the list of comments
	 * 
	 * @param answerId
	 *            answer id
	 * @param first
	 *            start index
	 * @param count
	 *            count
	 * @return list of comment associated with a answer
	 */
	public List<Comment> getComments(long answerId, int first, int count);

	/**
	 * gets the count to comment
	 * 
	 * @param answerId
	 *            answer id
	 * @return count of comments associated with a answer
	 */
	public int getCommentsCount(long answerId);
}
