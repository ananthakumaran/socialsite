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

public class PrivacyUserType implements CompositeUserType
{


	public PrivacyUserType()
	{

	}

	public Object assemble(Serializable cached, SessionImplementor session, Object owner)
			throws HibernateException
	{
		return deepCopy(cached);
	}

	public Object deepCopy(Object x) throws HibernateException
	{
		if (x == null)
			return null;
		PrivacyModel input = (PrivacyModel)x;
		return new PrivacyModel(input.getValue(), input.getPrivacy());
	}

	public Serializable disassemble(Object value, SessionImplementor session)
			throws HibernateException
	{
		if (value == null)
			System.out.println("OOPPSSS");
		return (Serializable)deepCopy(value);
	}

	public boolean equals(Object x, Object y) throws HibernateException
	{
		if (x == y)
			return true;
		if (x == null || y == null)
			return false;
		return ((PrivacyModel)x).equals((PrivacyModel)y);
	}

	public String[] getPropertyNames()
	{
		return new String[] { "value", "privacy" };
	}

	public Type[] getPropertyTypes()
	{
		return new Type[] { Hibernate.STRING, Hibernate.STRING };
	}

	public Object getPropertyValue(Object component, int property) throws HibernateException
	{
		PrivacyModel input = (PrivacyModel)component;
		if (property == 0)
		{
			return input.getValue();
		}
		else
		{
			return input.getPrivacy();
		}
	}

	public int hashCode(Object x) throws HibernateException
	{
		return x.hashCode();
	}

	public boolean isMutable()
	{
		return true;
	}

	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
			throws HibernateException, SQLException
	{
		String value = (String)Hibernate.STRING.nullSafeGet(rs, names[0]);
		String privacyStr = (String)Hibernate.STRING.nullSafeGet(rs, names[1]);
		Access privacy = null;
		if (privacyStr != null)
		{
			privacy = Enum.valueOf(Access.class, privacyStr);
		}
		return (value == null && privacy == null) ? new PrivacyModel() : new PrivacyModel(value,
				privacy);
	}

	public void nullSafeSet(PreparedStatement st, Object value, int index,
			SessionImplementor session) throws HibernateException, SQLException
	{
		PrivacyModel privacyField = (value == null) ? new PrivacyModel() : (PrivacyModel)value;
		Hibernate.STRING.nullSafeSet(st, privacyField.getValue(), index);
		String privacy = (privacyField.getPrivacy() == null) ? null : privacyField.getPrivacy()
				.toString();
		Hibernate.STRING.nullSafeSet(st, privacy, index + 1);
	}

	public Object replace(Object original, Object target, SessionImplementor session, Object owner)
			throws HibernateException
	{
		return deepCopy(original);
	}

	public Class<PrivacyModel> returnedClass()
	{
		return PrivacyModel.class;
	}

	public void setPropertyValue(Object component, int property, Object value)
			throws HibernateException
	{
		PrivacyModel input = (PrivacyModel)component;
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
