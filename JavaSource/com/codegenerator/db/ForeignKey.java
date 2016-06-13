package com.codegenerator.db;

import java.util.List;

import com.codegenerator.common.ApplicationObject;
import com.codegenerator.common.ApplicationProperties;
import com.codegenerator.common.ListHashtable;

public class ForeignKey extends ApplicationObject {

	
	protected String   relationShip   = null;
	protected String   firstRelation  = null;
	protected String   secondRelation = null;
	protected SqlTable parentTable ;
	protected String   tableName;
	protected ListHashtable columns ;
	protected ListHashtable parentColumns;
	
	public ForeignKey(SqlTable aTable, String tblName) {
		super();
		parentTable = aTable;
		tableName   = tblName;
		columns = new ListHashtable();
		parentColumns = new ListHashtable();
		
	}

	/**
	 * @return Returns the tableName.
	 */
	public String getTableName() {
		return tableName;
	}
	public String getParentTableName() {
		return parentTable.getTable();
	}
	/**
	 * @param col
	 * @param seq
	 */
	public void addColumn(String col,  String parentCol, Integer seq) {
		columns.put(seq, col);
		parentColumns.put(seq,parentCol);
	}
	public String getColumn(String parentCol) {
		// return the associated column given the parent column
		Object key = parentColumns.getKeyForValue(parentCol);
		String col = (String) columns.get(key);
		//System.out.println("get Column for" +parentCol);
		//System.out.println("key = "+key);
		//System.out.println("col="+col);
		//System.out.println("ParentColumns = "+parentColumns.toString());
		return col;
	}
	public ListHashtable getColumns() {
		return columns;
	}
	/**
	 * 
	 */
	private void initRelationship() {
		firstRelation   = "";
		secondRelation  = "";
		SqlTable foreignTable = (SqlTable) ApplicationProperties.getSqlTables().get(tableName);
		List parentPrimaryKeys    = parentTable.getPrimaryKeys();
		List foreignPrimaryKeys   = foreignTable.getPrimaryKeys();
		
		if (hasAllPrimaryKeys(parentPrimaryKeys,parentColumns))
			firstRelation = "one";
		else
			firstRelation = "many";

		if (hasAllPrimaryKeys(foreignPrimaryKeys,columns))
			secondRelation = "one";
		else
			secondRelation = "many";

		relationShip = firstRelation + "-to-" + secondRelation;
		 
	}
	private boolean hasAllPrimaryKeys(List pkeys, ListHashtable cols) {
		boolean hasAll = true;
		// if size is not equal then false
		int numKeys = pkeys.size();
		if (numKeys != cols.size())
			return false;
		
		for (int i=0;i<numKeys;i++) {
			SqlColumn col = (SqlColumn) pkeys.get(i);
			String colname = col.getColname();
			if (!cols.contains(colname))
				return false;
		}
		
		return hasAll;
	}
	public boolean isParentColumnsFromPrimaryKey() {
		boolean isFrom = true;
		List keys = parentTable.getPrimaryKeys();
		int numKeys = getParentColumns().size();
		for (int i=0;i<numKeys;i++) {
			String pcol = (String) getParentColumns().getOrderedValue(i);
			if (!primaryKeyHasColumn(pcol)) {
				isFrom=false;
				break;
			}
		}
		return  isFrom;
	}
	private boolean primaryKeyHasColumn(String aColumn) {
		boolean isFound = false;
		int numKeys = parentTable.getPrimaryKeys().size();
		for (int i=0;i<numKeys;i++) {
			SqlColumn sqlCol = parentTable.getPrimaryKey(i);
			String colname = sqlCol.getColname();
			if (colname.equals(aColumn)) {
				isFound = true;
				break;
			}
		}
		return isFound;
	}
	public boolean getHasImportedKeyColumn(String aColumn) {
		boolean isFound = false;
		List cols = getColumns().getOrderedValues();
		int numCols = cols.size();
		for (int i=0;i<numCols;i++) {
			String col = (String) cols.get(i);
			if (col.equals(aColumn)) {
				isFound = true;
				break;
			}
		}
		return  isFound;
	}
	/**
	 * @return Returns the firstRelation.
	 */
	public String getFirstRelation() {
		if (firstRelation == null)
			initRelationship();
		return firstRelation;
	}
	public SqlTable getSqlTable() {
		SqlTable table = (SqlTable) ApplicationProperties.getSqlTables().get(tableName);
		return table;
	}
	/**
	 * @return Returns the parentTable.
	 */
	public SqlTable getParentTable() {
		return parentTable;
	}
	/**
	 * @return Returns the relationShip.
	 */
	public String getRelationShip() {
		if (relationShip == null)
			initRelationship();
		return relationShip;
	}
	/**
	 * @return Returns the secondRelation.
	 */
	public String getSecondRelation() {
		if (secondRelation == null)
			initRelationship();
		return secondRelation;
	}
	/**
	 * @return Returns the parentColumns.
	 */
	public ListHashtable getParentColumns() {
		return parentColumns;
	}

	public boolean getHasImportedKeyParentColumn(String aColumn) {
		
		boolean isFound = false;
		List cols = getParentColumns().getOrderedValues();
		int numCols = cols.size();
		for (int i=0;i<numCols;i++) {
			String col = (String) cols.get(i);
			if (col.equals(aColumn)) {
				isFound = true;
				break;
			}
		}
		return  isFound;
	}
}
