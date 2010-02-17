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

package com.socialsite.profile;

import com.socialsite.BasePanel;
import com.socialsite.persistence.Profile;
import com.socialsite.util.NonEmptyPanel;

/**
 * @author Ananth
 */
public class PersonalInfoPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Profile profile;

	public PersonalInfoPanel(String id)
	{
		super(id);
		this.profile = getUser().getProfile();
		setOutputMarkupId(true);
		add(new NonEmptyPanel("activity", "Activity", profile.getActivities()));
		add(new NonEmptyPanel("interests", "Interests", profile.getInterests()));
		add(new NonEmptyPanel("favouriteMusic", "Favourite Music", profile.getFavouriteMovies()));
		add(new NonEmptyPanel("favouriteTvShows", "Favourite Tv Shows", profile
				.getFavouriteTvShows()));
		add(new NonEmptyPanel("favouriteMovies", "Favourite Movies ", profile.getFavouriteMovies()));
		add(new NonEmptyPanel("favouriteBooks", "Favourite Books", profile.getFavouriteBooks()));
		add(new NonEmptyPanel("favouriteQuotations", "Favourite Quotations", profile
				.getFavouriteQuotations()));
		add(new NonEmptyPanel("aboutMe", "About Me", profile.getAboutMe()));
	}

}
