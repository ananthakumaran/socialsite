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

package com.socialsite.util;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;

import com.socialsite.profile.Access;

/**
 * 
 * A composite UserType that contains the value and the Access type of the value
 * 
 * @author Ananth
 * 
 */
public class PrivacyUserType implements CompositeUserType
{


	public PrivacyUserType()
	{

	}

	public Object assemble(final Serializable cached, final SessionImplementor session,
			final Object owner) throws HibernateException
	{
		return deepCopy(cached);
	}

	public Object deepCopy(final Object x) throws HibernateException
	{
		if (x == null)
		{
			return null;
		}
		final PrivacyModel input = (PrivacyModel)x;
		return new PrivacyModel(input.getValue(), input.getPrivacy());
	}

	public Serializable disassemble(final Object value, final SessionImplementor session)
			throws HibernateException
	{
		return (Serializable)deepCopy(value);
	}

	public boolean equals(final Object x, final Object y) throws HibernateException
	{
		if (x == y)
		{
			return true;
		}
		if (x == null || y == null)
		{
			return false;
		}
		return ((PrivacyModel)x).equals(y);
	}

	public String[] getPropertyNames()
	{
		return new String[] { "value", "privacy" };
	}

	public Type[] getPropertyTypes()
	{
		return new Type[] { Hibernate.STRING, Hibernate.STRING };
	}

	public Object getPropertyValue(final Object component, final int property)
			throws HibernateException
	{
		final PrivacyModel input = (PrivacyModel)component;
		if (property == 0)
		{
			return input.getValue();
		}
		else
		{
			return input.getPrivacy();
		}
	}

	public int hashCode(final Object x) throws HibernateException
	{
		return x.hashCode();
	}

	public boolean isMutable()
	{
		return true;
	}

	public Object nullSafeGet(final ResultSet rs, final String[] names,
			final SessionImplementor session, final Object owner) throws HibernateException,
			SQLException
	{
		final String value = (String)Hibernate.STRING.nullSafeGet(rs, names[0]);
		final String privacyStr = (String)Hibernate.STRING.nullSafeGet(rs, names[1]);
		Access privacy = null;
		if (privacyStr != null)
		{
			privacy = Enum.valueOf(Access.class, privacyStr);
		}
		return value == null && privacy == null ? new PrivacyModel() : new PrivacyModel(value,
				privacy);
	}

	public void nullSafeSet(final PreparedStatement st, final Object value, final int index,
			final SessionImplementor session) throws HibernateException, SQLException
	{
		final PrivacyModel privacyField = value == null ? new PrivacyModel() : (PrivacyModel)value;
		Hibernate.STRING.nullSafeSet(st, privacyField.getValue(), index);
		final String privacy = privacyField.getPrivacy() == null ? null : privacyField.getPrivacy()
				.toString();
		Hibernate.STRING.nullSafeSet(st, privacy, index + 1);
	}

	public Object replace(final Object original, final Object target,
			final SessionImplementor session, final Object owner) throws HibernateException
	{
		return deepCopy(original);
	}

	public Class<PrivacyModel> returnedClass()
	{
		return PrivacyModel.class;
	}

	public void setPropertyValue(final Object component, final int property, final Object value)
			throws HibernateException
	{
		final PrivacyModel input = (PrivacyModel)component;
		if (property == 0)
		{
			input.setValue((String)value);
		}
		else
		{
			input.setPrivacy((Access)value);
		}
	}
}
