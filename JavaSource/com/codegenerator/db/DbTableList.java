package com.codegenerator.db;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.codegenerator.common.ApplicationProperties;
import com.codegenerator.common.ThreadContext;

public class DbTableList extends TableList {

	/**
	 * 
	 */
	public DbTableList() throws SQLException {
		super();
		initFromDb();
	}
	private void initFromDb() throws SQLException {
		  
		   initTableList();
		   
	       DatabaseMetaData dbmd = ThreadContext.getCurrentContext().getDbconn().getConn().getMetaData();
	       String table_type = "TABLE";
           String[] tableTypes = {table_type};
           String schema = ApplicationProperties.getDbSchema();
           ResultSet tables = dbmd.getTables(null,schema,"%",tableTypes);
           int numTables  = 0;
          
           while ( tables.next()) {
              String table  = tables.getString("TABLE_NAME");
              String type   = tables.getString("TABLE_TYPE");
              if (type.equals(table_type)) {
              	 SqlTable aTable = new SqlTable(table,schema);
              	 getTableList().put(table,aTable);
              	 ++numTables;
              }
             
           }
           tables.close();
	}

}
