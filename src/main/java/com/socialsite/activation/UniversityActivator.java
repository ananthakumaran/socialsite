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

package com.socialsite.activation;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.ActivationDao;
import com.socialsite.email.Email;
import com.socialsite.email.EmailSender;
import com.socialsite.persistence.Activation;
import com.socialsite.persistence.Admin;

/**
 * 
 * @author Ananth
 * 
 */
public class UniversityActivator
{
	private Admin admin;
	private String universityName;
	private long activationId;

	@SpringBean(name = "activationDao")
	ActivationDao activationDao;

	public UniversityActivator(Admin admin, String universityName)
	{
		this.admin = admin;
		this.universityName = universityName;
		InjectorHolder.getInjector().inject(this);
	}

	public void create()
	{
		this.activationId = addActivation();
		// send email to the admin
		sendEmail();
	}

	/**
	 * sends email to the admin
	 */
	public void sendEmail()
	{
		Email email = new Email();
		// am i sending message to myself :)
		email.addReceivers("ananthakumaran@gmail.com");
		email.setSubject("University Activation Request");
		email.setMessage(getMessage());
		new EmailSender().send(email);
	}

	public String getMessage()
	{
		StringBuffer message = new StringBuffer();
		message.append("University Activation Request \n");
		message.append("UniversityName : ").append(universityName).append("\n");
		message.append("Admin Name :").append(admin.getUserName()).append("\n");
		// message.append("Admin email :").append(admin.getProfile().getEmail()).append("\n");
		message.append("To activate the university click the following url ").append(
				getActivationUrl()).append("\n");
		message.append("To Deactivate the university click the following url ").append(
				getDeActivationUrl()).append("\n");
		return message.toString();
	}

	public long addActivation()
	{
		Activation activation = new Activation();
		activation.setAdmin(admin);
		activation.setUniversityName(universityName);
		activationDao.save(activation);
		return activation.getId();
	}

	public String getActivationUrl()
	{
		return "http://localhost:8081/activate?id=" + activationId + "&action=activate";
	}

	public String getDeActivationUrl()
	{
		return "http://localhost:8081/activate?id=" + activationId + "&action=deactivate";
	}
}
