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

package com.socialsite.scripts;

import org.apache.wicket.util.file.File;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * 
 * utility class to create the database schema using hibernate
 * 
 * NOTE - This class is not needed for the Application - used only for
 * development
 * 
 * @author Ananth
 */
public class SchemaCreator
{

	public static void create()
	{
		final Configuration cfg = new Configuration().configure();
		final SchemaExport schemaExport = new SchemaExport(cfg);
		schemaExport.setDelimiter(";");
		schemaExport.setFormat(true);
		final File f = new File("src/main/scripts");
		f.mkdirs();
		schemaExport.setOutputFile("src/main/scripts/schema.sql");
		schemaExport.drop(true, true);
		schemaExport.create(true, true);
	}

	/**
	 * Creates the schema for the database run this file and the schema will
	 * written to schema.sql run the schema.sql to clean and build the database
	 * and run the {@link com.socialsite.scripts.LoadData} to populate the
	 * database
	 * 
	 */
	public static void main(final String[] args)
	{
		SchemaCreator.create();
	}
}
