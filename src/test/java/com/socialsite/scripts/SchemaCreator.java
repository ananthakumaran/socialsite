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
	 * and run the populate.sql to populate the database
	 * 
	 */
	public static void main(String[] args)
	{
		SchemaCreator.create();
	}
}
