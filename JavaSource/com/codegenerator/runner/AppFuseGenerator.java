/**
 * 版权所有：版权所有(C) 2016，学酷网络
 * 文件编号：AppFuseGenerator.java
 * 文件名称：AppFuseGenerator.java
 * 系统编号：
 * 系统名称：
 * 设计作者：
 * 模块编号：
 * 模块名称：
 * 
 */
package com.codegenerator.runner;

import java.util.*;

import org.apache.velocity.*;

import com.codegenerator.common.ApplicationProperties;
import com.codegenerator.common.FieldTypeResolver;
import com.codegenerator.common.FileLocationResolver;
import com.codegenerator.common.FileUtility;
import com.codegenerator.common.Functions;
import com.codegenerator.common.PackageNameResolver;
import com.codegenerator.common.StringUtil;
import com.codegenerator.common.TemplateProcessor;
import com.codegenerator.common.ThreadContext;
import com.codegenerator.db.CsvTableList;
import com.codegenerator.db.SqlTable;

/**
 * 类 编 号：AppFuseGenerator
 * 类 名 称：AppFuseGenerator
 * 内容摘要：代码产生
 * 完成日期：2016.6.6
 * 编码作者：wl
 */
public class AppFuseGenerator {

	//获得cvsTableList的列表	
	private static CsvTableList csvTableList = null;

	/**
	 * 代码产生文件的起始点
	 * @param args 获得用户输入的字符串
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {

		String[] args1 = args;

		int numArgs = args1.length;
		//如果获得的参数为0，为该参数负于初始值
		if (numArgs < 1) {
			args1 = new String[] { "@tsbTables", "sys=tsb" };
		}
		//过滤参数
		String[] inputArgs = filter(args1);
		// check and initialize whether we are reading tables from a 
		// flat file or getting it from the database schema
		initCsvTableList();

		// get table name from file list if argument 0 starts with @
		Object[] tableNames;
		//如果该字符数组的第一个字母是@开头过滤
		if (inputArgs[0].startsWith("@")) {
			FileUtility flUtil = new FileUtility();
			List tabList = flUtil
					.getInputFileAsArray(inputArgs[0].substring(1));//读取文件tsbTables中的内容
			tableNames = tabList.toArray();
		}
		//分割-
		else if (inputArgs[0].indexOf("-") >= 0) {
			Vector tables = StringUtil.parseToVector(inputArgs[0], "-");
			tableNames = new String[tables.size()];
			tables.copyInto(tableNames);
		}
		//tablename为上面输入的参数之一
		else {
			tableNames = inputArgs;
		}

		String schema = ApplicationProperties.getDbSchema();
		String useCaseSensitiveNames = ApplicationProperties
				.getUseCaseSensitiveNames();

		//init the file for embbed generate
		//generateInitFiles();
		
		// :NPA: isCaseSensitive if property is set to 'true','t','yes','y', or '1'
		boolean isCaseSensitive = useCaseSensitiveNames != null
				&& (Functions.hasMask(useCaseSensitiveNames, "y")
						|| Functions.hasMask(useCaseSensitiveNames, "t") || Functions
						.hasMask(useCaseSensitiveNames, "1"));

		int numTables = tableNames.length;
		System.out.println("!!!!!!!! Generator Started for Framework: "
				+ ApplicationProperties.framework + " !!!!!!!!!");
		//获取单个的表名
		for (int i = 0; i < numTables; i++) {
			String tabName = (String) tableNames[i];
			generateFilesForOneTable(tabName, schema, isCaseSensitive);
		}
		//销毁所有的file package
		FieldTypeResolver.destroyResolver();
		FileLocationResolver.destroyResolver();
		PackageNameResolver.destroyResolver();
		ApplicationProperties.destroyProperties();

	}

	/**
	 * 从一个表中产生文件
	 * @param tableName
	 * @param schema
	 * @throws Exception
	 */
	private static void generateFilesForOneTable(String tableName,
			String schema, boolean isCaseSensitive) throws Exception {
		SqlTable sqlTable = getSqlTable(tableName, schema, isCaseSensitive);

		generateFilesForSqlTable(sqlTable, schema, isCaseSensitive);
	}

	/**
	 * 从sqltable中产生文件
	 * @param sqlTable
	 * @param schema
	 * @param isCaseSensitive
	 * @throws Exception
	 */
	private static void generateFilesForSqlTable(SqlTable sqlTable,
			String schema, boolean isCaseSensitive) throws Exception {
		System.out.println("--------> Start Generation for Table:  "
				+ sqlTable.getTable() + " <-----------");
		//  Create velocity context
		Functions commonFunctions = new Functions();
		VelocityContext context = new VelocityContext();
		//把需要产生的文件内容放入模版中
		context.put("sqlTable", sqlTable);
		context.put("utility", commonFunctions);
		ApplicationProperties appProperties = ThreadContext.getCurrentContext()
				.getApplicationProperties();
		context.put("prop", appProperties);

		// create instance of template processor and pass it the
		// context to process all templates in this properties file

		TemplateProcessor templateProcessor = new TemplateProcessor(
				ApplicationProperties.getProperties());
		templateProcessor.process(context);
		System.out.println("********* End of Generation for Table: "
				+ sqlTable.getTable() + " ************");
		sqlTable.setGenerated(true);
	}

	/**
	 * 文件产生的初始化
	 * @throws Exception
	 */
	private static void generateInitFiles() throws Exception {
		System.out.println("--------> Start Generation for init templates  ");
		//  Create velocity context
		//    Functions commonFunctions = new Functions();
		List sqlTableList = new ArrayList();

		VelocityContext context = new VelocityContext();

		//context.put("utility", commonFunctions);
		ApplicationProperties appProperties = ThreadContext.getCurrentContext()
				.getApplicationProperties();
		context.put("prop", appProperties);

		Properties prop = ApplicationProperties
				.getDefaultProperties("ssb.InitTemplate");

		// create instance of template processor and pass it the
		// context to process all templates in this properties file

		TemplateProcessor templateProcessor = new TemplateProcessor(prop);
		templateProcessor.processInitTemplates(context);
		System.out.println("********* End of Generation the init templates ");
	}

	/**
	 * 初始化CVStablelist
	 *
	 */
	public static void initCsvTableList() {
		String csvFile = ApplicationProperties.getProperty("csvFile");
		if (csvFile.equals(""))
			return;
		csvTableList = new CsvTableList(csvFile);
	}

	/**
	 * This method filters out the system specified on the command line
	 * The Other parameters passed into the mainline must be tables either
	 * individually specified or in a list: 
	 * Note: If sys= is not specified default is appfuse
	 * @param args
	 * @return
	 */
	private static String[] filter(String[] args) {
		Vector output = new Vector();

		int numArgs = args.length;
		//循环过滤
		for (int i = 0; i < numArgs; i++) {
			String arg = args[i];
			if (arg.toUpperCase().startsWith("SYS=")) {
				String frw = arg.substring(4).toLowerCase();
				// First call to set application properties framework
				// otherwise default will be to appfuse framework
				ApplicationProperties.framework = frw;
			}
			//加入子元素输出
			else {
				output.addElement(arg);
			}
			System.out.println("======");
			System.out.println(arg);
		}
		String[] outputArgs = new String[output.size()];
		output.copyInto(outputArgs);

		return outputArgs;
	}

	/**
	 * 获得数据库表
	 * @param tableName
	 * @param schema
	 * @param isCaseSensitive
	 * @return
	 */
	public static SqlTable getSqlTable(String tableName, String schema,
			boolean isCaseSensitive) {

		String pojoName = null;
		String[] fields = tableName.split("\\s+");
		//判断文件的长度 
		if (fields.length >= 2) {
			tableName = fields[0];
			pojoName = fields[1];
		}

		SqlTable sqlTable = null;
		//判断csvTableList是否为空
		if (csvTableList != null) {
			sqlTable = (SqlTable) csvTableList.getTableList().get(tableName);
		} 
		else {
			//通过表名判断
			if (ApplicationProperties.getSqlTables().containsKey(tableName)) {
				sqlTable = (SqlTable) ApplicationProperties.getSqlTables().get(
						tableName);
				sqlTable.setEntityName(pojoName);
			}
			//初始化sqlTable
			else {
				sqlTable = new SqlTable(tableName, schema, isCaseSensitive,
						pojoName);
			}
		}
		return sqlTable;
	}

}
