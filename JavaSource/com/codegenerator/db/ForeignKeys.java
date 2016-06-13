package com.codegenerator.db;

import com.codegenerator.common.ApplicationObject;
import com.codegenerator.common.ListHashtable;

public class ForeignKeys extends ApplicationObject {

	protected SqlTable parentTable;
	protected ListHashtable associatedTables;
	/**
	 * Constructor for Foreign Keys
	 */
	public ForeignKeys(SqlTable aTable) {
		super();
		parentTable      = aTable;
		associatedTables = new ListHashtable();
	}
	/**
	 * @param tableName
	 * @param columnName
	 * @param seq
	 */
	public void addForeignKey(String tableName, String columnName,  String parentColumn, Integer seq) {
		ForeignKey tbl = null;
		if (associatedTables.containsKey(tableName)) {
			tbl = (ForeignKey) associatedTables.get(tableName);
		}
		else {
			tbl = new ForeignKey(parentTable,tableName);
			associatedTables.put(tableName,tbl);
		}
		 
		tbl.addColumn(columnName, parentColumn, seq);
		
	}
	

	/**
	 * @return Returns the associatedTables.
	 */
	public ListHashtable getAssociatedTables() {
		return associatedTables;
	}
	public int getSize() {
		return getAssociatedTables().size();
	}
	public boolean getHasImportedKeyColumn(String aColumn) {
		boolean isFound = false;
		int numKeys = getAssociatedTables().size();
		for (int i=0;i<numKeys;i++) {
			ForeignKey aKey = (ForeignKey) getAssociatedTables().getOrderedValue(i);
			if (aKey.getHasImportedKeyColumn(aColumn)) {
				isFound = true;
				break;
			}
		}
		return isFound;
	}
	public ForeignKey getAssociatedTable(String name) {
		Object fkey = getAssociatedTables().get(name);
		if (fkey != null) {
			return (ForeignKey) fkey;
		}
		else return null;
	}
	/**
	 * @return Returns the parentTable.
	 */
	public SqlTable getParentTable() {
		return parentTable;
	}
	public boolean getHasImportedKeyParentColumn(String aColumn) {
		boolean isFound = false;
		int numKeys = getAssociatedTables().size();
		for (int i=0;i<numKeys;i++) {
			ForeignKey aKey = (ForeignKey) getAssociatedTables().getOrderedValue(i);
			if (aKey.getHasImportedKeyParentColumn(aColumn)) {
				isFound = true;
				break;
			}
		}
		return isFound;
	}
	public ForeignKey getImportedKeyParentColumn(String aColumn) {
		ForeignKey aKey = null;
		int numKeys = getAssociatedTables().size();
		for (int i=0;i<numKeys;i++) {
			aKey = (ForeignKey) getAssociatedTables().getOrderedValue(i);
			if (aKey.getHasImportedKeyParentColumn(aColumn)) {
				break;
			}
		}
		return aKey;
	}
}
