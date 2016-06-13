package com.codegenerator.common;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

public abstract class PropertiesResolver extends ApplicationObject {

	private ListHashtable propertyValues = null;

	/**
	 * 
	 */
	public PropertiesResolver() {
		super();
	}

	public abstract String getFileKey();

	private synchronized void initPropertyValues() {
		if (propertyValues != null)
			return;

		propertyValues = new ListHashtable();

		String mvcFramework = ApplicationProperties.getMvcFramework();
		String fKey = ApplicationProperties.getProperty(getFileKey());
		String filePrefix = "";
		String fileName = "";
		String mvcFileName = "";
		String FS = File.separator;

		if (fKey == null || fKey.equals("")) {
			return;
		} else {
			filePrefix = "." + FS + "properties" + FS
					+ ApplicationProperties.getFramework() + FS;
			fileName = filePrefix + fKey;
			mvcFileName = filePrefix + mvcFramework + fKey;
			if (fileName.indexOf(".properties") < 0) {
				fileName = fileName + ".properties";
			}
			if (mvcFileName.indexOf(".properties") < 0) {
				mvcFileName = mvcFileName + ".properties";
			}
		}
		FileUtility fl = new FileUtility();
		try {
			propertyValues = fl.getInputFileAsListHashtable(fileName);
			if (!mvcFramework.equals("")) {
				fl.updatePropertiesFromFile(mvcFileName, propertyValues);
			}
		} catch (IOException e) {
			System.out.println("Error reading package name resolver file: "
					+ e.getMessage());
			throw new RuntimeException(e);
		}

	}

	public ListHashtable getPropertyValues() {
		if (propertyValues == null)
			initPropertyValues();
		return propertyValues;
	}

	public String getPropertyValue(String lookupKey1, String lookupKey2) {
		String value = getPropertyValue(lookupKey1, false);
		// returned value must contain a package otherwise
		// reset to blank
		if (value.indexOf(".") < 0) {
			value = "";
		}
		if (value.equals(""))
			value = getPropertyValue(lookupKey2, true);
		return value;
	}

	public String getPropertyValue(String lookupKey) {
		return getPropertyValue(lookupKey, true);
	}

	public String getPropertyValue(String lookupKey, boolean lookupDefault) {
		String propertyValue = "";
		ListHashtable propValues = getPropertyValues();

		List orderedKeys = propValues.getOrderedKeys();
		int numValues = orderedKeys.size();
		for (int i = 0; i < numValues; i++) {
			String key = (String) orderedKeys.get(i);
			String value = (String) propValues.get(key);
			if (Functions.hasMask(lookupKey, key)) {
				Vector oneParm = StringUtil.parseToVector(value,"^");
		     	String template   = (String) oneParm.elementAt(0);    
				propertyValue = value;
				break;
			}
		}
		if (propertyValue.equals("") && lookupDefault) {
			propertyValue = (String) propValues.get("DEFAULT");
			if (propertyValue == null)
				propertyValue = "";
		}
		while (Functions.hasMask(propertyValue, "${")) {
			propertyValue = resolveParms(propertyValue);
		}
		return propertyValue;
	}

	public static String resolveParms(String propertyValue) {

		String value = propertyValue;
		int startpos = propertyValue.indexOf("${");
		int endpos = propertyValue.indexOf("}");

		if (startpos >= 0 && endpos > startpos) {

			String key = propertyValue.substring(startpos + 2, endpos);
			ListHashtable requestProperties = ThreadContext.getCurrentContext()
					.getRequestProperties();
			String keyValue = (String) requestProperties.get(key);
			if (keyValue == null) {
				keyValue = ApplicationProperties.getProperty(key);
			}
			if (keyValue != null) {
				String keyString = "${" + key + "}";
				value = Functions.replaceString(propertyValue, keyString,
						keyValue);
			}
		}
		return value;

	}

}
