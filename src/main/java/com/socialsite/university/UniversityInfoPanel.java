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

package com.socialsite.university;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.UniversityDao;
import com.socialsite.image.ImagePanel;
import com.socialsite.image.ImageService;
import com.socialsite.image.ImageType;
import com.socialsite.persistence.University;
import com.socialsite.persistence.User;
import com.socialsite.user.UserLink;

/**
 * @author Ananth
 */
public class UniversityInfoPanel extends Panel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	@SpringBean(name = "universityDao")
	private UniversityDao universityDao;

	/**
	 * constructor
	 * 
	 * @param id
	 *            component id
	 */
	public UniversityInfoPanel(final String id, final University university)
	{
		super(id);
		// image of the university
		add(new ImagePanel("image", university.getId(), ImageType.UNIVERSITY)
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void saveImage(final byte[] imageData)
			{
				final ImageService imageService = new ImageService();
				university.setImage(imageService.resize(imageData, ImageService.IMAGE_SIZE));
				university.setThumb(imageService.resize(imageData, ImageService.THUMB_SIZE));
				universityDao.save(university);
			}

		});

		// admin of the university
		add(new UserLink<User>("admin", new Model<User>(university.getAdmin())));

	}
}
