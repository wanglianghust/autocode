package com.codegenerator.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import com.codegenerator.common.ApplicationObject;

public class TableList extends ApplicationObject {
	public static String crlf = System.getProperties().getProperty("line.separator");
	public static char   eofchar[] = {0x1A};
	public static String eofstr = new String(eofchar);
	protected Hashtable tableList;

	/**
	 * 
	 */
	public TableList() {
		super();
		
	}
	protected void initTableList() {
		tableList  = new Hashtable();
	}

	/**
	 * @return Returns the tableList.
	 */
	public Hashtable getTableList() {
		return tableList;
	}
	public List getSortedTableObjects() {
		
		List sortedObjects = new ArrayList();
		List nameList = getSortedTableNames();
		int numObjects = nameList.size();
		for (int i=0;i<numObjects;i++) {
			String name = (String) nameList.get(i);
			sortedObjects.add(tableList.get(name));
		}
		return sortedObjects;
	}

	/**
	 * @return
	 */
	public List getSortedTableNames() {
		Enumeration names = tableList.keys();
		List nameList = new ArrayList();
		while (names.hasMoreElements()) {
			nameList.add(names.nextElement());
		}
		Collections.sort(nameList);
		return nameList;
	}
}
