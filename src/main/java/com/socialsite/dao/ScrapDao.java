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

import com.socialsite.persistence.Scrap;
import com.socialsite.persistence.User;

/**
 * DAO for the Scrap object
 * 
 * @author Ananth
 * 
 */
public interface ScrapDao extends AbstractDao<Scrap>
{
	/**
	 * find the scraps
	 * 
	 * @param user
	 *            user object
	 * @param first
	 *            first item no of the user List
	 * @param count
	 *            no of items needed in the user List
	 * @return List containig all the scraps
	 */
	public List<Scrap> getScraps(User user, int first, int count);

	/**
	 * get the count of scraps
	 * 
	 * @param user
	 *            user object
	 * @return the count of scraps
	 */
	public int getScrapsCount(User user);

}
