package com.codegenerator.db;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

import com.codegenerator.common.ApplicationProperties;
import com.codegenerator.common.Functions;

public class CsvTableList extends TableList {
		
	public CsvTableList(String csvFile) {
		super();
		initFromCsvFile(csvFile);
	}
	private void addToList(String tb,  String entity, 
		           String col,  String attrib,
		           String type, String nulltxt,
		           String pkey, String fkey,String comment ) {
			           
	SqlTable tableObject = (SqlTable) tableList.get(tb);
	  // look in the hashtable to find the table, if it doesn't exist, create it.
	if (tableObject == null) {
		  String schema = ApplicationProperties.getDbSchema();
		  tableObject = new SqlTable(tb, schema,entity,false, false);
		  tableList.put(tb, tableObject);
	}
	 // add column to the table
	boolean nullable = !Functions.hasMask(nulltxt,"NOT");
	boolean withDefault = nullable;
	boolean isKey = Functions.hasMask(pkey,"Yes");
	
	// parse type to find length and digits
	int colsize   = 0;
	int digits    = 0;
	short coltype = 0;
	String coltypname = type;
	if (type.indexOf("(") > 0) {
		StringTokenizer tline = new StringTokenizer(type,",()");
		coltypname = tline.nextToken().trim();
		colsize = (new Integer((tline.nextToken().trim()))).intValue();
		if (tline.hasMoreTokens()) {
			digits = (new Integer (tline.nextToken().trim())).intValue();
		}
		
	}
	 SqlColumn newColumn = new SqlColumn
	 (col,attrib,coltype,colsize,digits,coltypname,nullable,withDefault,comment);
	 newColumn.setKey(isKey);
	 tableObject.addColumn (newColumn);	
	 if (isKey) {
	 	tableObject.getPrimaryKeys().add(newColumn);
	 }
}
/**
 * This method was created in VisualAge.
 */
private Hashtable initFromCsvFile(String rptFile) {
	
  BufferedReader in;
  initTableList();
  
  try {

	in   = new BufferedReader(new InputStreamReader(new FileInputStream(rptFile)));
	String line = in.readLine();
	// Ignore Header Line
	line = in.readLine();
	// while there are data records, parse and build the hastable that stores the table information
	while (line != null && !line.equals(eofstr)) {
		parseRptLine(line, tableList);
		line = in.readLine();
	}
	
	in.close();
	
  }  catch (Exception e) {
	  System.out.println( "Error accessing file: " + rptFile + crlf + e);
	  System.exit(1);
  }
  
  return tableList;
  
}
/**
 * This method was created in VisualAge.
 * @return java.util.Hashtable
 */
public Hashtable getTableList() {
	return tableList;
}
/**
 * This method was created in VisualAge.
 */
private void parseRptLine(String newLine, Hashtable tableList) throws Exception {


	StringTokenizer tline = new StringTokenizer(newLine,",");
	String tableName  = "";
	String columnName = "";
	String type = "";
	String nulltxt = "";
	String entity  = "";
	String attrib  = "";
	String pkey = "No";
	String fkey = "No";
	String comment  = "";
	
	try {
	if (tline.hasMoreTokens()) {
	  tableName      = tline.nextToken().trim();
	  columnName     = tline.nextToken().trim();
	  type    = tline.nextToken().trim();
	  nulltxt = tline.nextToken().trim();
	  if (nulltxt.indexOf("NULL") < 0) {
		  type += "," + nulltxt;
		  nulltxt = tline.nextToken().trim();
	  }
	  if (tline.hasMoreTokens()) 
	  	entity  = tline.nextToken().trim();
	  if (tline.hasMoreTokens()) 
	  	attrib  = tline.nextToken().trim();
	  if (tline.hasMoreTokens()) 
	  	pkey    = tline.nextToken().trim();
	  if (tline.hasMoreTokens()) 
	  	fkey    = tline.nextToken().trim();
	  if (tline.hasMoreTokens()) 
		comment  = tline.nextToken().trim();
	  	
	  addToList(tableName, entity, columnName, attrib, type, nulltxt, pkey, fkey, comment);
	}
}
catch (Exception e)	{
		throw new Exception("Cannot parse this line From CSV file" + newLine);
}
}
}
