package com.codegenerator.common;

import java.util.HashMap;
import java.util.List;
import com.codegenerator.db.SqlColumn;
import com.codegenerator.db.SqlTable;

public class EzwGen extends ApplicationObject {

	private static final int maxLength = 40;
	private static final String blank15 = "               ";
	private SqlTable sqlTable;
	private StringBuffer selectStmt;
	private StringBuffer updateStmt;
	private StringBuffer insertStmt;
	private StringBuffer valueStmt;
	private StringBuffer deleteStmt;
	private StringBuffer populateList;
	private StringBuffer insertList;
	private StringBuffer updateList;
	private int numColumns = 0;

	/**
	 * @param aTable
	 * @return
	 */
	private void createSqlStatements() {

		HashMap columns = sqlTable.getAllColumns();
		List sqlColumns = sqlTable.getSqlColumns();
		String crlf = Functions.crlf;

		selectStmt = new StringBuffer(quote("SELECT "));
		updateStmt = new StringBuffer(quote("UPDATE ") + " + tableName + "
				+ quote(" SET "));
		deleteStmt = new StringBuffer(quote("DELETE FROM ") + " + tableName ");
		insertStmt = new StringBuffer(quote("INSERT INTO ") + " + tableName ");
		valueStmt = new StringBuffer("");

		numColumns = sqlColumns.size();
		SqlColumn aCol = (SqlColumn) sqlColumns.get(0);
		String colname = aCol.getColname();
		StringBuffer selectTemp = new StringBuffer("a." + colname);
		StringBuffer updateTemp = new StringBuffer(colname + " = ? ");
		StringBuffer insertTemp = new StringBuffer("( " + colname);
		StringBuffer valueTemp = new StringBuffer("VALUES ( ? ");
		updateList = new StringBuffer();
		populateList = new StringBuffer();
		insertList = new StringBuffer();

		colname = "";
		int updateColNum = 0;
		String entity = Functions.makeClassName(sqlTable.getEntityName());
		String entityVar = Functions.makeFirstLetterLowerCase(entity);
		String attrName = attrName = Functions.makeFirstLetterUpperCase(aCol
				.getBusinessAttributeVar());
		updateColNum = appendToLists(false, updateColNum, entityVar, 1, aCol,
				attrName);
		for (int i = 1; i < numColumns - 1; i++) {
			aCol = (SqlColumn) sqlColumns.get(i);
			colname = aCol.getColname();
			process(selectTemp, selectStmt, "a." + colname, false);
			process(updateTemp, updateStmt, colname + " = ? ", false);
			process(insertTemp, insertStmt, colname, false);
			process(valueTemp, valueStmt, "?", false);

			SqlColumn col = (SqlColumn) columns.get(colname);
			attrName = Functions.makeFirstLetterUpperCase(col
					.getBusinessAttributeVar());
			updateColNum = appendToLists(true, updateColNum, entityVar, i + 1,
					col, attrName);
		}
		// process last column
		int lastCol = numColumns - 1;
		aCol = (SqlColumn) sqlColumns.get(lastCol);
		attrName = Functions.makeClassName(aCol.getAttName());
		colname = aCol.getColname();
		process(selectTemp, selectStmt, "a." + colname, true);
		process(updateTemp, updateStmt, colname + " = ?  ", true);
		process(insertTemp, insertStmt, colname + " ) ", true);
		process(valueTemp, valueStmt, "?  ) ", true);

		attrName = Functions.makeFirstLetterUpperCase(aCol
				.getBusinessAttributeVar());
		updateColNum = appendToLists(true, updateColNum, entityVar, numColumns,
				aCol, attrName);

	}

	/**
	 * @param crlf
	 * @param updateColNum
	 * @param entityVar
	 * @param i
	 * @param col
	 * @param attrName
	 * @return
	 */
	protected int appendToLists(boolean prefixBlanks, int updateColNum,
			String entityVar, int i, SqlColumn col, String attrName) {
		String crlf = Functions.crlf;
		String entityVar2 = entityVar;
		if (prefixBlanks) {
			entityVar = "    " + entityVar;
		}
		populateList.append(entityVar + "Tbl.get" + attrName
				+ "().setOriginalDbValue(resultSet," + i + ");" + crlf);
		insertList.append(entityVar + "Tbl.get" + attrName
				+ "().getColumnValue(sqlStatement," + i + ");" + crlf);
		if (!col.isKey()) {
			++updateColNum;
			if (updateColNum > 1)
				entityVar2 = "    " + entityVar2;
			updateList.append(entityVar2 + "Tbl.get" + attrName
					+ "().getColumnValue(sqlStatement," + updateColNum + ");"
					+ crlf);
		}
		return updateColNum;
	}

	public static EzwGen getEzwGen(SqlTable aTable) {
		EzwGen ezw = new EzwGen(aTable);
		return ezw;
	}

	public EzwGen(SqlTable aTable) {
		sqlTable = aTable;
		createSqlStatements();
	}

	private static String quote(String aString) {
		return Functions.quote(aString);
	}

	private static void process(StringBuffer tempBuf, StringBuffer buf,
			String nxtString, boolean flushBuffer) {
		int numChars = tempBuf.toString().length() + nxtString.length();
		if (numChars > maxLength) {
			buf.append(Functions.crlf + blank15 + " + "
					+ quote(tempBuf.toString()));
			tempBuf.setLength(0);
			tempBuf.append(", " + nxtString);

		} else {
			tempBuf.append(", " + nxtString);
		}
		if (flushBuffer) {
			buf.append(Functions.crlf + blank15 + " + "
					+ quote(tempBuf.toString()));
			tempBuf.setLength(0);
		}
		return;
	}

	/**
	 * @return Returns the deleteStmt.
	 */
	public StringBuffer getDeleteStmt() {
		return deleteStmt;
	}

	/**
	 * @return Returns the insertStmt.
	 */
	public StringBuffer getInsertStmt() {
		return insertStmt;
	}

	/**
	 * @return Returns the selectStmt.
	 */
	public StringBuffer getSelectStmt() {
		return selectStmt;
	}

	/**
	 * @return Returns the updateStmt.
	 */
	public StringBuffer getUpdateStmt() {
		return updateStmt;
	}

	/**
	 * @return Returns the valueStmt.
	 */
	public StringBuffer getValueStmt() {
		return valueStmt;
	}

	/**
	 * @return Returns the insertList.
	 */
	public StringBuffer getInsertList() {
		return insertList;
	}

	/**
	 * @return Returns the numColumns.
	 */
	public int getNumColumns() {
		return numColumns;
	}

	/**
	 * @return Returns the populateList.
	 */
	public StringBuffer getPopulateList() {
		return populateList;
	}

	/**
	 * @return Returns the updateList.
	 */
	public StringBuffer getUpdateList() {
		return updateList;
	}
}
