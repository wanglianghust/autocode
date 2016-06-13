package com.codegenerator.common;

import java.util.Enumeration;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.Vector;
import com.codegenerator.db.Dbconn;
import com.codegenerator.db.SqlTable;

public class ApplicationProperties extends ApplicationObject {
	public static String framework = "tsb";
	protected static String propertiesFileName = "generator";
	protected static Properties properties = null;
	protected static Vector excludeProperties = null;
	protected static Vector includeProperties = null;
	protected static List singleKeyGenerator = null;
	protected static String generatorClass = null;
	protected static String mvcFramework = "";
	protected static ListHashtable sqlTables = null;
	protected static ListHashtable pojoNames = null;
	protected static ListHashtable puMap = null;

	private Dbconn dbconn = null;

	/**
	 * StarpacProperties constructor comment.
	 */
	public ApplicationProperties() {
		super();
	}

	/**
	 * StarpacProperties constructor comment.
	 */
	public ApplicationProperties(Properties aProp) {
		properties = aProp;
	}

	/**
	 * Return the useCaseSensitiveNames
	 */
	public static String getUseCaseSensitiveNames() {
		return getProperty("useCaseSensitiveNames");
	}

	public static String getFramework() {
		return getProperty("framework");
	}

	/**
	 * Return the dbOwner
	 */
	public static String getDbSchema() {
		return getProperty("dbSchema");
	}

	/**
	 * This method was created in VisualAge.
	 */
	public static String getDbUrl() {
		return getProperty("dbUrl");
	}

	/**
	 * This method was created in VisualAge.
	 */
	public static String getDbUserid() {
		return getProperty("dbUserid");
	}

	/**
	 * This method was created in VisualAge.
	 */
	public static String getDbPasswd() {
		return getProperty("dbPasswd");
	}

	/**
	 * This method was created in VisualAge.
	 */
	public static String getJdbcDriver() {
		return getProperty("jdbcDriver");
	}

	/**
	 * This method was created in VisualAge.
	 */
	public static String getModelTemplate() {
		return getProperty("modelTemplate");
	}

	/**
	 * This method was created in VisualAge.
	 * 
	 * @return java.util.Properties
	 */
	public static synchronized Properties getDefaultProperties() {
		return getDefaultProperties(framework + "." + propertiesFileName);
	}

	/**
	 * This method was created in VisualAge.
	 * 
	 * @return java.util.Properties
	 */
	public static Properties getDefaultProperties(String fileName) {
		PropertyResourceBundle configBundle = (PropertyResourceBundle) PropertyResourceBundle
				.getBundle(fileName);
		//
		Properties newProp = new Properties();
		Enumeration keys = configBundle.getKeys();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			String value = (String) configBundle.getObject(key);
			newProp.put(key, value);
			if (Functions.hasMask(value, "Template")) {
				if (!Functions.hasMask(value, "Common")) {
					int pos = value.indexOf("Template");
					if (pos > 0)
						mvcFramework = value.substring(0, pos);
				}
			}
		}

		return newProp;
	}

	/**
	 * Insert the method's description here. Creation date: (5/9/02 11:46:17 AM)
	 * 
	 * @return java.util.Properties
	 */
	public static java.util.Properties getProperties() {
		synchronized (ApplicationProperties.class) {
			if (properties == null) {
				properties = getDefaultProperties();
			}
		}

		return properties;
	}

	/**
	 * Return a property value for a given key
	 */
	public static String getProperty(String aKey) {

		String propVal = "";
		try {
			propVal = getProperties().getProperty(aKey);
			if (propVal == null)
				throw new Exception("Property value null for " + aKey);
		} catch (Exception e) {
			System.out.println("Property " + aKey
					+ " Not found in properties file");
			propVal = "";
		}
		return propVal;
	}

	public static Vector getExcludeProperties() {
		if (excludeProperties == null) {
			initExcludeProperties();
		}
		return excludeProperties;

	}

	public static Vector getIncludeProperties() {
		if (includeProperties == null) {
			initIncludeProperties();
		}
		return includeProperties;

	}

	public static boolean isExcludedProperty(String template) {
		boolean exclude = false;
		int numProp = getExcludeProperties().size();
		for (int i = 0; i < numProp; i++) {
			String mask = (String) getExcludeProperties().elementAt(i);
			if (Functions.hasMask(template, mask)) {
				exclude = true;
				break;
			}
		}
		// check inclusion list
		if (!exclude)
			exclude = !isIncludedProperty(template);

		return exclude;
	}

	public static boolean isIncludedProperty(String template) {
		// return true if include properties is empty vector
		// otherwise check the vector to ensure that the property
		// requested is in the inclusion list
		boolean include = true;
		int numProp = getIncludeProperties().size();
		if (numProp > 0)
			include = false;
		else {
			return include;
		}
		for (int i = 0; i < numProp; i++) {
			String mask = (String) getIncludeProperties().elementAt(i);
			if (Functions.hasMask(template, mask)) {
				include = true;
				break;
			}
		}
		return include;
	}

	public static void initExcludeProperties() {
		excludeProperties = new Vector();
		String exclude = getProperty("exclude");
		if (exclude.equals(""))
			return;
		excludeProperties = StringUtil.parseToVector(exclude, ",");
	}

	public static void initIncludeProperties() {
		includeProperties = new Vector();
		String include = getProperty("include");
		if (include.equals(""))
			return;
		includeProperties = StringUtil.parseToVector(include, ",");
	}

	public static List getSingleKeyGenerator() {
		if (singleKeyGenerator == null) {
			singleKeyGenerator = new ArrayList();
			generatorClass = "";
			String val = getProperty("singleKeyGenerator");
			if (!val.equals("")) {
				Vector t1 = StringUtil.parseToVector(val, ":,");
				int num = t1.size();
				if (num > 0)
					generatorClass = (String) t1.elementAt(0);
				for (int i = 1; i < num; i++) {
					String elem = (String) t1.elementAt(i);
					singleKeyGenerator.add(elem);
				}
			}
		}
		return singleKeyGenerator;
	}

	public static boolean isGeneratedKey(SqlTable aTable) {
		boolean genKey = aTable.getHasSingleKey();
		// Key must have one of the following types in the sinlgekeygenerator
		// list
		if (genKey) {
			String keyType = aTable.getPrimaryKey(0).getAttType();
			if (keyType.indexOf(".") > 0) {
				keyType = StringUtil.trimToLastDot(keyType);
			}
			List genList = getSingleKeyGenerator();
			if (!genList.contains(keyType)) {
				genKey = false;
			}
		}
		return genKey;
	}

	/**
	 * list the properties except the password.
	 */
	public String toString() {
		return getProperties().toString();
	}

	/**
	 * @return
	 */
	public static String getPropertiesFileName() {
		return propertiesFileName;
	}

	/**
	 * @param propertiesFileName
	 *            The propertiesFileName to set.
	 */
	public static void setPropertiesFileName(String propertiesFileName) {
		ApplicationProperties.propertiesFileName = propertiesFileName;
	}

	/**
	 * @return Returns the mvcFramework.
	 */
	public static String getMvcFramework() {
		return mvcFramework;
	}

	/**
	 * @param properties
	 *            The properties to set.
	 */
	public static void destroyProperties() {
		ApplicationProperties.properties = null;
	}

	/**
	 * @return Returns the generatorClass.
	 */
	public static String getGeneratorClass() {
		return generatorClass;
	}

	public static ListHashtable getSqlTables() {
		if (sqlTables == null) {
			sqlTables = new ListHashtable();
		}
		return sqlTables;
	}

	/**
	 * @param tableName
	 * @return
	 */
	public static SqlTable getSqlTable(String tableName) {
		if (getSqlTables().containsKey(tableName)) {
			return (SqlTable) getSqlTables().get(tableName);
		} else
			return null;
	}

	/**
	 * @return Returns the pojoNames.
	 */
	public static ListHashtable getPojoNames() {
		if (pojoNames == null) {
			initPojoNames();
		}
		return pojoNames;
	}

	public static Hashtable getPUMap() {

		if (puMap == null) {
			initPUMap();
		}
		return puMap;
	}

	private static void initPUMap() {
		String entityNamesFile = getFramework() + "PU";
		FileUtility flUtil = new FileUtility();
		try {
			puMap = flUtil.getInputFileAsListHashtable(entityNamesFile);
		} catch (Exception e) {
			System.out.println("Exception encountered reading pumap: "
					+ entityNamesFile);
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	/**
	 * @throws Exception
	 */
	private static void initPojoNames() {
		String entityNamesFile = getFramework() + "PojoNames";
		FileUtility flUtil = new FileUtility();
		try {
			pojoNames = flUtil.getInputFileAsListHashtable(entityNamesFile);
		} catch (Exception e) {
			System.out
					.println("Exception encountered reading entityNamesFile: "
							+ entityNamesFile);
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
		// System.out.println("PojoNames="+pojoNames.toString());
	}
}
