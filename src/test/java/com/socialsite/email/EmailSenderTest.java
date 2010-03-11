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

package com.socialsite.email;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Don't run this test every time. This will send a email
 * 
 * @author Ananth
 * 
 */
public class EmailSenderTest
{
	@Test
	@Ignore
	public void testSend()
	{
		final Email email = new Email();
		// am i sending message to myself :)
		email.addReceivers("ananthakumaran@gmail.com");
		email.setSubject("Unit Testing");
		email.setMessage("Test Message");
		new EmailSender().send(email);
	}
}
