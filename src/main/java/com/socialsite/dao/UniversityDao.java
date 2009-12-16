package com.socialsite.dao;

import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;

import com.socialsite.persistence.University;

/**
 * 
 * @author Ananth
 * 
 */
public interface UniversityDao extends AbstractDao<University>
{
	/**
	 * find the number of unique rows in the University (table) that matched the
	 * search text (filter)
	 * 
	 * @param filter
	 *            search text
	 * @return no of unique rows in the University (table) that matched the
	 *         search text (filter)
	 */
	public int countAll(String filter);

	/**
	 * find the list of all university according to the search text(filter) and
	 * orders the list according to the sortParam
	 * 
	 * @param filter
	 *            filter for university name
	 *            <p>
	 *            if filter is null all the users will be added to the list
	 * @param first
	 *            first item no of the user List
	 * @param count
	 *            no of items needed in the user List
	 * @param sortParam
	 *            used to sort the user List
	 * @return List containing the {@link University} matched the search
	 *         criteria
	 */
	public List<University> findAll(String filter, int first, int count, SortParam sortParam);
}
