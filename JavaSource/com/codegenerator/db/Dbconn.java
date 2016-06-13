package com.codegenerator.db;

import java.sql.*;


import com.codegenerator.common.ApplicationObject;
import com.codegenerator.common.ApplicationProperties;


public class Dbconn extends ApplicationObject {

        private Connection conn = null;     
        private String dbuserid;
        private String dbpasswd;
        private String jdbcDriver;
        private String dbUrl;

        private String errormsg;
        
        
   public Dbconn () { 
   		  
   		  jdbcDriver = ApplicationProperties.getJdbcDriver();
   		  dbUrl     =  ApplicationProperties.getDbUrl();
          dbuserid  =  ApplicationProperties.getDbUserid();
          dbpasswd  =  ApplicationProperties.getDbPasswd();
          errormsg  = "";
          conn      = null;
   }
  
   public void closeconn(boolean commit) {
         try
         {
              if (commit) commit();
              else rollback();
              
              if (conn != null) conn.close();
              conn = null;
      

         } catch ( Exception e ) {
           errormsg = "Close connect Sqlerror to "+dbUrl+" " +e.toString();
           System.out.println(errormsg);
           e.printStackTrace();
         }
   }
   public void commit() {
         try
         {
           if (conn != null) conn.commit();
         } catch ( Exception e ) {
           errormsg = "Commit Sqlerror to "+dbUrl+" " +e.toString();
           System.out.println(errormsg);
           e.printStackTrace();
         }
   }
   public void connect () 
        {
          
         try {

           if (conn != null) closeconn(true);

               
           // Load the driver classes
           // convert ^ in dbname to ";"
		   Class.forName (jdbcDriver);
         
           if (!dbuserid.equals("") & !dbpasswd.equals(""))
                conn = DriverManager.getConnection(dbUrl,dbuserid,dbpasswd);
           else
                conn = DriverManager.getConnection(dbUrl);

           conn.setAutoCommit(false);
          

         } catch ( Exception e ) {
          
             conn = null;
            
             errormsg = "SqlError in creating connection to "+dbUrl+ " "+ e.toString();
             System.out.println(errormsg);
             e.printStackTrace();
             throw new RuntimeException(e);
         }
        }
   public void rollback() {
         try
         {
           if (conn != null) conn.rollback();
         } catch ( Exception e ) {
           errormsg = "Rollback Sqlerror to "+dbUrl+" " +e.toString();
           System.out.println(errormsg);
           e.printStackTrace();
         }
   }
		/**
		 * @return Returns the conn.
		 */
		public Connection getConn() {
			if (conn == null) {
				connect();
			}
			return conn;
		}
}