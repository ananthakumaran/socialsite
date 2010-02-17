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

package com.socialsite.persistence;

import java.util.Date;

import com.socialsite.util.PrivacyModel;

/**
 * @author Ananth
 * 
 */
public class Profile implements AbstractDomain
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private User user;
	private String firstName;
	private String lastName;
	private String email;
	private byte[] image;
	private byte[] thumb;
	private String sex;
	private PrivacyModel currentCity;
	private PrivacyModel homeTown;
	private String relationshipStatus;
	private String politicalView;
	private String religiousView;
	private String activities;
	private String interests;
	private String favouriteMusic;
	private String favouriteTvShows;
	private String favouriteMovies;
	private String favouriteBooks;
	private String favouriteQuotations;
	private String aboutMe;
	private PrivacyModel mobilePhone;
	private PrivacyModel landPhone;
	private PrivacyModel address;
	private String city;
	private String neighborhood;
	private Integer zip;
	private String website;
	private String college;

	/**
	 * TODO add other fields link address , birthday etc;
	 */

	public Profile()
	{
	}

	public Profile(final User user)
	{
		setUser(user);
		getUser().setLastModified(new Date());
	}

	/**
	 * changes the profile image
	 * 
	 * @param image
	 *            image data in byte[]
	 */
	public void changeImage(final byte[] image)
	{
		this.image = image;
		getUser().setLastModified(new Date());
	}

	/**
	 * changes the thumb
	 * 
	 * @param thumb
	 *            thumb data in byte[]
	 */
	public void changeThumb(final byte[] thumb)
	{
		this.thumb = thumb;
		getUser().setLastModified(new Date());
	}

	public String getEmail()
	{
		return email;
	}

	public String getFirstName()
	{
		return firstName;
	}

	/***** accessor methods ****/
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.persistence.AbstractDomain#getId()
	 */
	public long getId()
	{
		return id;
	}

	public byte[] getImage()
	{
		return image;
	}

	public String getLastName()
	{
		return lastName;
	}

	public byte[] getThumb()
	{
		return thumb;
	}

	public User getUser()
	{
		return user;
	}

	public void setEmail(final String email)
	{
		this.email = email;
	}

	public void setFirstName(final String firstName)
	{
		this.firstName = firstName;
	}


	public void setId(final long id)
	{
		this.id = id;
	}

	public void setImage(final byte[] image)
	{
		this.image = image;
	}

	public void setLastName(final String lastName)
	{
		this.lastName = lastName;
	}

	public void setThumb(final byte[] thumb)
	{
		this.thumb = thumb;
	}

	public void setUser(final User user)
	{
		this.user = user;
	}

	public PrivacyModel getCurrentCity()
	{
		return currentCity;
	}

	public PrivacyModel getHomeTown()
	{
		return homeTown;
	}


	public String getPoliticalView()
	{
		return politicalView;
	}

	public String getRelationshipStatus()
	{
		return relationshipStatus;
	}

	public String getReligiousView()
	{
		return religiousView;
	}

	public String getSex()
	{
		return sex;
	}

	public void setCurrentCity(PrivacyModel currentCity)
	{
		this.currentCity = currentCity;
	}

	public void setHomeTown(PrivacyModel homeTown)
	{
		this.homeTown = homeTown;
	}


	public void setPoliticalView(String politicalView)
	{
		this.politicalView = politicalView;
	}

	public void setRelationshipStatus(String relationshipStatus)
	{
		this.relationshipStatus = relationshipStatus;
	}

	public void setReligiousView(String religiousView)
	{
		this.religiousView = religiousView;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public String getAboutMe()
	{
		return aboutMe;
	}

	public String getActivities()
	{
		return activities;
	}

	public String getFavouriteBooks()
	{
		return favouriteBooks;
	}

	public String getFavouriteMusic()
	{
		return favouriteMusic;
	}

	public String getFavouriteMovies()
	{
		return favouriteMovies;
	}

	public String getFavouriteQuotations()
	{
		return favouriteQuotations;
	}

	public String getFavouriteTvShows()
	{
		return favouriteTvShows;
	}

	public String getInterests()
	{
		return interests;
	}

	public void setAboutMe(String aboutMe)
	{
		this.aboutMe = aboutMe;
	}

	public void setActivities(String activities)
	{
		this.activities = activities;
	}

	public void setFavouriteBooks(String favouriteBooks)
	{
		this.favouriteBooks = favouriteBooks;
	}

	public void setFavouriteMovies(String favouriteMovies)
	{
		this.favouriteMovies = favouriteMovies;
	}

	public void setFavouriteMusic(String favouriteMusic)
	{
		this.favouriteMusic = favouriteMusic;
	}

	public void setFavouriteQuotations(String favouriteQuotations)
	{
		this.favouriteQuotations = favouriteQuotations;
	}

	public void setFavouriteTvShows(String favouriteTvShows)
	{
		this.favouriteTvShows = favouriteTvShows;
	}

	public void setInterests(String interests)
	{
		this.interests = interests;
	}

	public PrivacyModel getAddress()
	{
		return address;
	}

	public String getCity()
	{
		return city;
	}

	public PrivacyModel getLandPhone()
	{
		return landPhone;
	}

	public PrivacyModel getMobilePhone()
	{
		return mobilePhone;
	}

	public String getNeighborhood()
	{
		return neighborhood;
	}

	public String getWebsite()
	{
		return website;
	}

	public Integer getZip()
	{
		return zip;
	}

	public void setAddress(PrivacyModel address)
	{
		this.address = address;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public void setLandPhone(PrivacyModel landPhone)
	{
		this.landPhone = landPhone;
	}

	public void setMobilePhone(PrivacyModel mobilePhone)
	{
		this.mobilePhone = mobilePhone;
	}


	public void setNeighborhood(String neighborhood)
	{
		this.neighborhood = neighborhood;
	}

	public void setWebsite(String website)
	{
		this.website = website;
	}

	public void setZip(Integer zip)
	{
		this.zip = zip;
	}

	public String getCollege()
	{
		return college;
	}

	public void setCollege(String college)
	{
		this.college = college;
	}

}
